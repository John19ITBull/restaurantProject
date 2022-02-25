package by.overone.restaurant.service.impl;

import by.overone.restaurant.entity.Dish;
import by.overone.restaurant.entity.Order;
import by.overone.restaurant.entity.User;
import by.overone.restaurant.entity.dto.DishDTO;
import by.overone.restaurant.entity.enums.OrderStatus;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.repository.OrderRepository;
import by.overone.restaurant.repository.UserRepository;
import by.overone.restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IService<Order, Long> {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishMappingUtils dishMappingUtils;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> optionalTrack = orderRepository.findById(id);
        if (optionalTrack.isEmpty()) {
            throw new NoSuchRestaurantException("There is no order with ID = " + id + " in database");
        }
        return optionalTrack.get();
    }

    @Override
    public void create(Order entity) {
        orderRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }


    public Long formAnOrder(String username, List<DishDTO> dishesDTOOfBasket) {
        List<Dish> dishes = dishesDTOOfBasket
                .stream().map(e -> dishMappingUtils.mapToEntity(e))
                .collect(Collectors.toList());

        User user = userRepository.findByUsername(username).get();
        Order order = new Order(
                LocalDateTime.now(),
                LocalDateTime.now(),
                totalPrice(dishes),
                OrderStatus.VERIFICATION,
                user,
                dishes
        );

        Order returnedOrder = orderRepository.save(order);

        if (returnedOrder != null) {
            return returnedOrder.getId();
        } else {
            System.out.println("Something wrong");
            return 0L;
        }
    }

    public Double totalPrice(List<Dish> dishes) {
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        dishes.forEach(dish -> total.set(total.get() + dish.getPrice()));
        return total.get();
    }

    public void paidOrder(Long orderId, String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new NoSuchRestaurantException("There is no user with ID = " + orderId + " in database");
        }
        User user = userOptional.get();
        Order order = orderRepository.getById(orderId);

        if (user.getBalance() >= order.getAmount()) {
            user.setBalance(user.getBalance() - order.getAmount());
            order.setStatus(OrderStatus.PAID);
            userRepository.save(user);
            orderRepository.save(order);
        }else {
            System.out.println("NET BABLA");
        }
    }
}
