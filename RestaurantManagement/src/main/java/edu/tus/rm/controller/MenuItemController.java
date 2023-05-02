package edu.tus.rm.controller;

import edu.tus.rm.entity.MenuItem;
import edu.tus.rm.entity.Restaurant;
import edu.tus.rm.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("")
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getOrderById(@PathVariable(value = "id") Long menuItemId) throws EntityNotFoundException {
        MenuItem menuItem = menuItemService.getById(menuItemId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for this id :: " + menuItemId));

        return ResponseEntity.ok().body(menuItem);
    }

    @PostMapping("")
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem) {
        return menuItemService.createMenuItem(menuItem);
    }

}
