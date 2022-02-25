package by.overone.restaurant.entity;


import by.overone.restaurant.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "balance")
    private double balance;

    @Column(name = "enabled")
    private Integer enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    @JsonManagedReference
    @ToString.Exclude
    private Detail detail;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    public User(String username,
                String password,
                Role role,
                double balance,
                Integer enabled,
                Detail detail,
                List<Order> orders) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.enabled = enabled;
        this.detail = detail;
        this.orders = orders;
    }

    public User(String username,
                String password,
                Role role,
                double balance,
                Integer enabled,
                Detail detail) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.enabled = enabled;
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role && Objects.equals(enabled, user.enabled) && Objects.equals(detail, user.detail) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role, balance, enabled, detail, orders);
    }
}
