package vn.hoapm.springboot.domain.account.presentaion;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
