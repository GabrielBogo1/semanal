package com.examplesemanal_serverless.semanal_serverless.service;

import com.examplesemanal_serverless.semanal_serverless.entity.Item;
import com.examplesemanal_serverless.semanal_serverless.entity.Pedido;
import com.examplesemanal_serverless.semanal_serverless.repository.ItemRepository;
import com.examplesemanal_serverless.semanal_serverless.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
