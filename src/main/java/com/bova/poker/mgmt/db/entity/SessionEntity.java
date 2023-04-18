package com.bova.poker.mgmt.db.entity;

import com.bova.poker.mgmt.model.enums.DeckType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "session")
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"userStories", "members"})
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeckType deckType;

    @OneToMany(mappedBy = "session", cascade = CascadeType.REMOVE)
    private List<UserStoryEntity> userStories = new ArrayList<>();

    @ManyToMany(mappedBy = "sessions")
    private List<MemberEntity> members = new ArrayList<>();
}
