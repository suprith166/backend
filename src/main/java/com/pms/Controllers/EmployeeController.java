package com.pms.Controllers;

import com.pms.Entities.Admin;
import com.pms.Entities.Employee;
import com.pms.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Emp")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping("/AddEmp")
    public ResponseEntity<?> addEmployees(@RequestBody Employee obj)
    {
        if(employeeRepo.existsById(obj.getEmpid()))
        {
            throw new RuntimeException("Employee Id already exists");
        }
        if(employeeRepo.existsByEmail(obj.getEmail()))
        {
            throw new RuntimeException("Email id already registered");
        }
        if(employeeRepo.existsByMobile(obj.getMobile()))
        {
            throw new RuntimeException("Mobile number already exists");
        }
        employeeRepo.save(obj);
        return new ResponseEntity<>("Employee added successfully", HttpStatus.OK);
    }

    @GetMapping("/GetAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
        List<Employee> employee = employeeRepo.findAll();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteEmp/{EmpId}")
   public ResponseEntity<?> deleteEmp(@PathVariable String EmpId)
    {
        Employee employee = employeeRepo.findById(EmpId).orElseThrow(()->new RuntimeException("Employee not found"));
        employeeRepo.delete(employee);

        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/Changepass/{empid}")
    public ResponseEntity<?> changePass(@PathVariable String empid, @RequestBody Employee emp)
    {
        Employee existingEmp = employeeRepo.findById(empid).orElseThrow(()->new RuntimeException("Employee not found"));
        existingEmp.setPassword(emp.getPassword());
        employeeRepo.save(existingEmp);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @GetMapping("/GetSingleEmp/{empid}")
    public ResponseEntity<?> getSingleEmp(@PathVariable String empid)
    {
        return new ResponseEntity<>(employeeRepo.findById(empid),HttpStatus.OK);
    }
}
