package vn.hoapm.springbootV2.mapper;

import org.springframework.stereotype.Component;
import vn.hoapm.springbootV2.dto.UserInfoDTO;
import vn.hoapm.springbootV2.entities.UserInfo;

@Component
public class UserInfoEntityMapper extends CommonEntityMapper<UserInfo, UserInfoDTO, Long> {


    @Override
    protected Class<UserInfo> getEntityClass() {
        return UserInfo.class;
    }

    @Override
    public UserInfoDTO mapEntityToDTO(UserInfo entity) {
        return UserInfoDTO.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .gender(entity.getGender())
                .followerCount(entity.getFollowerCount())
                .status(entity.getStatus())
                .followingCount(entity.getFollowingCount())
                .trackCount(entity.getTrackCount())
                .playlistCount(entity.getPlaylistCount())
                .online(entity.getOnline())
                .build();
    }

}
