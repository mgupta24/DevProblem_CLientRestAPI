package com.mitali.devproblem.clientapi.annotation;

import com.mitali.devproblem.clientapi.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMobileNumberValidator implements ConstraintValidator<UniqueMobileNumber,String> {

    @Autowired
    private ClientService clientService;

    @Override
    public void initialize(UniqueMobileNumber constraintAnnotation) {
        //ToDo
    }

    @Override
    public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (clientService == null)
            return true;
        if(clientService.getClientByMobileNumber(mobileNumber)==null)
            return true;
        return false;
    }
}
