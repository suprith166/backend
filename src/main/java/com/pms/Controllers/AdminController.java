package com.pms.Controllers;

import com.pms.Entities.Admin;
import com.pms.Repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminRepo adminRepo;

    @PutMapping("/Changepass/{adminid}")
    public ResponseEntity<?> changePass(@PathVariable String adminid, @RequestBody Admin admin)
    {
        Admin existingAdmin = adminRepo.findById(adminid).orElseThrow(()->new RuntimeException("Admin not found"));
        existingAdmin.setPassword(admin.getPassword());
        adminRepo.save(existingAdmin);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @GetMapping("/GetAdmins")
    public ResponseEntity<?> getAdmins()
    {
        return new ResponseEntity<>(adminRepo.findAll(),HttpStatus.OK);
    }

    @PostMapping("/AddAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody Admin obj)
    {
        if(adminRepo.existsById(obj.getAdminid()))
        {
            throw new RuntimeException("User id already exists");
        }
        adminRepo.save(obj);
        return new ResponseEntity<>("Admin added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/DeleteAdmin/{adminid}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String adminid)
    {
        Admin admin = adminRepo.findById(adminid).orElseThrow(()->new RuntimeException("Admin not found"));
        adminRepo.delete(admin);
        return new ResponseEntity<>("Admin deleted successfully", HttpStatus.OK);
    }
}
