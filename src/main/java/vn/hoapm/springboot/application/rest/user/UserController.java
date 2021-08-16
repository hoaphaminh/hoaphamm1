package vn.hoapm.springboot.application.rest.user;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.common.APIResponse;
import vn.hoapm.springboot.application.config.jwt.CustomUserDetails;
import vn.hoapm.springboot.application.config.jwt.JwtTokenProvider;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.application.rest.mapper.UserJSONMapper;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.LoginRequest;
import vn.hoapm.springboot.domain.account.presentaion.LoginResponse;
import vn.hoapm.springboot.domain.account.presentaion.UserRequest;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.account.service.UserService;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;
import vn.hoapm.springboot.domain.documentstorage.service.DSService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private static final String USERS = "/users";
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";

    private UserService userService;

    private DSService dsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDsService(DSService dsService) {
        this.dsService = dsService;
    }

    @GetMapping(USERS + "/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable("username") String username) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserSearch(new UserSearch(username));
        UserResponse userResponse = userService.findByUsername(userRequest);
        UserJSONResponse jsonResponse = UserJSONMapper.getInstance().fromResponse(userResponse);
        APIResponse<UserJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "user.success.find_by_username");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //return jwt cho nguoi dung
        String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        LoginResponse loginResponse = new LoginResponse(jwt);
        APIResponse<LoginResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(loginResponse, HttpStatus.OK.value(), "login_success");
    }

    @PostMapping("/register")
    // register for account with role is USER
    public ResponseEntity<?> register(@RequestBody UserJSONRequest jsonRequest) throws CommonException {
        jsonRequest.validate();
        jsonRequest.setRoleCode(USER_ROLE);
        UserRequest request = UserJSONMapper.getInstance().fromJsonRequest(jsonRequest);
        UserResponse userResponse = userService.register(request);
        UserJSONResponse jsonResponse = UserJSONMapper.getInstance().fromResponse(userResponse);
        APIResponse<UserJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "user.success.register");
    }

    @PostMapping("/admin")
    // register for account with role is ADMIN
    public ResponseEntity<?> createAdminAccount(@RequestBody UserJSONRequest jsonRequest) throws CommonException {
        jsonRequest.setRoleCode(ADMIN_ROLE);
        UserRequest request = UserJSONMapper.getInstance().fromJsonRequest(jsonRequest);
        UserResponse userResponse = userService.register(request);
        UserJSONResponse jsonResponse = UserJSONMapper.getInstance().fromResponse(userResponse);
        APIResponse<UserJSONResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "user.success.register");
    }

    @GetMapping(USERS)
    public ResponseEntity<?> findUsers(UserJSONRequest userJSONRequest) {
        UserRequest request = UserJSONMapper.getInstance().fromJsonRequest(userJSONRequest);
        List<UserResponse> response = userService.findUsers(request);
        List<UserJSONResponse> jsonResponse = response
                .stream()
                .parallel()
                .map(e -> UserJSONMapper.getInstance().fromResponse(e))
                .collect(Collectors.toList());
        APIResponse<List<UserJSONResponse>> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "user.success.findusers");
    }

    @DeleteMapping(USERS + "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, @RequestBody UserJSONRequest userJSONRequest) {
        userJSONRequest.setId(id);
        UserRequest request = UserJSONMapper.getInstance().fromJsonRequest(userJSONRequest);
        String message = userService.deleteUser(request);
        APIResponse<String> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(message, HttpStatus.OK.value(), "user.success.deleteUser");
    }

    @PostMapping(USERS + "/import")
    public ResponseEntity<?> importUsers(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId,
            @RequestParam("docType") String docType) throws
            CommonException, IOException {
        DSRequest dsRequest = DSRequest.builder()
                .userId(userId)
                .docType(docType)
                .build();
        String uploadDir = dsService.createDS(dsRequest, file);
        List<UserJSONResponse> userJSONResponses = new ArrayList<>();
        if (Strings.isNotBlank(uploadDir)) {
            userJSONResponses = userService.importUser(uploadDir);
        }
        APIResponse<List<UserJSONResponse>> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(userJSONResponses, HttpStatus.OK.value(), "user.success.import");
    }

}
