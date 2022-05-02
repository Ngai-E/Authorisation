package com.ngai.auth.model.repository;

import com.ngai.auth.model.entity.TToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTokenRepository extends JpaRepository<TToken, String> {
}
