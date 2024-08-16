package org.example;
import org.example.domain.entity.Cliente;
import org.example.domain.entity.Pedido;
import org.example.domain.repository.Clientes;
import org.example.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
        return args -> {
            System.out.println("Salvando Clientes");

            Cliente cliente = new Cliente("Maria");

            clientes.save(cliente);
            clientes.save(new Cliente("Caio"));

            Pedido p = new Pedido();
            p.setCliente(cliente);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(1000));

            pedidos.save(p);

            Cliente fulano = clientes.findClientFetchPedidos(cliente.getId());
            System.out.println(fulano);
            System.out.println(fulano.getPedidos());
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(VendasApplication.class, args);
    }
}
