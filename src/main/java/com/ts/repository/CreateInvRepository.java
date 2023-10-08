package com.ts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.model.CreateInvModel;


@Repository
public interface CreateInvRepository extends JpaRepository<CreateInvModel, Long>{

	Optional<CreateInvModel> findByEmail(String email);
}
