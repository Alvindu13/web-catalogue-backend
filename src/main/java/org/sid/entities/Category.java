package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

//@Document //implique document JSON pour mongoDB sotckage
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString //lombok
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //String pour mongoDB préférable
    private Long id;
    private String name;
    private String description;
    private String photoName;


    //@DBRef //Dans le doc catégorie j'enregistre que l'id du produit, comme ça quand je charge la catégorie je connais la liste des produits de cette catégorie ?
    //Important d'initialiser pour mongoDB pour supposer qu'elle est vide ici


    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private Collection<Product> products;

}
