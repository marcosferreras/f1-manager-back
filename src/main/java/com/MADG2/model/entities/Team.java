package com.MADG2.model.entities;

import com.MADG2.security.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team implements Serializable {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can´t be empty")
    @Size(min = 1, max = 255 , message = "Name between 1 and 255 characters")
    private String name;

    @NotEmpty(message = "Logo is required")
    @NotNull
    private String logo;

    private String twitter;


    //RELATIONSHIPS

    // ONE TEAM = MANY CARS

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "team")
    @JsonManagedReference
    private List<Car> cars;

    // ONE TEAM = MANY DRIVERS
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "team")
    @JsonManagedReference
    private List<Driver> drivers;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "team")
    @JsonManagedReference
    private List<User> managers;
}
