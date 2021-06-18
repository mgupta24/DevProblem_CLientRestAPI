package com.mitali.devproblem.clientapi.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired(required = true)
    private ClientService clientService;

    @RequestMapping("/clients")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @RequestMapping("/clients/id/{id}")
    public Client getClientById(@PathVariable String id){
        return clientService.getClientById(id);
    }

    @RequestMapping("/clients/firstname/{firstName}")
    public Client getClientByFirstName(@PathVariable String firstName){
        return clientService.getClientByFirstName(firstName);
    }

    @RequestMapping("/clients/mobilenumber/{mobileNumber}")
    public Client getClientByMobileNumber(@PathVariable String mobileNumber){
        return clientService.getClientByMobileNumber(mobileNumber);
    }

    @RequestMapping(method= RequestMethod.POST, value= "/clients/create")
    public Client createClient(@Valid @RequestBody Client client){
        return clientService.createClient(client);
    }

    @RequestMapping(method= RequestMethod.PUT, value= "/clients/update/{id}")
    public Client updateClient(@Valid @RequestBody Client client, @PathVariable String id){
        return clientService.updateClient(client,id);
    }

}
