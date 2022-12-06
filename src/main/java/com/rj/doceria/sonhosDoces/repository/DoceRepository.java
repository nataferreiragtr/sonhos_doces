package com.rj.doceria.sonhosDoces.repository;

import com.rj.doceria.sonhosDoces.domain.model.Doce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("doceRepository")
public interface DoceRepository extends JpaRepository<Doce, UUID> {
}
