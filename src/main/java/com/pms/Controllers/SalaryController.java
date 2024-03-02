package com.pms.Controllers;

import com.pms.Entities.Salary;
import com.pms.Repositories.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Salary")
public class SalaryController {
    @Autowired
    private SalaryRepo salaryRepo;

    @PostMapping("/SaveSalary")
    public ResponseEntity<?> saveSalary(@RequestBody Salary salary)
    {
        return new ResponseEntity<>(salaryRepo.save(salary), HttpStatus.OK);
    }

    @GetMapping("/GetAllSalary")
    public ResponseEntity<?> getAllSalaries()
    {
        return new ResponseEntity<>(salaryRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/GetEmpSalary/{empid}")
    public ResponseEntity<?> getEmpSalaries(@PathVariable String empid)
    {
        return new ResponseEntity<>(salaryRepo.findByEmpid(empid),HttpStatus.OK);
    }

}
