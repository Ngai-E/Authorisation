package com.ngai.auth.model.repository;

import com.ngai.auth.model.entity.TParamters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TParametersRepository extends JpaRepository<TParamters, String> {
}
