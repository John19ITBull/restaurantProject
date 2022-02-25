package by.overone.restaurant.service.impl;

import by.overone.restaurant.entity.Detail;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.repository.DetailRepository;
import by.overone.restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetailService implements IService<Detail, Long> {

    @Autowired
    private DetailRepository detailRepository;

    @Override
    public List<Detail> findAll() {
        return detailRepository.findAll();
    }

    @Override
    public Detail findById(Long id) {
        Optional<Detail> optionalTrack = detailRepository.findById(id);
        if (optionalTrack.isEmpty()) {
            throw new NoSuchRestaurantException("There is no detail with ID = " + id + " in database");
        }
        return optionalTrack.get();
    }

    @Override
    public void create(Detail entity) {
        detailRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        detailRepository.deleteById(id);
    }

    public Detail save(Detail entity) {
       return detailRepository.save(entity);
    }
}
