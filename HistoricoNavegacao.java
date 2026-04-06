import java.util.Stack;

public class HistoricoNavegacao {

    private Stack<Livro> historico;
    private String nomeUsuario;

    public HistoricoNavegacao(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.historico = new Stack<>();
    }

    public void registrarVisualizacao(Livro livro) {
        historico.push(livro);
        System.out.println("[" + nomeUsuario + "] visualizou: " + livro);
    }

    public Livro voltarUltimo() {
        if (historico.isEmpty()) {
            System.out.println("Historico vazio.");
            return null;
        }
        Livro topo = historico.pop();
        System.out.println("Voltando para: " + topo);
        return topo;
    }

    public Livro verAtual() {
        if (historico.isEmpty()) return null;
        return historico.peek();
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhum livro visualizado ainda.");
            return;
        }
        System.out.println("\nHistorico de " + nomeUsuario);
        Stack<Livro> copia = new Stack<>();
        copia.addAll(historico);
        int pos = 1;
        while (!copia.isEmpty()) {
            System.out.println("  " + pos++ + ". " + copia.pop());
        }
    }

    public boolean estaVazio() {
        return historico.isEmpty();
    }
}