package vn.hoapm.springboot.domain.account.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class UserAuth {
    private String username;
    private String password;
    private String role;
}
