/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ngai.auth.model.repository;

import com.ngai.auth.model.entity.TCountryCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SOFT
 */
@Repository
public interface TCountryCodesRepository extends JpaRepository<TCountryCodes, String>{
    
}
