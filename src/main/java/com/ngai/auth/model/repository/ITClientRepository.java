/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ngai.auth.model.repository;

import com.ngai.auth.model.entity.TClient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SOFT
 */
@Repository
public interface ITClientRepository extends JpaRepository<TClient, String>{
    
    public Optional<TClient> findByStrUsername(String strUsername);
}
