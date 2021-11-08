package vn.hoapm.springbootV2.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
@Table(name = "plans")
@NoArgsConstructor
public class Plan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "utimestamp")
    private Timestamp utimestamp;

    @OneToMany(mappedBy = "plan", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //mappedBy trỏ đến biến plan trong class UserInfo
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<UserInfo> userInfos;

}
