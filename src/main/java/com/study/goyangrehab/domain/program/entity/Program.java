package com.study.goyangrehab.domain.program.entity;

import com.study.goyangrehab.domain.program.dto.ProgramRequestDto;
import com.study.goyangrehab.domain.user.entity.User;
import com.study.goyangrehab.domain.user.entity.UserProgram;
import com.study.goyangrehab.enums.ProgramStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "program_category")
@SuperBuilder
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "register_start_time")
    private LocalDateTime registerStartTime;

    @Column(name = "register_end_time")
    private LocalDateTime registerEndTime;

    @Enumerated(EnumType.STRING)
    private ProgramStatus status;

    @ColumnDefault("0")
    @Column(name = "recruitment_capacity")
    private int recruitmentCapacity;

    @ColumnDefault("0")
    @Column(name = "current_attendees")
    private int currentAttendees;

    @Column(name = "document_deadline")
    private LocalDateTime docDeadLine;

    @Column(name = "payment_deadline")
    private LocalDateTime paymentDeadLine;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    private int price;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
    private Set<UserProgram> users = new HashSet<>();


    public void update(ProgramRequestDto programRequestDto) {
        this.name = programRequestDto.getName();
        this.startTime = programRequestDto.getStartTime();
        this.endTime = programRequestDto.getEndTime();
        this.registerStartTime = programRequestDto.getStartTime();
        this.registerEndTime = programRequestDto.getEndTime();
        this.docDeadLine = programRequestDto.getDocDeadLine();
        this.paymentDeadLine = programRequestDto.getPaymentDeadLine();
        this.text = programRequestDto.getText();
    }

    @PrePersist
    public void updateStatusBasedOnTime() {
        if (isBeforeStart()) {
            this.status = ProgramStatus.PENDING;
        } else if (isAfterEnd()) {
            this.status = ProgramStatus.CLOSED;
        } else {
            this.status = ProgramStatus.OPEN;
        }
    }

    public void updateStatus(ProgramStatus status) {
        this.status = status;
    }

    public void addUser(UserProgram userProgram) {
        if (isOngoing() && isNotFull() && !isUserAlreadyJoin(userProgram.getUser())) {
            this.users.add(userProgram);
        } else if (!isNotFull() || isAfterEnd()) {
            this.status = ProgramStatus.CLOSED;
        } else if (isBeforeStart()) {
            this.status = ProgramStatus.OPEN;
        }
    }

    public void removeUser(UserProgram userProgram) {
        this.users.remove(userProgram);
    }

    private boolean isUserAlreadyJoin(User user) {
        for (UserProgram userProgram : users) {
            if (userProgram.getUser().getUserId().equals(user.getUserId())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotFull() {
        return this.users.size() <= this.recruitmentCapacity;
    }

    private boolean isBeforeStart() {
        return LocalDate.now().atStartOfDay().isBefore(this.registerStartTime);
    }

    private boolean isAfterEnd() {
        return LocalDate.now().atStartOfDay().isBefore(this.registerEndTime);
    }

    private boolean isOngoing() {
        return !isBeforeStart() && !isAfterEnd();
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
