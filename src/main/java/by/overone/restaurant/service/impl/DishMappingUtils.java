package by.overone.restaurant.service.impl;

import by.overone.restaurant.entity.Dish;
import by.overone.restaurant.entity.dto.DishDTO;
import by.overone.restaurant.service.impl.DishService;
import by.overone.restaurant.service.IMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishMappingUtils implements IMappingUtils<DishDTO, Dish> {

    @Autowired
    private DishService dishService;

    @Override
    public DishDTO mapToDto(Dish entity){
        DishDTO dto = new DishDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    @Override
    public Dish mapToEntity(DishDTO dto){
        return dishService.findById(dto.getId());
    }
}
