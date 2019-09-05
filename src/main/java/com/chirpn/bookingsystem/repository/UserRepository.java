package com.chirpn.bookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chirpn.bookingsystem.entities.User;
/**
 * @author Satish Ghonge
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	User findOneByEmail(String email);
	
	User findById(Long id);

	//User findByPasswordToken(String passwordToken);

	//User findBySignInlinkToken(String token);
}
