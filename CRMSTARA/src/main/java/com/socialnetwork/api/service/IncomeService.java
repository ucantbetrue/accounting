package com.socialnetwork.api.service;

import com.socialnetwork.api.dto.IncomeDTO;
import com.socialnetwork.api.model.Income.Income;
import com.socialnetwork.api.model.expense.Expense;
import com.socialnetwork.api.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
  private final IncomeRepository incomeRepository;

  @Autowired
  public IncomeService(IncomeRepository incomeRepository) {
    this.incomeRepository = incomeRepository;
  }

  public void addIncome(IncomeDTO incomeDTO) {
    Income income = new Income();
    income.setAmount(incomeDTO.getAmount());
    income.setDate(incomeDTO.getDate());
    income.setCategory(incomeDTO.getCategory());
    income.setNote(incomeDTO.getNote());
    incomeRepository.save(income);
  }


  public void editIncome(Long incomeId, IncomeDTO incomeDTO) {
    Optional<Income> optionalIncome = incomeRepository.findById(incomeId);
    if (optionalIncome.isPresent()) {
      Income income = optionalIncome.get();
      income.setAmount(incomeDTO.getAmount());
      income.setDate(incomeDTO.getDate());
      income.setCategory(incomeDTO.getCategory());
      income.setNote(incomeDTO.getNote());
      incomeRepository.save(income);
    } else {
      throw new EntityNotFoundException("Income with ID " + incomeId + " not found.");
    }
  }
  public void deleteIncome(Long incomeId) {
    Optional<Income> optionalIncome = incomeRepository.findById(incomeId);
    if (optionalIncome.isPresent()) {
      incomeRepository.deleteById(incomeId);
    } else {
      throw new EntityNotFoundException("Income with ID " + incomeId + " not found.");
    }
  }

  public List<Income> getAllIncomes() {
    return incomeRepository.findAll();
  }
}
