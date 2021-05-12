package com.example.testdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Board SET content = ?2 , updateDateTime = current_timestamp WHERE id = ?1")
	void updateContent(Long id, String content);
}
