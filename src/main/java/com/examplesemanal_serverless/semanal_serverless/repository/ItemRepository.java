package com.examplesemanal_serverless.semanal_serverless.repository;

import com.examplesemanal_serverless.semanal_serverless.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository <Item, Long> {
}
