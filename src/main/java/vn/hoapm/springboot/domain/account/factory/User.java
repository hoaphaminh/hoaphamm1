package vn.hoapm.springboot.domain.account.factory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hoapm.springboot.domain.shared.AuditLog;


import java.sql.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private int gender;
    private Date dob;
    private String avatar;
    private String roleCode;
    private String roleName;
    private AuditLog auditLog;
}
