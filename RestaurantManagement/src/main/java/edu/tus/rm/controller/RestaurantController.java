package edu.tus.rm.controller;

import edu.tus.rm.entity.MenuItem;
import edu.tus.rm.entity.Restaurant;
import edu.tus.rm.service.MenuItemService;
import edu.tus.rm.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getOrderById(@PathVariable(value = "id") Long restaurantId) throws EntityNotFoundException {
        Restaurant restaurant = restaurantService.getById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for this id :: " + restaurantId));

        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping("/{id}/menu-items/{menuId}")
    public ResponseEntity<Restaurant> getOrderById(@PathVariable(value = "id") Long restaurantId, @PathVariable("menuId") Long menuId) throws EntityNotFoundException {
        Optional<Restaurant> restaurant = restaurantService.getById(restaurantId);

        if (restaurant.isPresent()) {
            Optional<MenuItem> item = menuItemService.getById(menuId);

            if (item.isPresent()) {
                ArrayList<MenuItem> items = new ArrayList<MenuItem>();
                items.add(item.get());
                restaurant.get().setMenuItems(items);
                return ResponseEntity.ok().body(restaurant.get());
            }
        }
        return  ResponseEntity.ok().body(null);
    }

    @PostMapping("")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        final Restaurant orderObj = restaurant;
        orderObj.getMenuItems().forEach(orderItem -> orderItem.setRestaurant(orderObj));
        return restaurantService.createRestaurant(restaurant);
    }



}

