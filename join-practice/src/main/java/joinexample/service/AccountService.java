package joinexample.service;

import joinexample.domain.Account;
import joinexample.dto.AccountForm;
import joinexample.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // DB 의 상태를 변경시키는 작업 또는 한번에 수행되어야 하는 연산들을 의미
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(AccountForm accountForm) {
        Account account = accountForm.toEntity();
        accountRepository.save(account);

        return account.getId();
    }
}
