package com.bova.poker.mgmt.db.entity;

import com.bova.poker.mgmt.model.enums.UserStoryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_story")
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = "session")
public class UserStoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStoryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private SessionEntity session;
}
