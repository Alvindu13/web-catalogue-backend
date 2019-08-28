package org.sid.web;

import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {

    private ProductRepository productRepository;

    public CatalogueRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/products/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id ) throws Exception {
        Product p = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/appImg/products/" + p.getPhotoName()));
    }


}
