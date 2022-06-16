/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.auth.security;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author SOFT
 */
public class AuthException extends AuthenticationException {
    
    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
