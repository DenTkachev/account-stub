package ru.iteco.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iteco.account.model.entity.StatusEntity;

public interface ServiceRepository extends JpaRepository<StatusEntity, Integer> {

    StatusEntity findByName (String name);

}
