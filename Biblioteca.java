import java.util.LinkedList;

// formativa 02

public class Biblioteca {

    private LinkedList<Livro> acervo;

    public Biblioteca() {
        this.acervo = new LinkedList<>();
    }

    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
        System.out.println("Livro adicionado: " + livro);
    }

    public boolean removerLivro(String titulo) {
        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(l);
                System.out.println("Livro removido: " + l);
                return true;
            }
        }
        System.out.println("Livro nao encontrado: " + titulo);
        return false;
    }

    public Livro buscarLivro(String titulo) {
        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    public void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("Nenhum livro no acervo.");
            return;
        }
        System.out.println("\nLista de livros:");
        int i = 1;
        for (Livro l : acervo) {
            System.out.println(i++ + ". " + l);
        }
    }

    public LinkedList<Livro> getAcervo() {
        return acervo;
    }
}