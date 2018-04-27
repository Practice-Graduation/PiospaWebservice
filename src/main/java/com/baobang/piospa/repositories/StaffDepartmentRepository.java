package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.StaffDepartment;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@Repository
public interface StaffDepartmentRepository extends JpaRepository<StaffDepartment, Integer> {
	@Query("select sd from StaffDepartment sd where sd.staffDepartmentCode = :staffDepartmentCode")
	public StaffDepartment findByCode(@Param("staffDepartmentCode") String staffDepartmentCode);
}
