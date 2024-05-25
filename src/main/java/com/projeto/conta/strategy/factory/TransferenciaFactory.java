package com.projeto.conta.strategy.factory;

import com.projeto.conta.exception.HttpStatusException;
import com.projeto.conta.strategy.TransacaoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class TransferenciaFactory {

    private Map<String, TransacaoStrategy> transacaoMap = new HashMap<>();

    public TransferenciaFactory(@Autowired List<TransacaoStrategy> transacaoStrategies) {
        transacaoStrategies.forEach(strategy ->
                transacaoMap.put(strategy.buscarTipo(), strategy)
        );
    }

    public TransacaoStrategy buscarStrategy(String tipo) {
        TransacaoStrategy strategy = transacaoMap.get(tipo);
        if (Objects.isNull(strategy)) {
            throw new HttpStatusException();
        }
        return strategy;
    }
}
