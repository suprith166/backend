package com.pms.Repositories;

import com.pms.Entities.Employee;
import com.pms.Entities.Leaveapp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveAppRepo extends JpaRepository<Leaveapp, Integer> {

    List<Leaveapp> findByEmployee(Employee employee);

    @Query(value = "SELECT SUM(e.days) AS LEAVES FROM Leaveapp e WHERE YEAR(e.startdate) = :year AND MONTH(e.startdate) = :month " +
            "AND YEAR(e.enddate) = :year AND MONTH(e.enddate) = :month AND e.empid=:empid ", nativeQuery = true)
    String getCountByMonthAndYear(@Param("month") int month, @Param("year") int year, @Param("empid") String empid);
}
