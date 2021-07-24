package vn.hoapm.springboot.application.rest.userprofile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hoapm.springboot.application.rest.user.UserJSONResponse;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileJSONResponse {
    private UserJSONResponse userJSONResponse;
    private String avatar;
    private String bio;
    private String address;
    private int gender;
    private String coverPhoto;
    private int status;
    private Instant createdAt;
}
