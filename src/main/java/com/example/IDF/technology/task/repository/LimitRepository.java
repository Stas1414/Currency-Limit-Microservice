package com.example.IDF.technology.task.repository;

import com.example.IDF.technology.task.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
}
