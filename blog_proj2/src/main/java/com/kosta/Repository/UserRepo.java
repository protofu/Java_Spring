package com.kosta.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	List<User> findAllByNameContainsOrContentContainsOrderByName(String keyword, String keyword2);

	List<User> findAllByNameContainsOrContentContainsOrderByNameDesc(String keyword, String keyword2);

}
