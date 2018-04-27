package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.OrderDeliveryType;

/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */
@Repository
public interface OrderDeliveryTypeRepository extends JpaRepository<OrderDeliveryType, Integer>{
	@Query("select odt from OrderDeliveryType odt where odt.orderDeliveryTypeCode = :orderDeliveryTypeCode")
	public OrderDeliveryType findByCode(@Param("orderDeliveryTypeCode") String orderDeliveryTypeCode);
}
