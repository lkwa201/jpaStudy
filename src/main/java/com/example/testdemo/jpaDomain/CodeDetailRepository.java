package com.example.testdemo.jpaDomain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeDetailRepository extends JpaRepository<CodeDetail, String> {

	List<CodeDetail> findByGroupCode(String groupCode);

	@Transactional
	@Modifying
	@Query(value = "UPDATE  CodeDetail set codeName = :#{#codeDetail.codeName} " +
			"WHERE codeDetailNo = :#{#codeDetail.codeDetailNo}", nativeQuery = false)
	Integer update(@Param("codeDetail") CodeDetail codeDetail);
}
