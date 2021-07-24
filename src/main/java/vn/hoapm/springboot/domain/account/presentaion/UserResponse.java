package vn.hoapm.springboot.domain.account.presentaion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hoapm.springboot.domain.shared.AuditLog;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private Integer status;
    private Timestamp createdAt;
    private Timestamp utimestamp;


    private String password;
    private String phone;
    private Integer gender;
    private Date dob;
    private String avatar;
    private String role;
    private AuditLog auditLog;
}
