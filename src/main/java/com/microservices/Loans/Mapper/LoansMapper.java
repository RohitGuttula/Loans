package com.microservices.Loans.Mapper;

import com.microservices.Loans.DTO.LoansDTO;
import com.microservices.Loans.Entity.Loans;

public class LoansMapper {
    public static Loans mapToLoans(LoansDTO loansDTO, Loans loans){
        loans.setLoanNumber(loansDTO.getLoanNumber());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setMobileNumber(loansDTO.getMobileNumber());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setAmountPaid(loansDTO.getAmountPaid());
        loans.setOutstandingAmount(loansDTO.getOutStandingAmount());
        return loans;
    }
    public static LoansDTO mapToLoansDTO(Loans loans, LoansDTO loansDTO){
        loansDTO.setLoanNumber(loans.getLoanNumber());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setMobileNumber(loans.getMobileNumber());
        loansDTO.setTotalLoan(loans.getTotalLoan());
        loansDTO.setAmountPaid(loans.getAmountPaid());
        loansDTO.setOutStandingAmount(loans.getOutstandingAmount());
        return loansDTO;
    }
}
