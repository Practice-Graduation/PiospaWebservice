package com.baobang.piospa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.OrderProduct;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>{
	@Query("select op from OrderProduct op where op.order.orderId = :orderId")
	public List<OrderProduct> findOrderProductByOrderId(@Param("orderId") int orderId);
}
