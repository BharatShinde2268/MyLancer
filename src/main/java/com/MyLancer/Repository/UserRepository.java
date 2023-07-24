package com.MyLancer.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MyLancer.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.email = :email")  // this is jpa query this jpa query platform indipentand but native/sql query is olny depent for sql
	public User getUserByUserName(@Param("email") String email);

}
