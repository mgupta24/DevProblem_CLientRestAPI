package com.mitali.devproblem.clientapi.annotation;

import com.mitali.devproblem.clientapi.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueIdValidator implements ConstraintValidator<UniqueId,String> {

    @Autowired
    private ClientService clientService;

    @Override
    public void initialize(UniqueId constraintAnnotation) {
        //ToDo
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        if (clientService == null)
                return true;
        return clientService.getClientById(id)==null;
    }
}
