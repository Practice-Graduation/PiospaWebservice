package com.baobang.piospa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ServicePrice;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServicePriceRepository extends JpaRepository<ServicePrice, Integer>{

	@Query("select sp from ServicePrice sp where sp.serviceGroup.serviceGroupId = :serviceGroupId")
	public List<ServicePrice> findByServiceGroup(@Param("serviceGroupId")int group);
}
