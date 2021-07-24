package vn.hoapm.springboot.application.rest.mapper;

import org.apache.logging.log4j.util.Strings;
import vn.hoapm.springboot.application.rest.user.UserJSONRequest;
import vn.hoapm.springboot.application.rest.user.UserJSONResponse;
import vn.hoapm.springboot.domain.account.factory.UserCUD;
import vn.hoapm.springboot.domain.account.factory.UserSearch;
import vn.hoapm.springboot.domain.account.presentaion.UserRequest;
import vn.hoapm.springboot.domain.account.presentaion.UserResponse;
import vn.hoapm.springboot.domain.shared.PagingSortFilter;


import java.sql.Timestamp;

public class UserJSONMapper {
    private static UserJSONMapper INSTANCE;

    private UserJSONMapper() {

    }

    public static UserJSONMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserJSONMapper();
        }
        return INSTANCE;
    }

    public UserJSONResponse fromResponse(UserResponse userResponse) {
        UserJSONResponse userJSONResponse = new UserJSONResponse();
        if (userResponse != null) {
            userJSONResponse.setUsername(userResponse.getUsername());
            userJSONResponse.setName(userResponse.getName());
            userJSONResponse.setRole(userResponse.getRole());
            userJSONResponse.setPhone(userResponse.getName());
            userJSONResponse.setCreatedAt(userResponse.getCreatedAt().toInstant());
        }
        return userJSONResponse;
    }

    public UserRequest fromJsonRequest(UserJSONRequest jsonRequest) {
        UserRequest request = new UserRequest();
        if (jsonRequest != null) {
            // Map to search
            UserSearch search = UserSearch.builder()
                    .username(Strings.isNotEmpty(jsonRequest.getUsername()) ? jsonRequest.getUsername() : null)
                    .name(Strings.isNotEmpty(jsonRequest.getName()) ? jsonRequest.getName() : null)
                    .build();
            //Map to paging
            PagingSortFilter filter = new PagingSortFilter()
                    .withAsc(jsonRequest.getAsc())
                    .withPageIndex(jsonRequest.getPageIndex())
                    .withPageSize(jsonRequest.getPageSize())
                    .withSearchSuggest(jsonRequest.getSearchSuggest())
                    .withGlobalSearch(jsonRequest.getGlobalSearch());
            // Map to CUD
            UserCUD cud = UserCUD.builder()
                    .id(jsonRequest.getId())
                    .username(jsonRequest.getUsername())
                    .password(jsonRequest.getPassword())
                    .roleCode(jsonRequest.getRoleCode())
                    .email(jsonRequest.getEmail())
                    .phone(jsonRequest.getPhone())
                    .name(jsonRequest.getName())
                    .utimestamp(jsonRequest.getUtimestamp() != null ? Timestamp.from(jsonRequest.getUtimestamp()) : null)
                    .build();
            request.setCud(cud);
            request.setUserSearch(search);
            request.setPagingSortFilter(filter);
        }
        return request;
    }

}
