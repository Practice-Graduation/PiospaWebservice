package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
