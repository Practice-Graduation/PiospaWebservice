package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ServiceType;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer>{
	@Query("select sg from ServiceType sg where sg.serviceTypeName = :serviceTypeName")
	public ServiceType findByName(@Param("serviceTypeName")String serviceTypeName);
}
