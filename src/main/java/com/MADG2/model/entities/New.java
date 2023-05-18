package com.MADG2.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class New implements Serializable {

    //PROPERTIES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 350 , message = "Name between 1 and 350 characters")
    private String link;


    @NotEmpty(message = "title can´t be empty")
    @Size(min = 1, max = 500 , message = "title between 1 and 100 characters")
    private String title;

    private String image;

    @NotEmpty(message = "Text can´t be empty")
    @Size(message = "Text between 1500 and 2000 characters", min = 1, max = 2000)
    private String text;
}
