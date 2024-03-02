package com.pms.Repositories;


import com.pms.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, String> {

    boolean existsByEmail(String email);

    boolean existsByMobile(Long mobile);

}
