package by.overone.restaurant.entity;

import by.overone.restaurant.entity.enums.MenuCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dishes")
    private Long id;

    @Column(name = "name_dish")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private MenuCategory category;

    @Column(name = "price")
    private double price;

    @ManyToMany
    @JoinTable(
            name = "dishes_has_orders"
            , joinColumns = @JoinColumn(name = "dishes_id_dishes")
            , inverseJoinColumns = @JoinColumn(name = "orders_id"))
    @JsonBackReference
    private List<Dish> orders = new ArrayList<>();

    public Dish(String name,
                String description,
                MenuCategory category,
                double price,
                List<Dish> orders) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.price, price) == 0 && Objects.equals(id, dish.id) && Objects.equals(name, dish.name) && Objects.equals(description, dish.description) && category == dish.category && Objects.equals(orders, dish.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, price, orders);
    }
}
