package Repo;

import Entity.Order;
import Entity.OrderStatus;
import Entity.Product;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {

    @Test
    void getOrders() {
        OrderMapRepo repo = new OrderMapRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), ZonedDateTime.now(), OrderStatus.PROCESSING);
        repo.addOrder(newOrder);

        List<Order> actual = repo.getOrders();

        assertEquals(actual, List.of(newOrder));
    }

    @Test
    void getOrderById() {
        OrderMapRepo repo = new OrderMapRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), ZonedDateTime.now(), OrderStatus.PROCESSING);
        repo.addOrder(newOrder);

        Order actual = repo.getOrderById("1");

        assertEquals(actual, newOrder);
    }

    @Test
    void addOrder() {
        OrderMapRepo repo = new OrderMapRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), ZonedDateTime.now(), OrderStatus.PROCESSING);

        Order actual = repo.addOrder(newOrder);

        assertEquals(actual, newOrder);
        assertEquals(repo.getOrderById("1"), newOrder);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
