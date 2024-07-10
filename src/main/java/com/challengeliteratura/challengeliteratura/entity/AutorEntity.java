package com.challengeliteratura.challengeliteratura.entity;

import com.challengeliteratura.challengeliteratura.model.Autor;
import com.challengeliteratura.challengeliteratura.util.CadenasUtil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Autor")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer dataDeFalecimento;


    @OneToOne
    @JoinTable(
            name = "Livro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private LivroEntity livros;


    public AutorEntity() {

    }

    public AutorEntity(Autor autor) {
        this.nome = CadenasUtil.limitarLongitud(autor.nome(), 200);

        if (autor.anoDeNascimento() == null)
            this.anoNascimento = 1980;
        else
            this.anoNascimento = autor.anoDeNascimento();

        if (autor.anoDaMorte() == null)
            this.dataDeFalecimento = 3022;
        else
            this.dataDeFalecimento = autor.anoDaMorte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getDataDeFalecimento() {
        return dataDeFalecimento;
    }

    public void setDataDeFalecimento(Integer dataDeFalecimento) {
        this.dataDeFalecimento = dataDeFalecimento;
    }


    @Override
    public String toString() {
        return "AutorEntity [id=" + id + ", nome=" + nome + ", Ano de Nascimento=" + anoNascimento
                + ", Data De Falecimento=" + dataDeFalecimento + ", livro="  + "]";
    }

    public LivroEntity getLivros() {
        return livros;
    }

    public void setLivros(LivroEntity livros) {
        this.livros = livros;
    }

}