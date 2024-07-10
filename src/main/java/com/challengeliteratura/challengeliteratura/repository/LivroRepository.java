package com.challengeliteratura.challengeliteratura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.challengeliteratura.challengeliteratura.entity.LivroEntity;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    @Query("SELECT l FROM LivroEntity l WHERE l.linguagem >= :idioma")
    List<LivroEntity> findForLanguaje(String idioma);

}
