package com.server.frontendservice.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.common.model.Account;
import com.server.common.model.AccountTransaction;
import com.server.common.service.BaseService;
import com.server.frontendservice.repository.AccountRepository;

@Transactional
@Service
public class AccountService extends BaseService
{
    @Autowired
    private AccountRepository accountRepository;

    public CompletableFuture<List<Account>> getAll() {

        return accountRepository.getAll();
    }

    public void update(Account account) {
        accountRepository.create(account);
    }

    public void createTransaction(AccountTransaction transaction) {
        accountRepository.createTransaction(transaction);
    }

    public void delete(long id) {
        accountRepository.delete(id);
    }

    public void deleteTransaction(long id) {
        accountRepository.deleteTransaction(id);
    }

    public CompletableFuture<Account> getById(long id) {
        return accountRepository.getById(id);
    }
}
