package com.challengeliteratura.challengeliteratura.controller;

import java.util.List;
import java.util.Scanner;

import com.challengeliteratura.challengeliteratura.entity.AutorEntity;
import com.challengeliteratura.challengeliteratura.entity.LivroEntity;
import com.challengeliteratura.challengeliteratura.mapper.ConverteDados;
import com.challengeliteratura.challengeliteratura.model.Resposta;
import com.challengeliteratura.challengeliteratura.repository.AutorRepository;
import com.challengeliteratura.challengeliteratura.repository.LivroRepository;
import com.challengeliteratura.challengeliteratura.service.ConsumoAPI;

public class LiteraturaController {

    private final String URL_BASE = "https://gutendex.com/books/?search=";
    
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();


    private LivroRepository livroRepositorio;
    private AutorRepository autorRepositorio;

    public LiteraturaController(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void menu() {
        var opcao = -1;
        while (opcao != 0) {
            String menu = """
                    Escolha uma opção pelo seu número:
                        1 - Buscar livro por título
                        2 - Listar livros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos em um determinado ano
                        5 - Listar livros por idioma
                        0 - Sair
                        """;
            System.out.println(menu);
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    buscarLivros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    buscarAutoresVivo();
                    break;
                case 5:
                    buscarPorIdiomas();
                    break;
                case 0:
                    System.out.println("Volte Logo....");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private void buscarLivros() {

        List<LivroEntity> livros = livroRepositorio.findAll();

        if (!livros.isEmpty()) {

            for (LivroEntity livro : livros) {
                System.out.println("\n\n---------- LIVROS -------\n");
                System.out.println(" Título: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNome());
                System.out.println(" Idioma: " + livro.getLinguagem());
                System.out.println(" Downloads: " + livro.getDownloads());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO ---- \n\n");
        }

    }

    private void buscarAutores() {
        List<AutorEntity> autores = autorRepositorio.findAll();

        if (!autores.isEmpty()) {
            for (AutorEntity autor : autores) {
                System.out.println("\n\n---------- Autores -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Ano de Nascimento: " + autor.getAnoNascimento());
                System.out.println(" Ano de Falecimento: " + autor.getDataDeFalecimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO ---- \n\n");

        }

    }

    private void buscarAutoresVivo() {
        System.out.println("Digite o ano para verificar os autores vivos: ");
        var ano = teclado.nextInt();
        teclado.nextLine();

        List<AutorEntity> autores = autorRepositorio.findYear(ano);

        if (!autores.isEmpty()) {
            for (AutorEntity autor : autores) {
                System.out.println("\n\n---------- Autores Vivos -------\n");
                System.out.println(" Nome: " + autor.getNome());
                System.out.println(" Ano de Nascimento: " + autor.getAnoNascimento());
                System.out.println(" Ano de Falecimento: " + autor.getDataDeFalecimento());
                System.out.println(" Livros: " + autor.getLivros().getTitulo());
                System.out.println("\n-------------------------\n\n");
            }
        } else {
            System.out.println("\n\n ----- NENHUM RESULTADO ENCONTRADO ---- \n\n");

        }
    }

    private void buscarPorIdiomas() {

        var menu = """
				Selecione um Idioma:
					1 - Espanhol
					2 - Inglês
				
					""";
        System.out.println(menu);
        var idioma = teclado.nextInt();
        teclado.nextLine();

        String selecao = "";

        if(idioma == 1) {
            selecao = "es";
        } else 	if(idioma == 2) {
            selecao = "en";
        }

        List<LivroEntity> livros = livroRepositorio.findForLanguaje(selecao);

        if (!livros.isEmpty()) {

            for (LivroEntity livro : livros) {
                System.out.println("\n\n---------- LIVROS POR IDIOMA-------\n");
                System.out.println(" Título: " + livro.getTitulo());
                System.out.println(" Autor: " + livro.getAutor().getNome());
                System.out.println(" Idioma: " + livro.getLinguagem());
                System.out.println(" Downloads: " + livro.getDownloads());
                System.out.println("\n-------------------------\n\n");
            }

        } else {
            System.out.println("\n\n ----- NÃO FORAM ENCONTRADOS RESULTADOS ---- \n\n");
        }


    }

    private void buscarLivroWeb() {
        Resposta dados = getDadosLivro();

        if (!dados.results().isEmpty()) {

            LivroEntity livro = new LivroEntity(dados.results().get(0));
            livro = livroRepositorio.save(livro);

        }

        System.out.println("Dados: ");
        System.out.println(dados);
    }

    private Resposta getDadosLivro() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        var titulo = teclado.nextLine();
        titulo = titulo.replace(" ", "%20");
        System.out.println("Título : " + titulo);
        System.out.println(URL_BASE + titulo);
        var json = consumoApi.obterDados(URL_BASE + titulo);
        System.out.println(json);
        Resposta dados = conversor.obterDados(json, Resposta.class);
        return dados;
    }

}
