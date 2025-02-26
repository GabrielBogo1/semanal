package com.examplesemanal_serverless.semanal_serverless.repository;

import com.examplesemanal_serverless.semanal_serverless.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
