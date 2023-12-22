package com.example.useradvisormanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.useradvisormanagement.domain.Advisor;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {

	List<Advisor> findByAdvisorName(String advisorName);
}
