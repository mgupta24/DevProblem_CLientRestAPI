package com.mitali.devproblem.clientapi.client;

import java.util.List;

public interface ClientService {

    public List<Client> getAllClients();

    public Client getClientById(String id);

    public Client getClientByFirstName(String firstName);

    public Client getClientByMobileNumber(String mobileNumber);

    public Client createClient(Client client);

    public Client updateClient(Client client, String id);
}
