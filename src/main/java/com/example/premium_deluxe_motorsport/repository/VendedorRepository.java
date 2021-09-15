package com.example.premium_deluxe_motorsport.repository;

import com.example.premium_deluxe_motorsport.model.Cliente;
import com.example.premium_deluxe_motorsport.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    List<Vendedor> findAllByDeletedIsNull();
    Optional<Vendedor> findByDeletedIsNullAndId(Long id);

}
