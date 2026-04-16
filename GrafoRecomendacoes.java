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

    // dijkstra: calcula a distancia de um livro origem para todos os outros
    public static Map<Livro, Integer> djikstraSimples(HashMap<Livro, Set<Livro>> grafo, Livro origem) {
        Map<Livro, Integer> distancias = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();

        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Livro atual = fila.poll();
            int distanciaAtual = distancias.get(atual);

            for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                if (!distancias.containsKey(vizinho)) {
                    distancias.put(vizinho, distanciaAtual + 1);
                    fila.add(vizinho);
                }
            }
        }
        return distancias;
    }

    // exibe os livros ordenados do mais proximo ao mais distante
    public void exibirDistancias(Livro origem) {
        System.out.println("\n-- Distancias a partir de: " + origem.getTitulo() + " --");

        Map<Livro, Integer> distancias = djikstraSimples(grafo, origem);

        distancias.entrySet()
            .stream()
            .filter(e -> !e.getKey().equals(origem))
            .sorted(Map.Entry.comparingByValue())
            .forEach(e -> System.out.println("  distancia " + e.getValue() + ": " + e.getKey()));
    }

    public HashMap<Livro, Set<Livro>> getGrafo() {
        return grafo;
    }
}