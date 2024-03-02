package com.pms.Controllers;

import com.pms.Entities.Employee;
import com.pms.Entities.Leaveapp;
import com.pms.Repositories.EmployeeRepo;
import com.pms.Repositories.LeaveAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Leave")
public class LeaveAppController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private LeaveAppRepo leaveAppRepo;

    @PostMapping("/RequestLeave/{EmpId}")
    public ResponseEntity<?> requestLeave(@RequestBody Leaveapp leaveApp, @PathVariable String EmpId)
    {
        Employee employee = employeeRepo.findById(EmpId).orElseThrow(()->new RuntimeException("Employee not found"));
        leaveApp.setEmployee(employee);
        leaveApp.setStatus("Pending");
        leaveAppRepo.save(leaveApp);
        return new ResponseEntity<>("Leave requested successfully", HttpStatus.OK);
    }

    @PutMapping("/UpdateLeave/{leaveId}")
    public ResponseEntity<?> updateLeave(@RequestBody Leaveapp obj, @PathVariable Integer leaveId)
    {
        Leaveapp existingleaveApp = leaveAppRepo.findById(leaveId).orElseThrow(()->new RuntimeException("Leave application not found"));
        if(obj.getStatus().equals("Approved"))
        {
            Employee emp = existingleaveApp.getEmployee();
            emp.setNo_of_leaves(emp.getNo_of_leaves()-existingleaveApp.getDays());
            employeeRepo.save(emp);
        }
        existingleaveApp.setStatus(obj.getStatus());
        leaveAppRepo.save(existingleaveApp);
        return new ResponseEntity<>("Leave requested " + obj.getStatus()+ " successfully", HttpStatus.OK);
    }

    @GetMapping("/GetLeaves")
    public ResponseEntity<?> getLeaves()
    {

        return new ResponseEntity<>(leaveAppRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/GetLeavesByEmp/{EmpId}")
    public ResponseEntity<?> getLeavesByEmp(@PathVariable String EmpId)
    {
        Employee employee = employeeRepo.findById(EmpId).orElseThrow(()->new RuntimeException("Employee not found"));
        return new ResponseEntity<>(leaveAppRepo.findByEmployee(employee), HttpStatus.OK);
    }

    @GetMapping("/GetLeaveByEmpAndMonth/{month}/{year}/{empid}")
    public ResponseEntity<String> getLeavesByEmpandMonth(@PathVariable Integer month, @PathVariable Integer year, @PathVariable String empid)
    {
        String count = leaveAppRepo.getCountByMonthAndYear(month,year,empid);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
