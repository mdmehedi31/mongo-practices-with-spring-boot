package com.mongopractices.repository;

import com.mongopractices.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity,Object> {
}
