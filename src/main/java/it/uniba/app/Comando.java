package it.uniba.app;

/**
 * Classe che rappresenta un comando di gioco.
 */
public abstract class Comando {
    private String nome;
    private String categoria;

    Comando(final String nomeParam, final String categoriaParam) {
        this.nome = nomeParam;
        this.categoria = categoriaParam;
    }

    abstract void esegui();

    abstract void getDescrizione();

    /**
     * Metodo che restituisce il nome del comando.
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che restituisce la categoria del comando.
     * @return
     */
    public String getCategoria() {
        return categoria;
    }
}
