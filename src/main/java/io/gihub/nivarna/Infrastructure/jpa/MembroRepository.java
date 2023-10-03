package io.gihub.nivarna.Infrastructure.jpa;

import io.gihub.nivarna.domain.model.Membro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membro, Long>{

    @Query("select m from Membro m where (:nome is null or m.nome = :nome) and m.dataExclusao is null")
    Page<Membro> findMembro(String nome, Pageable pageable);

    @Query("update Membro m set m.dataExclusao = now() where m.id = :id")
    @Modifying
    void deleteMembro(Long id);
}
