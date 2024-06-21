package entidade;

public class Categorias {
    int id;
    String nome_categoria;

    public Categorias(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public Categorias() {
        this.id = 0;
        this.nome_categoria ="";
    }

    public Categorias(int id, String nome_categoria) {
        this.id = id;
        this.nome_categoria = nome_categoria;
    }

    public int getId() {
        return id;
    }
    public String getNome_categoria() {
        return nome_categoria;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}
