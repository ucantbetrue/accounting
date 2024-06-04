package com.socialnetwork.api.сontroller;

import com.socialnetwork.api.dto.ExpenseDTO;
import com.socialnetwork.api.dto.IncomeDTO;
import com.socialnetwork.api.model.Income.Income;
import com.socialnetwork.api.model.expense.Expense;
import com.socialnetwork.api.service.ExpenseService;
import com.socialnetwork.api.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/financial")
public class FinancialController {
  private final ExpenseService expenseService;
  private final IncomeService incomeService;

  @Autowired
  public FinancialController(ExpenseService expenseService, IncomeService incomeService) {
    this.expenseService = expenseService;
    this.incomeService = incomeService;
  }
  @GetMapping("/expense-all")
  public ResponseEntity<List<Expense>> getAllExpenses() {
    List<Expense> expenses = expenseService.getAllExpenses();
    return ResponseEntity.ok(expenses);
  }

  @PostMapping("/expenses")
  public ResponseEntity<String> addExpense(@RequestBody ExpenseDTO expenseDTO) {
    expenseService.addExpense(expenseDTO);
    return ResponseEntity.ok("Expense added successfully");
  }

  @PutMapping("/expenses/{expenseId}")
  public ResponseEntity<String> editExpense(@PathVariable Long expenseId, @RequestBody ExpenseDTO expenseDTO) {
    try {
      expenseService.editExpense(expenseId, expenseDTO);
      return ResponseEntity.ok("Expense updated successfully.");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
  @DeleteMapping("/expenses/{expenseId}")
  public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId) {
    try {
      expenseService.deleteExpense(expenseId);
      return ResponseEntity.ok("Expense deleted successfully.");
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }
  //incomeService
  @PostMapping("/income")
  public ResponseEntity<String> addIncome(@RequestBody IncomeDTO incomeDTO) {
    incomeService.addIncome(incomeDTO);
    return ResponseEntity.ok("Income added successfully.");
  }
  @PutMapping("/income/{incomeId}")
  public ResponseEntity<String> editIncome(@PathVariable Long incomeId, @RequestBody IncomeDTO incomeDTO) {
    try {
      incomeService.editIncome(incomeId,incomeDTO );
      return ResponseEntity.ok("Income updated successfully.");
    }catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/income/{incomeId}")
  public ResponseEntity<String> deleteIncome(@PathVariable Long incomeId) {
    try {
      incomeService.deleteIncome(incomeId);
      return ResponseEntity.ok("Income deleted successfully.");
    }catch (EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }
  @GetMapping("/income-all")
  public ResponseEntity<List<Income>> getAllIncomes() {
    List<Income> income = incomeService.getAllIncomes();
    return ResponseEntity.ok(income);
  }

}
