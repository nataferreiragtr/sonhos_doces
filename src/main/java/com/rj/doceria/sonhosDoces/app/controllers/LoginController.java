package com.rj.doceria.sonhosDoces.app.controllers;

import com.rj.doceria.sonhosDoces.domain.model.Login;
import com.rj.doceria.sonhosDoces.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "auth")
@Tag(name = "Autentificação Controller", description = "Controladora que gere o login do usuário")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um usuário", responses = {@ApiResponse(description = "Sucesso ao cadastrar", responseCode = "201", content = @Content)})
    ResponseEntity<Login> save(@RequestBody Login login){
        return new ResponseEntity<>(loginService.save(login), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Efetua o login de um usuário", responses = {@ApiResponse(description = "Sucesso ao efetuar o login", responseCode = "200", content = @Content)})
    ResponseEntity<Login> login(@RequestBody Login login){
        return new ResponseEntity<>(loginService.login(login), HttpStatus.OK);
    }

    @GetMapping(value = "/logoff/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Desconecta um usuário pelo ID", responses = {@ApiResponse(description = "Sucesso ao consultar", responseCode = "200", content = @Content)})
    ResponseEntity<?> logoff(@PathVariable UUID id){
        loginService.logoff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PatchMapping(value = "/active/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ativa um usuário", responses = {@ApiResponse(description = "Sucesso ao atualizar", responseCode = "204", content = @Content)})
    ResponseEntity<?> active(@PathVariable UUID id, String email){
        loginService.active(id, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/disable/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Desativa um usuário", responses = {@ApiResponse(description = "Sucesso ao atualizar", responseCode = "204", content = @Content)})
    ResponseEntity<?> disable(@PathVariable UUID id, String email){
        loginService.disable(id, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Exclui um usuário pelo ID", responses = @ApiResponse(description = "Sucesso ao deletar", responseCode = "204"))
    ResponseEntity<?> deleteById(@PathVariable UUID id){
        loginService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
