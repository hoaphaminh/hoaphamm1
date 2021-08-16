package vn.hoapm.springboot.application.rest.documentstorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.hoapm.springboot.application.common.APIResponse;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.documentstorage.presentation.DSRequest;
import vn.hoapm.springboot.domain.documentstorage.service.DSService;

@RestController
public class DocumentStorageController {

    @Autowired
    private DSService dsService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("userId") Long userId,
                                        @RequestParam("docType") String docType) throws CommonException {
        DSRequest dsRequest = DSRequest.builder()
                .userId(userId)
                .docType(docType)
                .build();
        APIResponse<String> apiResponse = new APIResponse<>();
        String uploadDir = dsService.createDS(dsRequest, file);
        return apiResponse.sendResponse(uploadDir, HttpStatus.OK.value(), "upload.success");
    }


}


