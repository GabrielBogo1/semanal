package com.examplesemanal_serverless.semanal_serverless.controller;

import com.examplesemanal_serverless.semanal_serverless.entity.Pedido;
import com.examplesemanal_serverless.semanal_serverless.repository.PedidoRepository;
import com.examplesemanal_serverless.semanal_serverless.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "api/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findByIDPath(@PathVariable("id") final UUID id) {
        final Pedido pedido = this.pedidoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listAll() {
        return ResponseEntity.ok(this.pedidoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <HttpStatus> createPedido(@RequestBody final Pedido pedido) {
        try {
            this.pedidoService.save(pedido);
            return ResponseEntity.ok(HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public void deletePedido (final UUID id){
        final Pedido dataPedido = this.pedidoRepository.findById(id).orElse(null);

        if (dataPedido == null || !dataPedido.getId().equals(id)){
            throw new RuntimeException();
        }

        assert dataPedido != null;
        this.pedidoRepository.delete(dataPedido);
    }
}
