package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Order;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	@Query("select o from Order o where o.code = :code")
	public Order findByCode(@Param("code") String code);
}
