package com.pms.Controllers;

import com.pms.Dto.LoginDto;
import com.pms.Entities.Admin;
import com.pms.Entities.Employee;
import com.pms.Repositories.AdminRepo;
import com.pms.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Auth")
public class AuthController
{

     @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AdminRepo adminRepo;


    @PostMapping("/LoginVerify")
    public ResponseEntity<?> LoginVerify(@RequestBody LoginDto obj)
    {
        if(obj.getUsertype().equals("Admin"))
        {
            Admin admin = adminRepo.findById(obj.getUserid()).orElseThrow(()->new RuntimeException("Admin not found"));
            if(admin.getPassword().equals(obj.getPassword()))
            {
                return new ResponseEntity<>(admin, HttpStatus.OK);
            }
            else {
                throw new RuntimeException("Invalid password");
            }
        }
        else {
            Employee user = employeeRepo.findById(obj.getUserid()).orElseThrow(()->new RuntimeException("Employee not found"));
            if(user.getPassword().equals(obj.getPassword()))
            {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            else {
                throw new RuntimeException("Invalid password");
            }
        }

    }
}
