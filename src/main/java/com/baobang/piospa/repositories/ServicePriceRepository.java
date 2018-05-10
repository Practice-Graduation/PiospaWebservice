package com.baobang.piospa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ServicePrice;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServicePriceRepository extends JpaRepository<ServicePrice, Integer>{

}
