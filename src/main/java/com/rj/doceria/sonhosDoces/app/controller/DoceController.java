package com.rj.doceria.sonhosDoces.app.controller;

import com.rj.doceria.sonhosDoces.domain.model.Doce;
import com.rj.doceria.sonhosDoces.service.DoceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "doceria")
@Tag(name = "Doceria Sonhos Doces Controller", description = "Controladora que gere o cadastro de doces")
public class DoceController {

    private final DoceService doceService;

    public DoceController(DoceService doceService) {
        this.doceService = doceService;
    }

    @PostMapping(value = "/doce", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um doce", responses = {@ApiResponse(description = "Sucesso ao cadastrar", responseCode = "201", content = @Content)})
    ResponseEntity<Doce> save(@RequestBody Doce doce){
        return new ResponseEntity<>(doceService.save(doce), HttpStatus.CREATED);
    }

    @GetMapping(value = "/doces", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lista doces", responses = {@ApiResponse(description = "Sucesso ao listar os doces", responseCode = "200", content = @Content)})
    ResponseEntity<List<Doce>> findAll(){
        return new ResponseEntity<>(doceService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/doce/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Localiza um doce pelo ID", responses = {@ApiResponse(description = "Sucesso ao consultar o doce", responseCode = "200", content = @Content)})
    ResponseEntity<Doce> findById(@PathVariable UUID id){
        return new ResponseEntity<>(doceService.findById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/doce", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualiza um doce", responses = {@ApiResponse(description = "Sucesso ao atualizar", responseCode = "204", content = @Content)})
    ResponseEntity<?> update(@RequestBody Doce doce){
        doceService.save(doce);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/doce/{id}")
    @Operation(summary = "Exclui um doce pelo ID", responses = {@ApiResponse(description = "Sucesso ao apagar o doce", responseCode = "204", content = @Content)})
    ResponseEntity<?> deleteById(@PathVariable UUID id){
        doceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/doces", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui todos os doces", responses = {@ApiResponse(description = "Sucesso ao apagar todos os doces", responseCode = "204", content = @Content)})
    ResponseEntity<List<Doce>> deleteAll(){
        doceService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/doces/{List}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui uma lista de doces", responses = {@ApiResponse(description = "Sucesso ao apagar a lista de doces", responseCode = "204", content = @Content)})
    ResponseEntity<List<Doce>> deleteDoces(@RequestBody List<Doce> doces){
        doceService.deleteDoces(doces);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}