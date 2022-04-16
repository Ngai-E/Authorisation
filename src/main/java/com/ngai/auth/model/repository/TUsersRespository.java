package com.ngai.auth.model.repository;

import com.ngai.auth.model.entity.TUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TUsersRespository extends JpaRepository<TUsers, String> {
    List<TUsers> findByUsername(String username);
}
