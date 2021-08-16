package vn.hoapm.springboot.application.rest.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;
import vn.hoapm.springboot.application.common.Validatable;
import vn.hoapm.springboot.application.exception.CommonException;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserJSONRequest extends PagingSortFilter implements Validatable {
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

    @Override
    public void validate() throws CommonException {
        if (StringUtils.isEmpty(username)) {
            throw new CommonException("Username can't be null");
        }
        if (StringUtils.isEmpty(phone)) {
            throw new CommonException("Phone can't be null");
        }
        if (StringUtils.isEmpty(email)) {
            throw new CommonException("Email can't be null");
        }
    }
}
