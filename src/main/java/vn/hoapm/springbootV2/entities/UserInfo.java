package vn.hoapm.springbootV2.entities;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_infos")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "bio")
    private String bio;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private int gender;

    @Column(name = "follower_count")
    private int followerCount;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "utimestamp")
    private Timestamp utimestamp;

    @Column(name = "following_count")
    private int followingCount;

    @Column(name = "track_count")
    private int trackCount;

    @Column(name = "playlist_count")
    private int playlistCount;

    @Column(name = "online")
    private int online;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Plan plan;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Playlist> playlists;


}
