package com.mitali.devproblem.clientapi.annotation;

import com.mitali.devproblem.clientapi.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SouthAfricanIdValidator implements ConstraintValidator<SouthAfricanId,String> {

    @Override
    public void initialize(SouthAfricanId constraintAnnotation) {
        //ToDo
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        if(id!=null)
        return id.matches("\\d{13}");
        else
            return true;
    }
}
