package com.socialnetwork.api.service;

import com.socialnetwork.api.dto.ExpenseDTO;
import com.socialnetwork.api.model.expense.Expense;
import com.socialnetwork.api.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
  private final ExpenseRepository expenseRepository;

  @Autowired
  public ExpenseService(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  public void addExpense(ExpenseDTO expenseDTO) {
    Expense expense = new Expense();
    expense.setAmount(expenseDTO.getAmount());
    expense.setDate(expenseDTO.getDate());
    expense.setCategory(expenseDTO.getCategory());
    expense.setNote(expenseDTO.getNote());
    expenseRepository.save(expense);
  }

  public void editExpense(Long expenseId, ExpenseDTO expenseDTO) {
    Optional<Expense> optionalExpense = expenseRepository.findById(expenseId);
    if (optionalExpense.isPresent()) {
      Expense expense = optionalExpense.get();
      expense.setAmount(expenseDTO.getAmount());
      expense.setDate(expenseDTO.getDate());
      expense.setCategory(expenseDTO.getCategory());
      expense.setNote(expenseDTO.getNote());
      expenseRepository.save(expense);
    } else {
      throw new EntityNotFoundException("Expense with ID " + expenseId + " not found.");
    }
  }


  public void deleteExpense(Long expenseId) {
    Optional<Expense> optionalIncome = expenseRepository.findById(expenseId);
    if (optionalIncome.isPresent()) {
      expenseRepository.deleteById(expenseId);
    } else {
      throw new EntityNotFoundException("Income with ID " + expenseId + " not found.");
    }
  }
  public List<Expense> getAllExpenses() {
    return expenseRepository.findAll();
  }

}
