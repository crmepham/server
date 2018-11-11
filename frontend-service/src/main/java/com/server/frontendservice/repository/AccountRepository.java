package com.server.frontendservice.repository;

import com.server.common.model.Account;
import com.server.common.model.AccountTransaction;
import com.server.common.repository.BaseRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Repository
public class AccountRepository extends BaseRepository
{
    private static final String GET_ALL = "accounts/get-all";
    private static final String GET_BY_ID = "accounts/get-by-id/";
    private static final String GET_TRANSACTION_BY_NAME = "accounts/get-transaction-by-name/";
    private static final String CREATE = "accounts/create";
    private static final String CREATE_TRANSACTION = "accounts/create-transaction";
    private static final String DELETE = "accounts/delete";
    private static final String DELETE_TRANSACTION = "accounts/delete-transaction";

    @Autowired
    private RestTemplate template;

    @Value("${base.api.uri}")
    private String baseUri;

    public CompletableFuture<List<Account>> getAll() {

        ResponseEntity<List<Account>> res = template.exchange(baseUri + GET_ALL, GET, getEntity(), new ParameterizedTypeReference<List<Account>>() {});
        return CompletableFuture.completedFuture(res.getBody());
    }

    public CompletableFuture<Account> getById(long id) {

        ResponseEntity<Account> account = template.exchange(baseUri + GET_BY_ID + id, GET, getEntity(), new ParameterizedTypeReference<Account>() {});
        return CompletableFuture.completedFuture(account.getBody());
    }

    public CompletableFuture<AccountTransaction> getAccountTransactionByName(String name) {

        ResponseEntity<AccountTransaction> account = template.exchange(baseUri + GET_TRANSACTION_BY_NAME + name, GET, getEntity(), new ParameterizedTypeReference<AccountTransaction>() {});
        return CompletableFuture.completedFuture(account.getBody());
    }

    public void create(Account account) {

        template.exchange(baseUri + CREATE, POST, postJson(account), Account.class);
    }

    public void createTransaction(AccountTransaction transaction) {

        template.exchange(baseUri + CREATE_TRANSACTION, POST, postJson(transaction), AccountTransaction.class);
    }

    public void delete(long id) {
        final String url = baseUri + DELETE;
        template.exchange(url, POST, postJson(id), Account.class);
    }

    public void deleteTransaction(long id) {

        final String url = baseUri + DELETE_TRANSACTION;
        template.exchange(url, POST, postJson(id), AccountTransaction.class);
    }
}
