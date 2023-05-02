package edu.tus.rm.service;

import edu.tus.rm.entity.MenuItem;
import edu.tus.rm.entity.Restaurant;
import edu.tus.rm.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> findAll() {
      return   menuItemRepository.findAll();
    }

    public Optional<MenuItem> getById(Long restaurantId) {
        return menuItemRepository.findById(restaurantId);
    }
}
