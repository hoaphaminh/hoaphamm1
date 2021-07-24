package vn.hoapm.springboot.domain.documentstorage.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName :DSResponse
 * @Description :
 * @Author :hoaphaminh
 * @CreatedAt :7/20/2021 7:09 PM
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DSResponse {
    private Long id;
    private Long userId;
    private String fileName;
    private String docType;
    private String docFormat;
    private String uploadDir;
    private Timestamp createdAt;
}
