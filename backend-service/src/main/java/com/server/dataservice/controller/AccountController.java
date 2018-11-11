package com.server.dataservice.controller;

import com.google.gson.Gson;
import com.server.common.model.Account;
import com.server.common.model.AccountTransaction;
import com.server.dataservice.service.AccountService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("accounts")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Account>> getAll() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Account> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accountService.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public void create(@RequestBody String payload) {
        accountService.create(new Gson().fromJson(payload, Account.class));
    }

    @PostMapping(value = "/create-transaction", consumes = "application/json")
    public void createTransaction(@RequestBody String payload) {
        accountService.createTransaction(new Gson().fromJson(payload, AccountTransaction.class));
    }

    @PostMapping(value = "/delete", consumes = "application/json")
    public void delete(@RequestBody String payload) {
        long id = Long.valueOf(payload);
        accountService.delete(id);
    }

    @PostMapping(value = "/delete-transaction", consumes = "application/json")
    public void deleteTransaction(@RequestBody String payload) {
        long id = Long.valueOf(payload);
        accountService.deleteTransaction(id);
    }

    @GetMapping("/get-transaction-by-name/{name}")
    public ResponseEntity<AccountTransaction> get(@PathVariable("name") String name) {
        return new ResponseEntity<>(accountService.getAccountTransactionByName(name), HttpStatus.OK);
    }
}
