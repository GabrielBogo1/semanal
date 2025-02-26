package com.examplesemanal_serverless.semanal_serverless.service;

import com.examplesemanal_serverless.semanal_serverless.entity.Pedido;
import com.examplesemanal_serverless.semanal_serverless.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
