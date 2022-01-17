package com.example.fourthhomeworkrsmciftci.service.entityService;

import com.example.fourthhomeworkrsmciftci.dao.LoanDao;
import com.example.fourthhomeworkrsmciftci.entity.Loan;
import com.example.fourthhomeworkrsmciftci.enums.LoanType;
import com.example.fourthhomeworkrsmciftci.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanEntityService extends BaseEntityService<Loan, LoanDao> {
     public LoanEntityService(LoanDao dao){ super(dao); }
     public List<Loan> findLoanByLoanCreationDateBetweenAndLoanTypeIs(LocalDate earlierDate, LocalDate laterDate, LoanType loanType){
          return getDao().findLoanByLoanCreationDateBetweenAndLoanTypeIs(earlierDate, laterDate, loanType);
     }

}
