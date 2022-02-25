package by.overone.restaurant.repository;

import by.overone.restaurant.entity.Dish;
import org.apache.logging.log4j.message.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Page<Message> findByTag(Pageable pageable);
}
