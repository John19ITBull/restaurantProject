package by.overone.restaurant.entity;

import by.overone.restaurant.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "users_id")
    @JsonManagedReference
    @ToString.Exclude
    private User user;

    @ManyToMany
    @JoinTable(
            name = "dishes_has_orders"
            , joinColumns = @JoinColumn(name = "orders_id")
            , inverseJoinColumns = @JoinColumn(name = "dishes_id_dishes"))
    @JsonManagedReference
    @ToString.Exclude
    private List<Dish> dishes = new ArrayList<>();

    public Order(LocalDateTime orderTime,
                 LocalDateTime paymentTime,
                 double amount,
                 OrderStatus status,
                 User user,
                 List<Dish> dishes) {
        this.amount = amount;
        this.status = status;
        this.user = user;
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.amount, amount) == 0 && Objects.equals(id, order.id) && status == order.status && Objects.equals(user, order.user) && Objects.equals(dishes, order.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, status, user, dishes);
    }
}
