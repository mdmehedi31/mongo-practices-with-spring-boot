package com.mongopractices.service;


import com.mongopractices.dto.OrderDTO;
import com.mongopractices.entity.OrderEntity;
import com.mongopractices.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    public MongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public OrderEntity save(OrderEntity orderEntity) {
        log.info("Saving order entity: {}" , orderEntity.getUserId());

        return orderRepository.save(orderEntity);
    }

    private OrderEntity findById(ObjectId id) {
        return orderRepository.findById(id).isPresent() ? orderRepository.findById(id).get() : null;
    }

    public List<OrderDTO> findAllOrderByUserId(ObjectId userId) {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("userId").is(userId)),

                Aggregation.group("productId")
                        .sum("price").
                        as("totalPrice")
                        .sum("quantity").as("totalQuantity"),
                Aggregation.lookup("users","userId","_id","ud"),

                Aggregation.lookup("products","productId", "_id","pd"),

                Aggregation.project("id", "totalQuantity","totalPrice","ud","pd").
                        and("ud.id").as("userId").
                        and("ud.email").as("email").
                        and("pd.id").as("productId").
                        and("pd.productName").as("productName")
        );

        AggregationResults<OrderDTO> results = mongoTemplate
                .aggregate(aggregation, "orders", OrderDTO.class);

        return results.getMappedResults();

    }
}
