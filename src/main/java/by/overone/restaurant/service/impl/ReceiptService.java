package by.overone.restaurant.service.impl;

import by.overone.restaurant.entity.Dish;
import by.overone.restaurant.entity.Order;
import by.overone.restaurant.entity.Receipt;
import by.overone.restaurant.exception.NoSuchRestaurantException;
import by.overone.restaurant.repository.OrderRepository;
import by.overone.restaurant.repository.ReceiptRepository;
import by.overone.restaurant.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReceiptService implements IService<Receipt, Long> {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public List<Receipt> findAll() {
        return receiptRepository.findAll();
    }

    @Override
    public Receipt findById(Long id) {
        Optional<Receipt> optionalTrack = receiptRepository.findById(id);
        if (optionalTrack.isEmpty()) {
            throw new NoSuchRestaurantException("There is no order with ID = " + id + " in database");
        }
        return optionalTrack.get();
    }


    @Override
    public void create(Receipt entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
