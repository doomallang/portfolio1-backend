package com.doomole.portfolio.repository.account;

import com.doomole.portfolio.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountId(String accountId);
}
