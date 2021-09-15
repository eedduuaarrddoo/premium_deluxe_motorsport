package com.example.premium_deluxe_motorsport.repository;
import com.example.premium_deluxe_motorsport.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findAllByDeletedIsNull();
    Optional<Carro> findByDeletedIsNullAndId(Long id);
}
