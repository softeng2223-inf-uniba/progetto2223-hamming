package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.ComandiException;

/**
 * <<Control>>
 * Classe che rappresenta un comando di gioco.
 * Il comando è rappresentato da un nome e una categoria.
 *
 * @author Gruppo Hamming
 */
public abstract class Comando {
    private String nome;
    private String categoria;

    Comando(final String nomeParam, final String categoriaParam) {
        this.nome = nomeParam;
        this.categoria = categoriaParam;
    }

    abstract void esegui(String[] parametri) throws ComandiException;

    abstract String getDescrizione();

    /**
     * Metodo che restituisce il nome del comando.
     * @return nome del comando
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che restituisce la categoria del comando.
     * @return categoria del comando
     */
    public String getCategoria() {
        return categoria;
    }
}
