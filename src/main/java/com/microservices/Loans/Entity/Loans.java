package com.microservices.Loans.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Loans extends BaseEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long loanId;

     private String mobileNumber;

     private String loanNumber;
     private String loanType;
     private int totalLoan;
     private int amountPaid;
     private int OutstandingAmount;

}
