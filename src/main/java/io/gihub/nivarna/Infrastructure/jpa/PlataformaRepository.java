package io.gihub.nivarna.Infrastructure.jpa;

import io.gihub.nivarna.domain.model.Plataforma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {


    @Query("SELECT p FROM Plataforma p WHERE (:nome IS NULL OR p.nome = :nome) and p.dataExclusao IS NULL")
    Page<Plataforma> findPlataforma(@Param("nome") String nome, Pageable pageable);

    @Query("update Plataforma p set p.dataExclusao = now() where p.id = :id")
    @Modifying
    void deletePlataforma(Long id);

}
