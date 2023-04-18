package com.bova.poker.mgmt.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = "sessions")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @ManyToMany
    @JoinTable(name = "session_member",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private List<SessionEntity> sessions = new ArrayList<>();
}
