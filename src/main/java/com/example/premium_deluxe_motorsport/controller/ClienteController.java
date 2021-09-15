package com.example.premium_deluxe_motorsport.controller;
import com.example.premium_deluxe_motorsport.model.Cliente;
import com.example.premium_deluxe_motorsport.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteService service;

    @Autowired
    public void setService(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> getAllCliente() {
        return service.getAllCliente();
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> getClienteUnique(@PathVariable Long id) {
        Optional<Cliente> c = service.getClienteById(id);
        if (c.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(c.get());
        }
    }

    @PostMapping
    public Cliente newCliente(@RequestBody Cliente cliente) {
        return service.insert(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente c) {
        return service.getClienteById(id).map(record -> {
            if (record.getId().equals(c.getId())) {
                service.update(c);
                return ResponseEntity.ok(c);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,Cliente c ){
        return service.getClienteById(id)
                .map( record -> {
                    service.delete(record.getId());
                    return ResponseEntity.ok(c);
                }).orElse(ResponseEntity.notFound().build());
    }







}
