package com.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.ResponseLogin;
import com.backend.dto.UsuarioLoginDTO;
import com.backend.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    
    @PostMapping("/login")
    public ResponseEntity<ResponseLogin> login(@RequestBody UsuarioLoginDTO login){
        return new ResponseEntity<>(usuarioServiceImpl.login(login), HttpStatus.OK);
    }

}
