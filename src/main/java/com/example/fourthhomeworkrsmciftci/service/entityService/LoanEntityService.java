package com.example.fourthhomeworkrsmciftci.service.entityService;

import com.example.fourthhomeworkrsmciftci.dao.LoanDao;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanEntityService extends BaseEntityService<Loan, LoanDao> {

     public LoanEntityService(LoanDao dao){ super(dao); }

     public List<Loan> findLoanByLoanCreationDateBetweenAndLoanTypeIs(LocalDate earlierDate, LocalDate laterDate, LoanType loanType){
          return getDao().findLoansByLoanCreationDateBetween(earlierDate, laterDate);
     }

     public List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIs(Long id, BigDecimal unpaidLoanAmountGreaterThan, LoanType loanType){
          return getDao().findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIs(id,unpaidLoanAmountGreaterThan,loanType);
     }

     public List<Loan> findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndPaymentDueDateBeforeAndLoanTypeIs(Long id, BigDecimal unpaidLoanAmountGreaterThan, LocalDate localDateNow, LoanType loanType){
          return getDao().findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndPaymentDueDateBeforeAndLoanTypeIs(id,unpaidLoanAmountGreaterThan,localDateNow,loanType);
     }


     public List<Loan> calculateSumOfUnpaidLoansOfUser(Long id,BigDecimal bigDecimalZero, LoanType loanType){
          return getDao().findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIs(id,bigDecimalZero,loanType);
     }
     public List<Loan> calculateSumOfOverdueUnpaidLoansOfUser(Long id,BigDecimal bigDecimalZero, LoanType loanType,LocalDate now){
          return getDao().findLoansByCustomerIdAndUnpaidLoanAmountGreaterThanAndLoanTypeIsAndPaymentDueDateBefore(id,bigDecimalZero,loanType,now);
     }
}
