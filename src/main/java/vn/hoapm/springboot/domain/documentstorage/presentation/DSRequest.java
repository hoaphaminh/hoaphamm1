package vn.hoapm.springboot.domain.documentstorage.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @ClassName :DSRequest
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/20/2021 7:09 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DSRequest {
    private Long id;
    private Long userId;
    private String fileName;
    private String docType;
    private String docFormat;
    private String uploadDir;
}
