package com.pms.Repositories;

import com.pms.Entities.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepo extends JpaRepository<Salary, Integer> {

    List<Salary> findByEmpid(String empid);
}
