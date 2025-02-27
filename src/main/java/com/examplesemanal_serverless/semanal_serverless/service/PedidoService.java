package com.examplesemanal_serverless.semanal_serverless.service;

import com.examplesemanal_serverless.semanal_serverless.entity.Item;
import com.examplesemanal_serverless.semanal_serverless.entity.Pedido;
import com.examplesemanal_serverless.semanal_serverless.enums.Status;
import com.examplesemanal_serverless.semanal_serverless.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido save(Pedido pedido) {

        if (pedido.getItens() != null) {
            for (Item item : pedido.getItens()) {
                item.setPedido(pedido);
            }
        }

        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }



    public void deletarPedido (final String id){
        final Pedido dataPedido = this.pedidoRepository.findById(id).orElse(null);

        if (dataPedido == null || !dataPedido.getId().equals(id)){
            throw new RuntimeException();
        }
        this.pedidoRepository.delete(dataPedido);
    }

    public ResponseEntity<?> updateStatus(String id, Status novoStatus) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setStatus(novoStatus);
            pedidoRepository.save(pedido);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Pedido atualizado com sucesso");
            response.put("status", novoStatus.name());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
    }

}
