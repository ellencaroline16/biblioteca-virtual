import java.util.HashSet;
import java.util.Set;

/*  Ellen Caroline Santos Silva - Análise e Desenvolvimento de Sistemas 
 
 *   - Formativa 2: Biblioteca com LinkedList
 *   - Formativa 3: Fila de espera (Queue) e Histórico de navegação (Stack)
 *   - Somativa 01: Grafo de recomendações (HashMap<Livro, Set<Livro>>)
 *   - Somativa 02: Dijkstra para caminhos mais curtos
 
 */
public class Main {

    public static void main(String[] args) {

        // --- livros q eu gosto  ---
        Livro l1  = new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", 1997);
        Livro l2  = new Livro("Harry Potter e a Camara Secreta", "J.K. Rowling", 1998);
        Livro l3  = new Livro("Harry Potter e o Prisioneiro de Azkaban", "J.K. Rowling", 1999);
        Livro l4  = new Livro("Harry Potter e o Calice de Fogo", "J.K. Rowling", 2000);
        Livro l5  = new Livro("Harry Potter e a Ordem da Fenix", "J.K. Rowling", 2003);
        Livro l6  = new Livro("Harry Potter e o Enigma do Principe", "J.K. Rowling", 2005);
        Livro l7  = new Livro("Harry Potter e as Reliquias da Morte", "J.K. Rowling", 2007);
        Livro l8  = new Livro("A Culpa é das Estrelas", "John Green", 2012);
        Livro l9  = new Livro("Os Sete Maridos de Evelyn Hugo", "Taylor Jenkins Reid", 2017);
        Livro l10 = new Livro("O Fetiço para as Coisas Perdidas", "Jenna Evans Welch", 2022);

        // --- LinkedList ---
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.adicionarLivro(l1);
        biblioteca.adicionarLivro(l2);
        biblioteca.adicionarLivro(l3);
        biblioteca.adicionarLivro(l4);
        biblioteca.adicionarLivro(l5);
        biblioteca.adicionarLivro(l6);
        biblioteca.adicionarLivro(l7);
        biblioteca.adicionarLivro(l8);
        biblioteca.adicionarLivro(l9);
        biblioteca.adicionarLivro(l10);

        biblioteca.listarAcervo();

        System.out.println("\nBuscando livro:");
        Livro encontrado = biblioteca.buscarLivro("Harry Potter e a Pedra Filosofal");
        System.out.println(encontrado);

        System.out.println("\nRemovendo livro:");
        biblioteca.removerLivro("A Culpa é das Estrelas");

        // Queue
        GerenciarFilas filaEspera = new GerenciarFilas();
        filaEspera.entrarNaFila("Os Sete Maridos de Evelyn Hugo", "Barbara");
        filaEspera.entrarNaFila("Os Sete Maridos de Evelyn Hugo", "Francine");
        filaEspera.entrarNaFila("Os Sete Maridos de Evelyn Hugo", "Camila");
        filaEspera.entrarNaFila("Harry Potter e o Prisioneiro de Azkaban", "Dylan");
        filaEspera.entrarNaFila("Harry Potter e o Prisioneiro de Azkaban", "Miguel");

        filaEspera.exibirTodasFilas();

        System.out.println("\nLivro devolvido, notificando proximo:");
        filaEspera.notificarProximo("Os Sete Maridos de Evelyn Hugo");
        filaEspera.exibirFila("Os Sete Maridos de Evelyn Hugo");

        // Stack
        HistoricoNavegacao historico = new HistoricoNavegacao("Barbara");
        historico.registrarVisualizacao(l1);
        historico.registrarVisualizacao(l6);
        historico.registrarVisualizacao(l4);

        historico.exibirHistorico();

        System.out.println("\nVoltando ao livro anterior:");
        historico.voltarUltimo();
        historico.exibirHistorico();

        // HashMap
        GrafoRecomendacoes grafo = new GrafoRecomendacoes();

        grafo.adicionarLivro(l1);
        grafo.adicionarLivro(l2);
        grafo.adicionarLivro(l3);
        grafo.adicionarLivro(l4);
        grafo.adicionarLivro(l5);
        grafo.adicionarLivro(l6);
        grafo.adicionarLivro(l7);
        grafo.adicionarLivro(l8);
        grafo.adicionarLivro(l9);
        grafo.adicionarLivro(l10);

        // mesmo autor
        grafo.adicionarRelacao(l1, l2);
        grafo.adicionarRelacao(l1, l3);
        grafo.adicionarRelacao(l2, l3);
        grafo.adicionarRelacao(l4, l5);
        grafo.adicionarRelacao(l6, l7);
        grafo.adicionarRelacao(l9, l10);

        // tema/estilo parecido
        grafo.adicionarRelacao(l6, l4);
        grafo.adicionarRelacao(l6, l8);
        grafo.adicionarRelacao(l9, l4);
        grafo.adicionarRelacao(l10, l8);
        grafo.adicionarRelacao(l8, l4);
        grafo.adicionarRelacao(l7, l1);

        System.out.println("\nGrafo: " + grafo.totalNos() + " livros, " + grafo.totalArestas() + " relacoes");
        grafo.exibirGrafo();

        Set<Livro> lidos = new HashSet<>();
        lidos.add(l1);
        lidos.add(l4);
        grafo.exibirRecomendacoes("Barbara", lidos, 2);

        Set<Livro> lidos2 = new HashSet<>();
        lidos2.add(l9);
        grafo.exibirRecomendacoes("Dylan", lidos2, 1);

        // somativa 02: dijkstra
        System.out.println("\n-- Somativa 02: Dijkstra --");
        grafo.exibirDistancias(l1);
        grafo.exibirDistancias(l9);
    }
}