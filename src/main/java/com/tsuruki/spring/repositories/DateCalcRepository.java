package com.tsuruki.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsuruki.spring.DateCalc;

@Repository
public interface DateCalcRepository extends JpaRepository<DateCalc, String> {
	public Optional<DateCalc> findById(String dateId);
}
