package com.example.premium_deluxe_motorsport.service;

import com.example.premium_deluxe_motorsport.model.Cliente;
import com.example.premium_deluxe_motorsport.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService  {

    private ClienteRepository repository;

    @Autowired
    public void setRepository(ClienteRepository repository) {
        this.repository = repository;
    }

    public Optional<Cliente> getClienteById(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<Cliente> getAllCliente(){
        return repository.findAllByDeletedIsNull();
    }

    public Cliente insert(Cliente cli){
        return repository.save(cli);
    }

    public Cliente update(Cliente cli){
        return  repository.saveAndFlush(cli);
    }

    public Cliente delete(Long id){
        Cliente cli = repository.getById(id);
        cli.setDeleted(new Date());
        return repository.saveAndFlush(cli);
    }



}
