package com.socialnetwork.api.repository;

import com.socialnetwork.api.model.Income.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
