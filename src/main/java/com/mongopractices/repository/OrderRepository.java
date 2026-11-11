package com.mongopractices.repository;

import com.mongopractices.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Object> {
}
