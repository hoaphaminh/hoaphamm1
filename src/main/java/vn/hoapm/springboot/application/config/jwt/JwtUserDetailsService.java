package vn.hoapm.springboot.application.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.hoapm.springboot.domain.account.core.UserAuth;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserRequest;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.account.service.UserService;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public void setAccountService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserSearch(new UserSearch(username));
        UserResponse userResponse = userService.findByUsername(userRequest);
        if (userResponse != null) {
            UserAuth userAuth = new UserAuth();
            userAuth.setPassword(userResponse.getPassword());
            userAuth.setUsername(userResponse.getUsername());
            userAuth.setRole(userResponse.getRole());
            return new CustomUserDetails(userAuth);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
   