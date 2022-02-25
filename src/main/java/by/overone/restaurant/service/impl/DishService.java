package by.overone.restaurant.service.impl;

import by.overone.restaurant.entity.Dish;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.repository.DishRepository;
import by.overone.restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DishService implements IService<Dish, Long> {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public List<Dish> findAll() {
        List<Dish> list = dishRepository.findAll();
        return dishRepository.findAll();
    }

    @Override
    public Dish findById(Long id) {
        Optional<Dish> optionalTrack = dishRepository.findById(id);
        if (optionalTrack.isEmpty()) {
            throw new NoSuchRestaurantException("There is no dish with ID = " + id + " in database");
        }
        return optionalTrack.get();
    }

    @Override
    public void create(Dish entity) {
        dishRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
