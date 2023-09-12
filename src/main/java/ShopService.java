import Entity.Order;
import Entity.OrderStatus;
import Entity.Product;
import Repo.OrderMapRepo;
import Repo.OrderRepo;
import Repo.ProductRepo;

import java.util.*;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            productToOrder.ifPresent(products::add);
            productToOrder.orElseThrow(() -> new NoSuchElementException("Product mit der Id: " + productId + " konnte nicht bestellt werden!"));

        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public Order changeOrderStatus(String id, OrderStatus orderStatus){
        Order orderToChange = orderRepo.getOrderById(id);
        Order newOrder = orderToChange.withOrderStatus(orderStatus);
        orderRepo.removeOrder(id);
        orderRepo.addOrder(newOrder);
        return orderRepo.getOrderById(id);
    }

    public List<Order> getListWithOrderStatus(OrderStatus orderStatus){
        return orderRepo.getOrders().stream().filter((order) -> order.orderStatus().equals(orderStatus)).toList();
    }
}
