package com.MADG2.security.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import com.MADG2.model.dtos.team.TeamDto;
import com.MADG2.model.entities.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull 
    @Column(unique = false)
    private String userName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Rol role;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id", updatable = false, insertable = false)
    private Team team;

    @Column(name = "team_id")
    private Long teamId;

    @Transient
    private TeamDto teamManager;

    public User(@NotNull String name, @NotNull String userName, @NotNull String email, @NotNull String password) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}
