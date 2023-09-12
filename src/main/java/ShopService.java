import Entity.Order;
import Entity.Product;
import Repo.OrderMapRepo;
import Repo.OrderRepo;
import Repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static Entity.OrderStatus.PROCESSING;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, PROCESSING);

        return orderRepo.addOrder(newOrder);
    }
}
