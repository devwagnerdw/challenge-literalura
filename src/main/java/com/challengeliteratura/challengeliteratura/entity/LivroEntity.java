package com.challengeliteratura.challengeliteratura.entity;

import com.challengeliteratura.challengeliteratura.model.Autor;
import com.challengeliteratura.challengeliteratura.model.Livro;
import com.challengeliteratura.challengeliteratura.util.CadenasUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Livro")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String linguagem;
    private Integer downloads;
    @OneToOne(mappedBy = "livros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AutorEntity autor;

    public LivroEntity() {

    }

    public LivroEntity(Livro livro) {
        this.titulo = CadenasUtil.limitarLongitud(livro.title(), 200);
        this.downloads = livro.download();
        if (!livro.languages().isEmpty())
            this.linguagem = livro.languages().get(0);
        if (!livro.autores().isEmpty()) {
            for (Autor autor : livro.autores()) {
                this.autor = new AutorEntity(autor);
                break;
            }
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "LivroEntity [id=" + id + ", titulo=" + titulo + ", linguagem=" + linguagem + ", downloads=" + downloads
                + ", autores=" + autor + "]";
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

}
