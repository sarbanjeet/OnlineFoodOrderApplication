package edu.tus.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackUrlController {
    @GetMapping("/FoodApplicationFallBack")
    public String foodApplicationFallBack()
    {
        return "Food order application is down, please check it and try again later.";
    }

    @GetMapping("/RestaurantApplicationFallBack")
    public String restaurantApplicationFallBack()
    {
        return "Restaurant management is down, please check it and try again later.";
    }

}
