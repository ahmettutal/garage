package com.tutalizm.garage.repository;

import com.tutalizm.garage.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends Entity> extends JpaRepository<T, Long> {

}
