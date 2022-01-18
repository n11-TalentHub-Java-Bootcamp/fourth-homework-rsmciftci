package com.example.fourthhomeworkrsmciftci.interestCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InterestCalculator {

    public InterestCalculator() {
    }

    public BigDecimal calculateInterest(LocalDate dueDate, LocalDate now, BigDecimal principleLoan){

        BigDecimal interest = BigDecimal.ZERO;
        BigDecimal interestRateBefore = BigDecimal.valueOf(1.5);
        BigDecimal interestRateAfter = BigDecimal.valueOf(2);
        LocalDate dateChangesInterestRate = LocalDate.of(2018,01,01);


        if(dueDate.isBefore(now)){
            if(dueDate.isBefore(dateChangesInterestRate)){


                BigDecimal numberOfDaysBeforeDateChangesInterestRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dueDate,dateChangesInterestRate));
                BigDecimal numberOfDaysAfterDateChangesInterestRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dateChangesInterestRate,now));

                BigDecimal interestBeforeInterestRateChange = principleLoan.multiply(interestRateBefore)
                        .multiply(numberOfDaysBeforeDateChangesInterestRate).divide(BigDecimal.valueOf(36000));

                BigDecimal interestAfterInterestRateChange = principleLoan.multiply(interestRateAfter)
                        .multiply(numberOfDaysAfterDateChangesInterestRate).divide(BigDecimal.valueOf(36000));

                interest = interestBeforeInterestRateChange.add(interestAfterInterestRateChange);

            }else{
                BigDecimal numberOfDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(dueDate,now));

                interest = principleLoan.multiply(interestRateAfter).multiply(numberOfDays).divide(BigDecimal.valueOf(36000));
            }

            if(interest.compareTo(BigDecimal.ONE) < 0){
                interest = BigDecimal.ONE;
            }

        }

        return interest;
    }
}
