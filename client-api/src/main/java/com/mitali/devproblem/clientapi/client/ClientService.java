package com.mitali.devproblem.clientapi.client;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {

    private List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("Neha","Gupta","9414234561","001","Jaipur"),
            new Client("Jyoti","Sharma","9414234562","002","Delhi"),
            new Client("Priya","jain","9414235463","003","Mumbai")
    ));

    public List<Client> getAllClients() {
        return clients;
    }

    public Client getClientById(String id){

        for(int i=0;i<clients.size();i++)
        {
            Client c = clients.get(i);
            if(c.getId().equals((id)))
                return c;
        }
        return null;
    }

    public Client getClientByFirstName(String firstName){
        for(int i=0;i<clients.size();i++)
        {
            Client c = clients.get(i);
            if(c.getFirstName().equals((firstName)))
                return c;
        }
        return null;
    }

    public Client getClientByMobileNumber(String mobileNumber){
        for(int i=0;i<clients.size();i++)
        {
            Client c = clients.get(i);
            if(c.getMobileNumber().equals((mobileNumber)))
                return c;
        }
        return null;
    }

    public Client createClient(Client client) {

        clients.add(client);
        return client;

    }


    public Client updateClient(Client client, String id) {
        for(int i=0; i< clients.size();i++) {
            Client c = clients.get(i);
            if (c.getId().equals(id))
                clients.set(i, client);
        }
        return client;
    }
}
