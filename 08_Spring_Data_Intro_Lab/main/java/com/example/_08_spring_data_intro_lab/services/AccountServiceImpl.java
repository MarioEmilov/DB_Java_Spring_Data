package com.example._08_spring_data_intro_lab.services;

import com.example._08_spring_data_intro_lab.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {

    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {

    }
}
