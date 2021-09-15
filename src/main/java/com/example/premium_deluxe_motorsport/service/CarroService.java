package com.example.premium_deluxe_motorsport.service;
import com.example.premium_deluxe_motorsport.model.Carro;
import com.example.premium_deluxe_motorsport.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CarroService {


        private CarroRepository repository;

        @Autowired
        public void setRepository(CarroRepository repository) {
            this.repository = repository;
        }

        public Optional<Carro> getCarroById(Long id){
            return repository.findByDeletedIsNullAndId(id);
        }

        public List<Carro> getAllCarro(){
            return repository.findAllByDeletedIsNull();
        }

        public Carro insert(Carro c){
            return repository.save(c);
        }

        public Carro update(Carro c){
            return  repository.saveAndFlush(c);
        }

        public Carro delete(Long id){
            Carro c = repository.getById(id);
            c.setDeleted(new Date());
            return repository.saveAndFlush(c);
        }



}