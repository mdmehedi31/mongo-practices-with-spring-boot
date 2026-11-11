package com.mongopractices.service;


import com.mongopractices.entity.ProductEntity;
import com.mongopractices.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public ProductEntity save(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public String delete(ObjectId id) {
        try{
            productRepository.deleteById(id);
            return "Product deleted successfully";
        }catch (Exception e) {
            log.error("An exception occurred while deleting the product", e);
            return "An exception occurred while deleting the product";
        }
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public ProductEntity findOne(ObjectId id) {
        return productRepository.findById(id).orElse(null);
    }
}
