package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Staff;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	@Query("select s from Staff s where s.code = :code")
	public Staff findByCode(@Param("code") String code);
	
	@Query("select s from Staff s where s.account = :account")
	public Staff findByUsername(@Param("account") String account);
	
	@Query("select s from Staff s where s.account = :account and s.password = :password")
	public Staff findByUsernameAndPassword(@Param("account") String account, @Param("password") String password);
}
