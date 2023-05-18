package com.MADG2.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can´t be empty")
    @Size(min = 1, max = 255 , message = "Name between 1 and 255 characters")
    private String name;

    @NotEmpty(message = "Last name can´t be empty")
    @Size(min = 1, max = 255 , message = "Last name between 1 and 255 characters")
    private String lastName;

    @NotEmpty(message = "Acronym can´t be empty")
    @Size(min = 1, max = 8, message = "Acronym between 1 and 8 characters")
    @Column(unique = true)
    private String acronym;

    private Long number;

    private String image;

    private String country;

    private String twitter;

    // RELATIONSHIPS
    // ONE DRIVER = ONE TEAM
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id", updatable = false, insertable = false)
    private Team team;

    @Column(name = "team_id")
    private Long teamId;

    // ONE DRIVER = MANY VOTES
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "driver")
    private List<Vote> votes;

    //MANY DRIVERS = MANY SURVEYS
    @JsonIgnore
    @ManyToMany(mappedBy = "drivers")
    private List<Survey> surveys;
}
