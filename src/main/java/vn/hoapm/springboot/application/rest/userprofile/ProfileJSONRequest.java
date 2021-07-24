package vn.hoapm.springboot.application.rest.userprofile;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileJSONRequest extends PagingSortFilter {
    private Long id;
    private Long userId;
    private String avatar;
    private String bio;
    private String address;
    private int gender;
    private String coverPhoto;
    private int status;
    //timezone in UTC
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Instant utimestamp;

}
