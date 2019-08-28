package org.sid;

import net.bytebuddy.utility.RandomString;
import org.sid.dao.CategoryRepository;
import org.sid.dao.ProductRepository;
import org.sid.entities.Category;
import org.sid.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class CatalogueServiceApplication implements CommandLineRunner{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public CatalogueServiceApplication(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            RepositoryRestConfiguration repositoryRestConfiguration) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.repositoryRestConfiguration = repositoryRestConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repositoryRestConfiguration.exposeIdsFor(Product.class);

        categoryRepository.save(
                new Category(null, "Computers",
                null, null, null));
        categoryRepository.save(
                new Category(null, "Printers",
                        null, null, null));
        categoryRepository.save(
                new Category(null, "Smartphones",
                        null, null, null));


        Random rnd = new Random();
        categoryRepository.findAll().forEach(category -> {
            for (int i = 0; i < 10; i++){
                Product p = new Product();
                p.setName(RandomString.make(18));
                //mini 100 max 10 000
                p.setCurrentPrice(100 + rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setPhotoName("unknown.png");
                p.setCategory(category);
                productRepository.save(p);
            }
        });

    }

    /*@Bean
    CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            categoryRepository.deleteAll();
            productRepository.deleteAll();
            Stream.of("C1 Ordinateurs", "C2 Imprimantes").forEach(c -> {
                System.out.println(c.substring(3));
                categoryRepository.save(new Category(c.split(" ")[0], c.substring(3), null, new ArrayList<>()));
            });
            categoryRepository.findAll().forEach(System.out::println);


            productRepository.deleteAll();

            Category c1 = categoryRepository.findById("C1").get();
            Stream.of("P1", "P2", "P3", "P4").forEach(name -> {

                Product p = productRepository.save(new Product(null, name, Math.random()*1000, null, c1));
                c1.getProducts().add(p);
                categoryRepository.save(c1);

            });

            Category c2 = categoryRepository.findById("C2").get();
            Stream.of("P5", "P6").forEach(name -> {
                Product p = productRepository.save(new Product(null, name, Math.random()*1000, null, c2));
                c2.getProducts().add(p);
                categoryRepository.save(c2);
            });

            productRepository.findAll().forEach(System.out::println);





        };

    }*/

}
