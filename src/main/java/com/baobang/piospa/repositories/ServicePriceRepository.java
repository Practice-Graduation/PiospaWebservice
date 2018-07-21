package com.baobang.piospa.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Product;
import com.baobang.piospa.entities.ServicePrice;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServicePriceRepository extends JpaRepository<ServicePrice, Integer>{
	
	@Query("select sp from ServicePrice sp where sp.serviceGroup.serviceGroupId = :serviceGroupId and sp.isActive = 1")
	List<ServicePrice> getServiceByGroupId(@Param("serviceGroupId") int serviceGroupId);
	
	@Query(value="SELECT * FROM service_price WHERE service_group_id = ?1 and is_active = 1 LIMIT 10", nativeQuery = true)
	public List<ServicePrice> findTopTenByGroupId(String groupId);
	
}
