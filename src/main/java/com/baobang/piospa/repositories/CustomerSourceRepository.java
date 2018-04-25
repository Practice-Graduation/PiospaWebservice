package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.CustomerSource;

/**
  * @author BaoBang
  * @Created Apr 20, 2018
  * 
  */
@Repository
public interface CustomerSourceRepository extends JpaRepository<CustomerSource, Integer> {
	@Query("select cs from CustomerSource cs where cs.customerSourceCode = :customerSourceCode")
	public CustomerSource findByCode(@Param("customerSourceCode") String customerSourceCode);
}
