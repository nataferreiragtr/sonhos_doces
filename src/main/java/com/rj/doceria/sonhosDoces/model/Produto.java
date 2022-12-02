package com.rj.doceria.sonhosDoces.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rj.doceria.sonhosDoces.enums.TipoDoce;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "doces")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID produtoId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private TipoDoce tipoDoce;

    @Column(nullable = false)
    private int qntd;

    @Column(nullable = false)
    private double price;
}
