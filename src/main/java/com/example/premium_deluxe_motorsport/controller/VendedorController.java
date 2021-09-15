package com.example.premium_deluxe_motorsport.controller;
import com.example.premium_deluxe_motorsport.model.Vendedor;
import com.example.premium_deluxe_motorsport.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
@RequestMapping("/vendedor")

public class VendedorController {
    private VendedorService service;
    @Autowired
    public void setService(VendedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vendedor> getAllVendedor() {
        return service.getAllVendedor();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vendedor> getVendedorUnique(@PathVariable Long id) {
        Optional<Vendedor> a = service.getVendedorById(id);
        if (a.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(a.get());
        }
    }

    @PostMapping
    public Vendedor newVendedor(@RequestBody Vendedor vendedor) {
        return service.insert(vendedor);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateVendedor(@PathVariable Long id, @RequestBody Vendedor v) {
        return service.getVendedorById(id).map(record -> {
            if (record.getId().equals(v.getId())) {
                service.update(v);
                return ResponseEntity.ok(v);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Vendedor v ){
        return service.getVendedorById(id)
                .map( record -> {
                    service.delete(record.getId());
                    return ResponseEntity.ok(v);
                }).orElse(ResponseEntity.notFound().build());
    }








}