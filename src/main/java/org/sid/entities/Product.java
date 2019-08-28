package org.sid.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double currentPrice;
    private boolean promotion;
    private boolean selected;
    //savoir s'il est en stock
    private boolean available;
    private String photoName;

    //@DBRef //mapping object document (et non ORM comme avec JPA)
    @ManyToOne
    private Category category;

}
