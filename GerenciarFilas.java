import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GerenciarFilas {

    private Map<String, Queue<String>> filasDeEspera;

    public GerenciarFilas() {
        this.filasDeEspera = new HashMap<>();
    }

    public void entrarNaFila(String tituloLivro, String usuario) {
        filasDeEspera.putIfAbsent(tituloLivro, new LinkedList<>());
        filasDeEspera.get(tituloLivro).offer(usuario);
        System.out.println(usuario + " entrou na fila para: " + tituloLivro);
    }

    public String notificarProximo(String tituloLivro) {
        Queue<String> fila = filasDeEspera.get(tituloLivro);
        if (fila == null || fila.isEmpty()) {
            System.out.println("Fila vazia para: " + tituloLivro);
            return null;
        }
        String proximo = fila.poll();
        System.out.println(proximo + " foi notificado: " + tituloLivro + " disponivel!");
        return proximo;
    }

    public void exibirFila(String tituloLivro) {
        Queue<String> fila = filasDeEspera.get(tituloLivro);
        if (fila == null || fila.isEmpty()) {
            System.out.println("Fila vazia para: " + tituloLivro);
            return;
        }
        System.out.println("\nFila de espera - " + tituloLivro);
        int pos = 1;
        for (String usuario : fila) {
            System.out.println("  " + pos++ + ". " + usuario);
        }
    }

    public void exibirTodasFilas() {
        if (filasDeEspera.isEmpty()) {
            System.out.println("Nenhuma fila ativa.");
            return;
        }
        System.out.println("\n-- Filas de espera ativas --");
        for (Map.Entry<String, Queue<String>> entry : filasDeEspera.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                exibirFila(entry.getKey());
            }
        }
    }
}