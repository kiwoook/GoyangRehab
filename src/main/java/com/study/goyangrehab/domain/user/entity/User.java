package com.study.goyangrehab.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.goyangrehab.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, length = 50)
    private String userId;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<UserProgram> programs = new HashSet<>();

    @Builder
    public User(String userId) {
        this.userId = userId;
    }

    public void addProgram(UserProgram userProgram) {
        this.programs.add(userProgram);
    }

    public void leaveProgram(UserProgram userProgram) {
        this.programs.remove(userProgram);
    }
}
