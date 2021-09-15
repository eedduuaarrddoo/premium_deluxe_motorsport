package com.example.premium_deluxe_motorsport.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String modelo;
    Integer valor;
    String fabricante;
    String vendedor;
    Boolean condicao = false;
    Date deleted=null;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendedor_id")
    Vendedor vendedorDaCompra;
}





