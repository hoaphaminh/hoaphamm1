package vn.hoapm.springboot.domain.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonPagingAndSort {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalCount;

}
