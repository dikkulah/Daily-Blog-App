package com.dikkulah.data.repository;

import com.dikkulah.data.entity.Daily;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyRepository extends MongoRepository<Daily, String> {
    List<Daily> findByEmail(String email);
}
