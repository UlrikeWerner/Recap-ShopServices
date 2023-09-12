import Entity.Order;
import Entity.OrderStatus;
import Entity.Product;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), ZonedDateTime.now(), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_throwsNoSuchElementException() {
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "5");

        assertThrows(NoSuchElementException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void changeOrderStatus_shouldGiveSameOrder_withNewOrderStatus(){
        ShopService shopService = new ShopService();
        Order order1 = shopService.addOrder(List.of("1", "3"));
        Order newOrder1 = shopService.updateOrderStatus(order1.id(), OrderStatus.IN_DELIVERY);

        assertNotEquals(order1, newOrder1);
        assertEquals(order1.id(), newOrder1.id());
        assertEquals(order1.products(), newOrder1.products());
        assertEquals(OrderStatus.IN_DELIVERY, newOrder1.orderStatus());
    }

    @Test
    void getListWithOrderStatus_shouldReturnEmptyList_whenOrderStatusNotFit() {

        ShopService shopService = new ShopService();
        List<Order> newOrderList = shopService.getListWithOrderStatus(OrderStatus.IN_DELIVERY);

        assertEquals(List.of(), newOrderList);
    }

    @Test
    void getListWithOrderStatus_shouldReturnList_withRightOrderStatus() {

        ShopService shopService = new ShopService();
        Order order1 = shopService.addOrder(List.of("1", "3"));
        Order order2 = shopService.addOrder(List.of("1", "2"));
        Order order3 = shopService.addOrder(List.of("2", "3"));
        Order order4 = shopService.addOrder(List.of("3", "1"));

        Order newOrder3 = shopService.updateOrderStatus(order3.id(), OrderStatus.IN_DELIVERY);
        Order newOrder1 = shopService.updateOrderStatus(order1.id(), OrderStatus.IN_DELIVERY);

        List<Order> newOrderList = shopService.getListWithOrderStatus(OrderStatus.IN_DELIVERY);

        assertEquals(2, newOrderList.size());
        assertTrue(newOrderList.contains(newOrder1));
        assertTrue(newOrderList.contains(newOrder3));
        assertEquals(order1.id(), newOrder1.id());
        assertEquals(order3.id(), newOrder3.id());
    }
}
