package com.socialnetwork.api.repository;

import com.socialnetwork.api.model.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
