package com.mitali.devproblem.clientapi.client;

import com.mitali.devproblem.clientapi.exceptions.DataNotFoundException;
import com.mitali.devproblem.clientapi.exceptions.InvalidIdException;
import com.mitali.devproblem.clientapi.exceptions.InvalidMobileNumberException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("Neha","Gupta","9414234561","7801014800084","Jaipur")
    ));

    //Get All Client information
    public List<Client> getAllClients() {
        return clients;
    }

    //Search Client by Id
    public Client getClientById(String id){

        return clients.stream().filter(c->c.getId().equals(id)).findFirst().orElse(null);

    }

    //Search Client by First Name
    public Client getClientByFirstName(String firstName){

        return clients.stream().filter(c->c.getFirstName().equals(firstName)).findFirst().orElse(null);

    }

    //Search Client by Mobile Number
    public Client getClientByMobileNumber(String mobileNumber){

        return clients.stream().filter(c->c.getMobileNumber().equals(mobileNumber)).findFirst().orElse(null);

    }

    //Create Client
    public Client createClient(Client client) {

        if(isIdExist(client.getId()))
            throw new InvalidIdException("Duplicate Client Id: This Client Id already exists.");

        Optional.ofNullable(client.getMobileNumber()).ifPresent(mobileNumber -> {
            if (Optional.ofNullable(isMobileNumberExist(mobileNumber)).orElse(true))
                throw new InvalidMobileNumberException("Duplicate Mobile Number : A client with this mobile number already exists,Enter other mobile number.");
        });

        clients.add(client);
        return client;

    }

    //Update Client
    public Client updateClient(Client client, String id) {

        Client c = Optional.ofNullable(getClientById(id))
                .orElseThrow(() -> new DataNotFoundException("Client does not exist for the given id Number"));

        Optional.ofNullable(client.getMobileNumber()).ifPresent(mobileNumber -> {
            if(Optional.of(!client.getMobileNumber().equals(c.getMobileNumber())).orElse(true))
                if (Optional.of(!isMobileNumberExist(mobileNumber)).orElseThrow(()->new InvalidMobileNumberException("Duplicate Mobile Number : A client with this mobile number already exists,Enter other mobile number.")));
                  c.setMobileNumber(client.getMobileNumber());
        });
             c.setFirstName(client.getFirstName());
             c.setLastName(client.getLastName());
             c.setAddress(client.getAddress());

        return client;
    }

    //Check for duplicate mobile number
    private Boolean isMobileNumberExist(String mobileNumber) {
        return Optional.ofNullable(getClientByMobileNumber(mobileNumber)).isPresent();
    }

    //check for duplicate client id
    private Boolean isIdExist(String id) {
        return Optional.ofNullable(getClientById(id)).isPresent();
    }
}
