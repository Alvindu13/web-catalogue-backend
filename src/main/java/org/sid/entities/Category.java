package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document //implique document JSON pour mongoDB sotckage
@Data @AllArgsConstructor @NoArgsConstructor @ToString //lombok
public class Category {

    @Id
    //String pour mongoDB préférable
    private String id;
    private String name;


    @DBRef //Dans le doc catégorie j'enregistre que l'id du produit, comme ça quand je charge la catégorie je connais la liste des produits de cette catégorie ?
    //Important d'initialiser pour mongoDB pour supposer qu'elle est vide ici
    @ToString.Exclude private Collection<Product> products = new ArrayList<>();

}
