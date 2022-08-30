package com.codezmr.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codezmr.spring.batch.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>{

}
