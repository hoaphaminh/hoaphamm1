package vn.hoapm.springboot.domain.account.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserCUD {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String roleCode;
    private String phone;
    private String email;
    private Timestamp utimestamp;
}
