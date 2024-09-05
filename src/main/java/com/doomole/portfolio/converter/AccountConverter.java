package com.doomole.portfolio.converter;

import com.doomole.portfolio.dto.response.account.ResAccount;
import com.doomole.portfolio.entity.account.Account;

public class AccountConverter {

    public static Account toAccount(String accountId, String password, String name, String nickname, String email) {
        return Account.builder()
                .accountId(accountId)
                .password(password)
                .name(name)
                .nickname(nickname)
                .email(email)
                .build();
    }

    public static ResAccount toResAccount(Account account) {
        return ResAccount.builder()
                .accountIdx(account.getAccountIdx())
                .accountId(account.getAccountId())
                .name(account.getName())
                .nickname(account.getNickname())
                .email(account.getEmail())
                .avatar(account.getAvatar())
                .build();
    }
}
