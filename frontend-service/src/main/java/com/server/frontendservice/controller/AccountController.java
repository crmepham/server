package com.server.frontendservice.controller;

import static java.lang.String.format;
import static java.lang.String.valueOf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.server.common.model.Account;
import com.server.common.model.AccountTransaction;
import com.server.common.model.InputResult;
import com.server.frontendservice.service.AccountService;
import lombok.val;

@Controller
public class AccountController extends BaseController
{
    private static final String PATH = "applications/accounts";

    @Autowired
    private AccountService accountService;

    @GetMapping(PATH)
    public String reminders(Model model) throws ExecutionException, InterruptedException
    {

        val accounts = accountService.getAll();

        CompletableFuture.allOf(accounts).join();

        model.addAttribute("accounts", accounts.get());
        css(model, "data-tables", "data-tables/accounts", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");

        return "/applications/accounts";
    }

    @GetMapping("applications/accounts/{id}")
    public String reminder(Model model, @PathVariable("id") long id) throws ExecutionException, InterruptedException {

        val  account = accountService.getById(id);

        CompletableFuture.allOf(account).join();

        css(model, "data-tables");
        js(model, "data-tables");

        model.addAttribute("item", account.get());
        return "/applications/accounts/edit";
    }

    @GetMapping(value = "applications/accounts/create")
    public String createView(Model model)
    {
        model.addAttribute("item", new Account());

        return "/applications/accounts/edit";
    }

    @PostMapping(value = "applications/accounts/update")
    public String create(Model model,
                         @ModelAttribute("account") Account account,
                         RedirectAttributes redirect) throws ExecutionException, InterruptedException
    {
        val isNew = account.getId() == null;

        accountService.update(account);

        if (isNew)
        {
            val all = accountService.getAll();
            CompletableFuture.allOf(all).join();
            Collections.reverse(all.get());
            account = all.get().iterator().next();

        }

        model.addAttribute("item", account);

        toast(format("Successfully %s account", isNew ? "created" : "updated"), redirect);

        return "redirect:/applications/accounts/" + account.getId();
    }

    @GetMapping(value = "applications/accounts/{id}/delete")
    public String delete(@PathVariable("id") long id,
                         RedirectAttributes redirect)
    {
        accountService.delete(id);

        toast("Successfully deleted account", redirect);

        return "redirect:/applications/accounts";
    }

    @GetMapping(value = "applications/accounts/{accountId}/transaction/{transactionId}/delete")
    public String deleteTransaction(@PathVariable("accountId") long accountId,
                                    @PathVariable("transactionId") long transactionId,
                                    RedirectAttributes redirect)
    {
        accountService.deleteTransaction(transactionId);

        toast("Successfully deleted transaction", redirect);

        return "redirect:/applications/accounts/" + accountId;
    }

    @PostMapping(value = "applications/accounts/transaction/create")
    @ResponseBody
    public InputResult createTransaction(@RequestParam("name") String name,
                                         @RequestParam("type") String type,
                                         @RequestParam("value") double value,
                                         @RequestParam("percent") double percent,
                                         @RequestParam("accountId") long accountId) throws ExecutionException, InterruptedException
    {
        val account = accountService.getById(accountId);

        CompletableFuture.allOf(account).join();

        val existing = account.get().getTransactions().stream()
                .filter(t -> name.equalsIgnoreCase(t.getName())).collect(Collectors.toList());

        if (existing.size() > 0) {
            return new InputResult(format("A transaction with the name %s already exists.", name));
        }

        val transaction = new AccountTransaction();
        transaction.setAccountId(account.get().getId());
        transaction.setName(name);
        transaction.setType(type);
        transaction.setValue(value);
        transaction.setPercent(percent);

        accountService.createTransaction(transaction);

        return new InputResult();
    }
}
