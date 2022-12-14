package com.rj.doceria.sonhosDoces.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rj.doceria.sonhosDoces.domain.enums.TipoDoce;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "doces")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID doceId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDoce tipoDoce;

    @Column(nullable = false)
    private int qntd;

    @Column(nullable = false)
    private double preco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Doce doce = (Doce) o;
        return doceId != null && Objects.equals(doceId, doce.doceId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
