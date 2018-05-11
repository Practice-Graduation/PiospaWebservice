package com.baobang.piospa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Service;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>{
	@Query("select s from Service s where s.serviceCode = :serviceCode")
	public Service findByCode(@Param("serviceCode") String serviceCode);
	@Query("select s from Service s where s.serviceGroup.serviceGroupId = :serviceGroupId")
	public List<Service> getServiceByGroupId(@Param("serviceGroupId") int serviceGroupId);
}
