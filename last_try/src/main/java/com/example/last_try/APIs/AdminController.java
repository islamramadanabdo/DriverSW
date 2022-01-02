package com.example.last_try.APIs;
import com.example.last_try.Models.*;
import com.example.last_try.Database.*;

import com.example.last_try.Services.AdminServices;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/v1/admin")
@RestController

public class AdminController {

    private final AdminServices  admin_services = new AdminServices();

    @PostMapping(path = "pending")
    public database.database_response PendingDriversUsers(@RequestBody User user) {
            return admin_services.PendingDriversUsers(user);
    }

    @PostMapping(path = "Verifypending")
    public  database.database_response  VerifyPendingDriver(@RequestBody User user){
            return admin_services.VerifyPendingDriver(user);
    }

    @GetMapping(path = "listAllPendingDrivers")
    public ArrayList<Driver> listAllPendingDrivers(){
            return admin_services.listAllPendingDrivers();
    }

    @GetMapping(path = "listDriverUsers")
    public ArrayList<Driver> listDriverUsers(){
            return admin_services.listDriverUsers();
    }

    @PostMapping(path = "suspend")
    public database.database_response suspend(@RequestBody User user) {
        return admin_services.PendingDriversUsers(user);
    }
}
