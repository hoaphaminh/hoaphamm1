package vn.hoapm.springboot.domain.shared;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
public class PagingSortFilter {

    @Nullable
    private Integer pageIndex;
    @Nullable
    private Integer pageSize;
    @Nullable
    private Boolean asc;
    @Nullable
    private List<String> fieldSort;
    @Nullable
    private String globalSearch;
    @Nullable
    private String searchSuggest;

    public PagingSortFilter() {
        this.pageIndex = 0;
        this.pageSize = Integer.MAX_VALUE;
        this.asc = true;
        this.fieldSort = new ArrayList<>();
    }

    public PagingSortFilter withPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex == null ? 0 : pageIndex;
        return this;
    }

    public PagingSortFilter withPageSize(Integer pageSize) {
        this.pageSize = pageSize == null ? Integer.MAX_VALUE : pageSize;
        return this;
    }

    public PagingSortFilter withAsc(Boolean asc) {
        this.asc = asc == null ? true : asc;
        return this;
    }

    public PagingSortFilter withFieldSort(List<String> fieldSort) {
        this.fieldSort = fieldSort == null || fieldSort.isEmpty() ? new ArrayList<>() : fieldSort;
        return this;
    }


    public PagingSortFilter withSearchSuggest(String searchSuggest) {
        this.searchSuggest = searchSuggest == null ? null : searchSuggest;
        return this;
    }

    public PagingSortFilter withGlobalSearch(String globalSearch) {
        this.globalSearch = globalSearch == null ? null : globalSearch;
        return this;
    }

}
