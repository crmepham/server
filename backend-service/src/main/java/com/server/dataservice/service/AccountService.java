package com.server.dataservice.service;

import com.server.common.model.Account;
import com.server.common.model.AccountTransaction;
import com.server.dataservice.repository.AccountRepository;
import com.server.dataservice.repository.AccountTransactionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    public List<Account> getAll() {
        return accountRepository.findByDeletedFalse();
    }

    public Account get(Long id) {
        return accountRepository.findById(id).get();
    }

    public void create(Account account) {
        accountRepository.save(account);
    }

    public void createTransaction(AccountTransaction transaction) {
        accountTransactionRepository.save(transaction);
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }

    public void deleteTransaction(long id) {
        accountTransactionRepository.deleteById(id);
    }

    public AccountTransaction getAccountTransactionByName(String name) {

        return accountTransactionRepository.findFirstByNameAndDeletedFalse(name);
    }
}
