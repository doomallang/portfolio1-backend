package com.doomole.portfolio.service;

import com.doomole.portfolio.config.TokenProvider;
import com.doomole.portfolio.converter.AccountConverter;
import com.doomole.portfolio.dto.request.account.ReqAccount;
import com.doomole.portfolio.dto.request.account.ReqPassword;
import com.doomole.portfolio.dto.response.account.ResAccount;
import com.doomole.portfolio.dto.response.common.ResToken;
import com.doomole.portfolio.entity.account.Account;
import com.doomole.portfolio.exception.FailException;
import com.doomole.portfolio.repository.account.AccountRepository;
import com.doomole.portfolio.util.AesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public ResToken login(ReqAccount reqAccount) {
        String accountId = reqAccount.getAccountId();
        String password = AesUtil.decrypt(reqAccount.getPassword());
        ResToken resToken = new ResToken();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(accountId, password));
            if(!authentication.isAuthenticated()) {
                throw new FailException("인증 에러");
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            Account account = accountRepository.findByAccountId(reqAccount.getAccountId());

            resToken.setToken(token);
            resToken.setAccountId(accountId);
            resToken.setAvatar(account.getAvatar());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new FailException(e.getMessage());
        } finally {
            return resToken;
        }
    }

    public long join(ReqAccount reqAccount) {
        String accountId = reqAccount.getAccountId();
        String password = AesUtil.decrypt(reqAccount.getPassword());
        String name = reqAccount.getName();
        String nickname = reqAccount.getNickname();
        String email = reqAccount.getEmail();

        Account account = selectAccountByAccountId(accountId);
        if(account != null) {
            throw new FailException("이미 존재하는 사용자입니다.");
        }

        String encPassword = encryptPassword(password);
        Account account1 = AccountConverter.toAccount(accountId, encPassword, name, nickname, email);
        accountRepository.save(account1);

        return account1.getAccountIdx();
    }

    public ResAccount getInfo(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        ResAccount resAccount = AccountConverter.toResAccount(account);

        return resAccount;
    }

    public void passwordChange(ReqPassword reqPassword) {
        long accountIdx = reqPassword.getAccountIdx();
        String prevPassword = AesUtil.decrypt(reqPassword.getPrevPassword());
        String nextPassword = AesUtil.decrypt(reqPassword.getNextPassword());

        Account account = accountRepository.findById(accountIdx).get();

        if(!passwordMatch(prevPassword, account.getPassword())) {
            throw new FailException("비밀번호가 일치하지 않습니다.");
        }

        String encPassword = encryptPassword(nextPassword);
        account.setPassword(encPassword);

        accountRepository.save(account);
    }

    public void modify(ReqAccount reqAccount) {
        Account account = accountRepository.findById(reqAccount.getAccountIdx()).get();

        account.setNickname(reqAccount.getNickname());
        account.setAvatar(reqAccount.getAvatar());

        accountRepository.save(account);
    }

    public Account selectAccountByAccountId(String accountId) {
        return accountRepository.findByAccountId(accountId);
    }



    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean passwordMatch(String password, String encryptPassword) { return passwordEncoder.matches(password, encryptPassword); }

    public void setPasswordInit() {
        String password = encryptPassword("1");
        Account account = accountRepository.findById(3L).get();

        account.setPassword(password);
        accountRepository.save(account);
    }
}
