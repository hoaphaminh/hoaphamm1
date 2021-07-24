package vn.hoapm.springboot.application.rest.documentstorage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.common.APIResponse;

/**
 * @ClassName :DocumentStorageController
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/20/2021 5:05 PM
 */

@RestController
public class DocumentStorageController {

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile (@RequestParam("file")MultipartFile file,
                                         @RequestParam("userId") Long userId,
                                         @RequestParam("docType") String docType) {
        DSJsonResponse jsonResponse = new DSJsonResponse();
        APIResponse<DSJsonResponse> apiResponse = new APIResponse<>();
        return apiResponse.sendResponse(jsonResponse, HttpStatus.OK.value(), "upload.success");
    }
}
