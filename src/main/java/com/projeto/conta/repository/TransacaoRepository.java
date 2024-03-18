package com.projeto.conta.repository;

import com.projeto.conta.entity.Cliente;
import com.projeto.conta.entity.Transacao;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Integer> {

    List<Transacao> findByClienteOrderByRealizadaEmDesc(final Cliente cliente, final Limit limit);

}
