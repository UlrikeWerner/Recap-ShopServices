import Entity.OrderStatus;
import Entity.Product;
import Repo.OrderListRepo;
import Repo.OrderRepo;
import Repo.ProductRepo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepo productRepo = new ProductRepo();
        productRepo.addProduct(new Product("4", "Kiwi"));
        OrderRepo orderRepo = new OrderListRepo();
        ShopService shopService = new ShopService(productRepo, orderRepo);

        shopService.addOrder(List.of("1", "4"));
        shopService.addOrder(List.of("4"));
        shopService.addOrder(List.of("3", "2", "4"));
        System.out.println(shopService.getListWithOrderStatus(OrderStatus.PROCESSING));
    }
}
