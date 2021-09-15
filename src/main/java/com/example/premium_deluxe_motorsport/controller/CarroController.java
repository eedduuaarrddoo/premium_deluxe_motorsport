package com.example.premium_deluxe_motorsport.controller;
import com.example.premium_deluxe_motorsport.model.Carro;
import com.example.premium_deluxe_motorsport.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
@RequestMapping("/carro")
public class CarroController {

    private CarroService service;

    @Autowired
    public void setService(CarroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Carro> getAllCarro() {
        return service.getAllCarro();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carro> getCarroUnique(@PathVariable Long id) {
        Optional<Carro> a = service.getCarroById(id);
        if (a.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(a.get());
        }
    }

    @PostMapping
    public Carro newCarro(@RequestBody Carro carro) {
        return service.insert(carro);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCarro(@PathVariable Long id, @RequestBody Carro c) {
        return service.getCarroById(id).map(record -> {
            if (record.getId().equals(c.getId())) {
                service.update(c);
                return ResponseEntity.ok(c);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Carro c ){
       return service.getCarroById(id)
           .map( record -> {
                   service.delete(record.getId());
                   return ResponseEntity.ok(c);
               }).orElse(ResponseEntity.notFound().build());
    }

}




