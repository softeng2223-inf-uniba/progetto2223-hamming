package it.uniba.app.interfaccia;

import it.uniba.app.exceptions.InputNonFormattatoException;
import it.uniba.app.exceptions.PartitaNonIniziataException;
import it.uniba.app.exceptions.ParametriNonCorrettiException;

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

    abstract void esegui(String[] parametri) throws InputNonFormattatoException,
    PartitaNonIniziataException, ParametriNonCorrettiException;

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
