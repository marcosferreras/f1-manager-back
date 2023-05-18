package com.MADG2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 100 , message = "Name between 1 and 255 characters")
    private String link;

    @NotEmpty(message = "title can´t be empty")
    @Size(min = 1, max = 100 , message = "title between 1 and 100 characters")
    private String title;

    @NotEmpty(message = "Description can´t be empty")
    @Size(min = 1, max = 500 , message = "Description between 1 and 500 characters")
    private String description;

    private Date limitDate;

    //RELATIONSHIPS
    // ONE DRIVER = MANY VOTES
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "survey")
    private List<Vote> votes;

    //MANY SURVEYS = MANY DRIVERS
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "survey_driver", joinColumns = @JoinColumn(name = "survey_id"), inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> drivers;

}
