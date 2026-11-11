package com.mongopractices.repository;

import com.mongopractices.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
    UserEntity getUserEntityById(ObjectId id);
}
