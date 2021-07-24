package vn.hoapm.springboot.application.rest.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserJSONRequest extends PagingSortFilter {
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String phone;
    private String email;
    private String roleCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Instant utimestamp;
}
