public class Livro {

    private String titulo;
    private String autor;
    private int anoPub;

    public Livro(String titulo, String autor, int anoPub) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPub = anoPub;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPub() {
        return anoPub;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Livro)) return false;
        Livro outro = (Livro) obj;
        return this.titulo.equalsIgnoreCase(outro.titulo)
                && this.autor.equalsIgnoreCase(outro.autor);
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode() * 31
                + autor.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" - " + autor + " (" + anoPub + ")";
    }
}