package com.example.last_try.APIs;

import com.example.last_try.Models.offer;
import com.example.last_try.Services.DriverServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/driver")
@RestController
public class DriverController
{
    private final DriverServices driver_services;

    @Autowired
    public DriverController(DriverServices driver_services) {
        this.driver_services = driver_services;
    }

    @PostMapping(path="make_offer")
    public void make_offer(@RequestBody offer new_offer)
    {
        driver_services.make_offer(new_offer);
    }
}
