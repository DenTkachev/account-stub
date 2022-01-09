package ru.iteco.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.stock.model.entity.UserAuthEntity;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {

    Optional<UserAuthEntity> findByUsername(String username);

}
