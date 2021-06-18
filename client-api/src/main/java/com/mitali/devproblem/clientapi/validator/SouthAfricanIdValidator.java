package com.mitali.devproblem.clientapi.validator;

import com.mitali.devproblem.clientapi.exceptions.InvalidIdException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class SouthAfricanIdValidator implements ConstraintValidator<SouthAfricanId,String> {

    private Year pivotYear;

    @Override
    public void initialize(SouthAfricanId constraintAnnotation) {
        //ToDo
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        if(id!=null) {

            try {
                //ID Length Validation
                if (id.length() != 13)
                    throw new InvalidIdException("Invalid Id: Length must be 13 digits.");

                //DOB Validation
                String birthDate = id.substring(0, 6);

                if (pivotYear == null) {
                    pivotYear = Year.of(Year.now().getValue() - 100);
                }

                int year = Year.parse(birthDate.substring(0, 2), new DateTimeFormatterBuilder().appendValueReduced(ChronoField.YEAR, 2, 2, pivotYear.getValue())
                        .toFormatter()).getValue();

                LocalDate dateOfBirth = LocalDate.of(year, Month.of(Integer.parseInt(birthDate.substring(2, 4))),
                        Integer.parseInt(birthDate.substring(4)));

                //GenderNumber
                int genderNum = Integer.parseInt(id.substring(6, 10));

                //Citizenship Number Validation
                int citizenshipNum = Integer.parseInt(id.substring(10, 11));
                if ((citizenshipNum != 0) && (citizenshipNum != 1))
                    throw new InvalidIdException("Citizenship Number must be either 0 or 1");

                //CheckSum Validation
                int checkBit = Integer.parseInt(id.substring(12, 13));
                if (!(checkBit == this.getCheckSumDigit(id)))
                    throw new InvalidIdException("Invalid Id: Wrong Checksum");
            }
            catch (InvalidIdException ex)
            {
                throw new InvalidIdException(ex.getMessage());
            }
            catch (NumberFormatException | DateTimeParseException ex )
            {
                throw new InvalidIdException("Invalid Id: Wrong Format "+ ex.getMessage());
            }
            catch (Exception ex)
            {
                throw new InvalidIdException("Invalid Id: "+ ex.getMessage());
            }

        }
            return true;
    }

    private int getCheckSumDigit(String id) {

        String idDigits = id.substring(0, id.length() - 1);
        int length = idDigits.length();
        int sum = 0;
        int weight = 2;
        char[] numberArray = idDigits.toCharArray();

        for (int i = length - 1; i >= 0; i--) {
            int digit = weight * (numberArray[i] - '0');
            sum += Math.floor(digit / 10) + digit % 10;
            weight = weight % 2 + 1;
        }

        return (10 - sum % 10) % 10;
    }

}
