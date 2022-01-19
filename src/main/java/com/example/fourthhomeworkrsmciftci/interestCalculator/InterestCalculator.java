package com.example.fourthhomeworkrsmciftci.interestCalculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class InterestCalculator {

    public InterestCalculator() {
    }

    public static BigDecimal calculateInterest(LocalDate dueDate, LocalDate now, BigDecimal principleLoan){

        BigDecimal interest = BigDecimal.ZERO;
        BigDecimal interestRateBefore = BigDecimal.valueOf(1.5);
        BigDecimal interestRateAfter = BigDecimal.valueOf(2);
        LocalDate dateChangesInterestRate = LocalDate.of(2018,1,1);


        if(dueDate.isBefore(now)){
            if(dueDate.isBefore(dateChangesInterestRate)){


                BigDecimal numberOfDaysBeforeDateChangesInterestRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dueDate,dateChangesInterestRate));
                BigDecimal numberOfDaysAfterDateChangesInterestRate = BigDecimal.valueOf(ChronoUnit.DAYS.between(dateChangesInterestRate,now));

                BigDecimal interestBeforeInterestRateChange = principleLoan.multiply(interestRateBefore)
                        .multiply(numberOfDaysBeforeDateChangesInterestRate).divide(BigDecimal.valueOf(36000), MathContext.DECIMAL32);


                BigDecimal interestAfterInterestRateChange = principleLoan.multiply(interestRateAfter)
                        .multiply(numberOfDaysAfterDateChangesInterestRate).divide(BigDecimal.valueOf(36000), MathContext.DECIMAL32);

                interest = interestBeforeInterestRateChange.add(interestAfterInterestRateChange);


            }else{
                BigDecimal numberOfDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(dueDate,now));

                interest = principleLoan.multiply(interestRateAfter).multiply(numberOfDays).divide(BigDecimal.valueOf(36000), MathContext.DECIMAL32);

            }

            if(interest.compareTo(BigDecimal.ONE) < 0){
                interest = BigDecimal.ONE;
            }

        }

        return interest.setScale(2, RoundingMode.HALF_EVEN);

    }
}
