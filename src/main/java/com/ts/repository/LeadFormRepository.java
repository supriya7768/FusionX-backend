package com.ts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ts.model.LeadForm;

@Repository
public interface LeadFormRepository extends JpaRepository<LeadForm, Long> {
	Optional<LeadForm> findByEmail(String email);

	@Query("SELECT lf FROM LeadForm lf " + "WHERE ( " + "   (:search IS NULL) OR " + "   ( "
			+ "      (lf.leadName LIKE %:search%) OR " + "      (lf.mobileNo LIKE %:search%) OR "
			+ "      (lf.email LIKE %:search%) OR " + "      (lf.courseName LIKE %:search%) " + "   ) " + ")")
	List<LeadForm> searchByCriteria(@Param("search") String search);
}
