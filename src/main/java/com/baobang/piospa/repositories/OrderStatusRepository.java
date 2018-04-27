package com.baobang.piospa.repositories;
/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.OrderStatus;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{

}
