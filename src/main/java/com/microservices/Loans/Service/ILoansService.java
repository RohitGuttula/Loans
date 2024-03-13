package com.microservices.Loans.Service;


import com.microservices.Loans.DTO.LoansDTO;

public interface ILoansService {


    void createLoans(String mobileNumber);

    LoansDTO fetchLoanDetails(String mobileNumber);

    boolean updateLoans(LoansDTO loansDTO);

    boolean deleteLoans(String mobileNumber);
}
