import java.util.LinkedList;

/**formativa 02 */

public class Biblioteca {

    private LinkedList<Livro> acervo;

    public Biblioteca() {
        this.acervo = new LinkedList<>();
    }

   
    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
        System.out.println("✔ Livro adicionado ao acervo com sucesso: " + livro);
    }

   
    public boolean removerLivro(String titulo) {
        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                acervo.remove(l);
                System.out.println("✔ Livro removido com sucesso: " + l);
                return true;
            }
        }
        System.out.println("✘ Livro não encontrado, tente novamente: " + titulo);
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
            System.out.println("Não há livros no acervo!");
            return;
        }
        System.out.println("\n===== ACERVO DA BIBLIOTECA =====");
        int i = 1;
        for (Livro l : acervo) {
            System.out.println(i++ + ". " + l);
        }
        System.out.println("================================");
    }

    public LinkedList<Livro> getAcervo() {
        return acervo;
    }
}
