/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nunesonline.todolistapi.dto;

import lombok.Data;

@Data
public class LoginRET extends DefaultRET{
    
    private String jwtToken;
   
}
