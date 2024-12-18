package com.example.nagoyamesi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyamesi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String email);
	public Page<User> findByNameLikeOrFuriganaLikeOrEmailLike(String nameKeyword, String furiganaKeyword, String emailKeyword,Pageable pageable);
	Optional<User> findById(Long id);
	public User findUserByEmail(String email);
	void  deleteById(Long id);
}
