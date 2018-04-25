package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.District;

/**
  * @author BaoBang
  * @Created Apr 20, 2018
  * 
  */
@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{
	@Query("select d from District d where d.districtid = :districtid")
	public District findById(@Param("districtid")String districtid);
}
