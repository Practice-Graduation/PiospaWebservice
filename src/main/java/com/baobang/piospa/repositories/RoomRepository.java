package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Room;

/**
  * @author BaoBang
  * @Created May 13, 2018
  * 
  */
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{

}
