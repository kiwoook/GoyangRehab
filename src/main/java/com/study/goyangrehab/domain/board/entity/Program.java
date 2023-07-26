package com.study.goyangrehab.domain.board.entity;

import com.study.goyangrehab.common.BaseTimeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Program extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name="program_title", nullable = false)
    private String title;

    @ColumnDefault("0")
    @Column(name = "max_user", nullable = false)
    private Integer maxUser = 0;

    @ColumnDefault("0")
    @Column(name="num_user", nullable = false)
    private Integer numUser = 0;







}
