package by.overone.restaurant.entity;

import by.overone.restaurant.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_summ")
    private double total_summ;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    @JsonBackReference
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    public Receipt(Long id, double total_summ, OrderStatus status, List<Order> orders) {
        this.id = id;
        this.total_summ = total_summ;
        this.status = status;
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Double.compare(receipt.total_summ, total_summ) == 0 && id.equals(receipt.id) && status == receipt.status && orders.equals(receipt.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total_summ, status, orders);
    }
}