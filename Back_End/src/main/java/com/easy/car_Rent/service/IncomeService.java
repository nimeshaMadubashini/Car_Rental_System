package com.easy.car_Rent.service;

import com.easy.car_Rent.dto.IncomeDTO;

import java.util.ArrayList;

public interface IncomeService {
    ArrayList<IncomeDTO> dailyIncome();
    ArrayList<IncomeDTO> monthlyIncome();
    ArrayList<IncomeDTO> AnnuallyIncome();
}
