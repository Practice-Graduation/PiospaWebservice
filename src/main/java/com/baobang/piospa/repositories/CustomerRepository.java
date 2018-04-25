package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Customer;

/**
  * @author BaoBang
  * @Created Apr 24, 2018
  * 
  */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("select c from Customer c where c.code = :code")
	public Customer findByCode(@Param("code") String code);
}
