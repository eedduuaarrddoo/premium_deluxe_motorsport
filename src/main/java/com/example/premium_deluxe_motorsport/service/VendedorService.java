package com.example.premium_deluxe_motorsport.service;
import com.example.premium_deluxe_motorsport.model.Vendedor;
import com.example.premium_deluxe_motorsport.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService  {

    private VendedorRepository repository;

    @Autowired
    public void setRepository(VendedorRepository repository) {
        this.repository = repository;
    }

    public Optional<Vendedor> getVendedorById(Long id){ return repository.findByDeletedIsNullAndId(id); }

    public List<Vendedor> getAllVendedor(){
        return repository.findAllByDeletedIsNull();
    }

    public Vendedor insert(Vendedor v){ return repository.save(v); }

    public Vendedor update(Vendedor v){
        return  repository.saveAndFlush(v);
    }

    public Vendedor delete(Long id){
        Vendedor v = repository.getById(id);
        v.setDeleted(new Date());
        return repository.saveAndFlush(v);
    }



}
