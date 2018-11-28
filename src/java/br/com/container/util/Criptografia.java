/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.container.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 *
 * @author orion
 */
public class Criptografia {

    private static Object salt;

    public static String geraHash(String senha) {
        MessageDigestPasswordEncoder encodadorSenha = getMessageDigesterPassword();
        String encodePassword = encodadorSenha.encodePassword(senha, salt);
        return encodePassword;
    }

    private static MessageDigestPasswordEncoder getMessageDigesterPassword() {
//informo tipo de enconding que desejo 
        MessageDigestPasswordEncoder digestPasswordEncoder = new MessageDigestPasswordEncoder("MD5");
        return digestPasswordEncoder;
    }
    //método que faz a validação  como não usamos salt deixei em null

    public static boolean isSenhaValida(String senha, String hashSenha) {
        MessageDigestPasswordEncoder digestPasswordEncoder = getMessageDigesterPassword();
        return digestPasswordEncoder.isPasswordValid(hashSenha, senha, salt);
    }
}

