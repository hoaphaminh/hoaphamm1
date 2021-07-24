package vn.hoapm.springboot.application.rest.user;

import lombok.Data;

import java.time.Instant;

@Data
public class UserJSONResponse {
    private Long id;
    private String name;
    private String username;
    private String phone;
    private Integer status;
    private Instant createdAt;
    private Instant utimestamp;
    private String role;
}
