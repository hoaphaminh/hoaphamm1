package vn.hoapm.springboot.application.rest.documentstorage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @ClassName :DSJsonResponse
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/20/2021 6:07 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DSJsonResponse {
    private Long id;
    private Long userId;
    private String fileName;
    private String docType;
    private String docFormat;
    private String uploadDir;
    private Instant createdAt;
}
