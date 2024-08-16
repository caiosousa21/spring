package org.example.domain.repository;

import org.example.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeLikeOrIdOrderByNome(String nome, Integer id);

    boolean existsByNome(String nome);

    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> findByNomeCustom(@Param("nome") String nome);

    void deleteByNome(String nome);

    @Query("delete from Cliente c where c.nome =:nome")
    @Modifying
    void deleteByNomeCustom(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos p where c.id =:id")
    Cliente findClientFetchPedidos(@Param("id") Integer id);
}
