package com.microservices.Loans.Service.IMPL;

import com.microservices.Loans.Constants.LoansConstants;
import com.microservices.Loans.DTO.LoansDTO;
import com.microservices.Loans.Entity.Loans;
import com.microservices.Loans.Exception.LoanAlreadyExistsException;
import com.microservices.Loans.Exception.ResourceNotFoundException;
import com.microservices.Loans.Mapper.LoansMapper;
import com.microservices.Loans.Repository.LoansRepository;
import com.microservices.Loans.Service.ILoansService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Slf4j
public class LoanServiceIMPL implements ILoansService  {

    private LoansRepository loansRepository;


    @Override
    public void createLoans(String mobileNumber) {
        Optional<Loans> optionalLoans=loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException
                    ("Loan is already exists for given mobile Number"+mobileNumber);
        }
        Loans loansEntity=createNewLoan(mobileNumber);
       loansRepository.save(loansEntity);
    }

    @Override
    public LoansDTO fetchLoanDetails(String mobileNumber) {
        Loans optionalLoans=loansRepository.
                findByMobileNumber(mobileNumber).orElseThrow(
                        ()->new ResourceNotFoundException("Loans","MobileNumber",mobileNumber)
        );
        return LoansMapper.mapToLoansDTO(optionalLoans,new LoansDTO());
    }

    @Override
    public boolean updateLoans(LoansDTO loansDTO) {
        log.info(loansDTO.getLoanNumber());
        Loans loans=loansRepository.findByLoanNumber(loansDTO.getLoanNumber()).orElseThrow(
                ()->new ResourceNotFoundException("Loans","Loan Number", loansDTO.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDTO,loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoans(String mobileNumber) {
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loans","Mobile Number",mobileNumber)
        );
        if(loans!=null){
            loansRepository.deleteById(loans.getLoanId());
        }
        return true;
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}
