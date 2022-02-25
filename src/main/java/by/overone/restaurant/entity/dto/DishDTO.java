package by.overone.restaurant.entity.dto;

import by.overone.restaurant.entity.enums.MenuCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    private Long id;
    private String name;
    private String description;
    private MenuCategory category;
    private double price;
}
