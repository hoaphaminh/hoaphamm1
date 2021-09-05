package vn.hoapm.springbootV2.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "playlists")
@Data
@Builder
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "art_background_url")
    private String artBackgroundUrl;

    @Column(name = "duration")
    private int duration;

    @Column(name = "track_count")
    private int trackCount;

    @Column(name = "userInfo_id")
    private int userInfoId;

    @Column(name = "streamable")
    private int streamable;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "utimestamp")
    private Timestamp utimestamp;

    @ManyToOne
    @JoinColumn(name = "userInfo_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserInfo userInfo;

}
