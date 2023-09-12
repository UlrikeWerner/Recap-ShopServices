package Repo;

import Entity.Product;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        expected.add(new Product("2", "Banane"));
        expected.add(new Product("3", "Kirsche"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        ProductRepo repo = new ProductRepo();

        Product expected = new Product("1", "Apfel");
        if(repo.getProductById("1").isPresent()){
            Product actual = repo.getProductById("1").get();
            Assertions.assertEquals(actual, expected);
        } else {
            Assertions.fail();
        }
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        //WHEN
        Product actual = repo.addProduct(newProduct);

        //THEN
        Product expected = new Product("2", "Banane");
        assertEquals(actual, expected);
        if(repo.getProductById("2").isPresent()){
            assertEquals(repo.getProductById("2").get(), expected);
        }

    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");

        //THEN
        assertEquals(Optional.empty(), repo.getProductById("1"));
    }
}
