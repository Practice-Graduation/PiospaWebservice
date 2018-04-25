package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.email = :email and u.password = :password")
	public User findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

	@Query("select u from User u where u.email = :email")
	public User findByEmail(@Param("email") String email);
}
