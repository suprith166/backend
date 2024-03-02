package com.pms.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Leaveapp
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveid;

    private String startdate;

    private String enddate;

    private String status;

    private String reason;

    private int days;

    private String leavetype;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="empid")
    private Employee employee;
}
