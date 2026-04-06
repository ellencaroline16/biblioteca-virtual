import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GrafoRecomendacoes {

    private HashMap<Livro, Set<Livro>> grafo;

    public GrafoRecomendacoes() {
        this.grafo = new HashMap<>();
    }

    public void adicionarLivro(Livro livro) {
        grafo.putIfAbsent(livro, new HashSet<>());
    }

    public void adicionarRelacao(Livro a, Livro b) {
        grafo.putIfAbsent(a, new HashSet<>());
        grafo.putIfAbsent(b, new HashSet<>());
        grafo.get(a).add(b);
        grafo.get(b).add(a);
    }

    public Set<Livro> recomendacoesDirectas(Livro livro) {
        return grafo.getOrDefault(livro, new HashSet<>());
    }

    public Set<Livro> gerarRecomendacoes(Set<Livro> livrosLidos, int profundidade) {
        Set<Livro> recomendados = new HashSet<>();
        Set<Livro> visitados = new HashSet<>(livrosLidos);

        Queue<Livro> fila = new LinkedList<>(livrosLidos);
        int tamanhoNivel = fila.size();
        int contadorNivel = 0;
        int nivelAtual = 0;

        while (!fila.isEmpty() && nivelAtual < profundidade) {
            Livro atual = fila.poll();
            contadorNivel++;

            for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                if (!visitados.contains(vizinho)) {
                    recomendados.add(vizinho);
                    visitados.add(vizinho);
                    fila.offer(vizinho);
                }
            }

            if (contadorNivel == tamanhoNivel) {
                nivelAtual++;
                tamanhoNivel = fila.size();
                contadorNivel = 0;
            }
        }

        return recomendados;
    }

    public void exibirRecomendacoes(String nomeUsuario, Set<Livro> livrosLidos, int profundidade) {
        System.out.println("\n-- Recomendacoes para " + nomeUsuario + " --");

        if (livrosLidos.isEmpty()) {
            System.out.println("Nenhum livro lido informado.");
            return;
        }

        System.out.println("Baseado em:");
        for (Livro l : livrosLidos) {
            System.out.println("  - " + l);
        }

        Set<Livro> sugestoes = gerarRecomendacoes(livrosLidos, profundidade);

        if (sugestoes.isEmpty()) {
            System.out.println("Nenhuma sugestao encontrada.");
        } else {
            System.out.println("Voce pode gostar de:");
            int i = 1;
            for (Livro s : sugestoes) {
                System.out.println("  " + i++ + ". " + s);
            }
        }
    }

    public void exibirGrafo() {
        System.out.println("\n-- Grafo de recomendacoes --");
        for (Map.Entry<Livro, Set<Livro>> entry : grafo.entrySet()) {
            System.out.println(entry.getKey());
            for (Livro rec : entry.getValue()) {
                System.out.println("    -> " + rec);
            }
        }
    }

    public int totalNos() {
        return grafo.size();
    }

    public int totalArestas() {
        int total = 0;
        for (Set<Livro> vizinhos : grafo.values()) {
            total += vizinhos.size();
        }
        return total / 2;
    }
}