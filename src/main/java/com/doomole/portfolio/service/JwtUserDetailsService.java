package com.doomole.portfolio.service;

import com.doomole.portfolio.dto.request.account.AccountDetails;
import com.doomole.portfolio.entity.account.Account;
import com.doomole.portfolio.repository.account.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class JwtUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDetails user = null;

        Account account = accountRepository.findByAccountId(username);

        if(account != null) {
            user = new AccountDetails();
            user.setUsername(account.getAccountId());
            user.setPassword(account.getPassword());
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
            user.setAuthorities(Collections.singleton(grantedAuthority));
        }

        return user;
    }
}
