package com.study.goyangrehab.domain.user.entity;

import com.study.goyangrehab.domain.program.entity.Program;
import com.study.goyangrehab.enums.PendingStatus;
import com.study.goyangrehab.enums.ProgramCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_program")
public class UserProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PendingStatus status;

    @Enumerated(EnumType.STRING)
    private ProgramCategory category;

    @Column(name = "register_start_time")
    private LocalDateTime registerStartTime;

    @Column(name = "register_end_time")
    private LocalDateTime registerEndTime;

    @Builder
    public UserProgram(User user, Program program, ProgramCategory category) {
        this.user = user;
        this.program = program;
        this.status = PendingStatus.PENDING;
        this.category = category;
        this.registerEndTime = program.getRegisterEndTime();

        if (this.registerEndTime.isBefore(LocalDateTime.now())) {
            this.status = PendingStatus.TERMINATE;
        }
    }

    public void updateStatus(PendingStatus status) {
        this.status = status;
    }


}
