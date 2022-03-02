package com.example._08_spring_data_intro_lab.repositories;

import com.example._08_spring_data_intro_lab.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
