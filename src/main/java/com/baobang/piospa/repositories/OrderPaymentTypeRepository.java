package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.OrderPaymentType;

/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */
@Repository
public interface OrderPaymentTypeRepository extends JpaRepository<OrderPaymentType, Integer>{

}
