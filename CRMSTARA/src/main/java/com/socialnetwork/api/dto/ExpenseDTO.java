package com.socialnetwork.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO {
  private Long id;
  private Double amount;
  private LocalDate date;
  private String category;
  private String note;

}
