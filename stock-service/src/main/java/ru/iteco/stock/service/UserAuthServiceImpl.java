package ru.iteco.stock.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.iteco.stock.model.dto.UserAuthDto;
import ru.iteco.stock.model.entity.UserAuthEntity;
import ru.iteco.stock.repository.UserAuthRepository;

@Component
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService{

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String create(UserAuthDto userAuthDto) {
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        userAuthEntity.setUsername(userAuthDto.getLogin());
        userAuthEntity.setPassword(passwordEncoder.encode(userAuthDto.getPassword()));
        return userAuthRepository.save(userAuthEntity).getUsername();
    }

}
