package semantico

import java.util.ArrayList

import sintaxis.Expresion

/**
 * Clase que representa un simbolo dentro del lenguaje
 *
 * @author valentina osorio
 */
class Simbolo {

    /**
     * @return the nombre
     */
    /**
     * @param nombre the nombre to set
     */
    var nombre: String? = null
    /**
     * @return the tipo
     */
    /**
     * @param tipo the tipo to set
     */
    var tipo: String? = null
    var ambito: Simbolo? = null
    var ambitoPadre: Simbolo? = null
    var fila: Int = 0
    var columna: Int = 0
    var isEsFuncion: Boolean = false
    /**
     * @return the numeroCIclo
     */
    /**
     * @param numeroCiclo the numeroCIclo to set
     */
    var numeroCiclo = 0
    /**
     * @return the numeroCondicional
     */
    /**
     * @param numeroCondicional the numeroCondicional to set
     */
    var numeroCondicional = 0
    /**
     * @return the tipos
     */
    /**
     * @param tipos the tipos to set
     */
    var tipos: ArrayList<String>? = null
    var expresion: Expresion? = null
    private var retorno: Boolean? = false

    /**
     * @param nombre
     * @param tipo
     * @param ambito
     * @param esFuncion
     * @param parametros
     */
    constructor(nombre: String, tipo: String, ambito: Simbolo) : super() {
        this.nombre = nombre
        this.tipo = tipo
        this.ambito = ambito
        isEsFuncion = false
    }

    /**
     * @param nombre
     * @param tipo
     * @param parametros
     * @param tipos
     */
    constructor(nombre: String?, tipo: String?, tipos: ArrayList<String>?) : super() {
        this.nombre = nombre
        this.tipo = tipo
        this.tipos = tipos
        isEsFuncion = true
    }

    /**
     * @param nombre
     * @param tipo
     * @param ambito
     * @param fila
     * @param columna
     */
    constructor(nombre: String?, tipo: String?, ambito: Simbolo?, fila: Int?, columna: Int?, expresion: Expresion?) {
        this.nombre = nombre
        this.tipo = tipo
        this.ambito = ambito
        this.fila = fila!!
        this.columna = columna!!
        this.expresion = expresion
    }

    fun getRetorno(): Boolean {
        return retorno!!
    }

    fun setRetorno(retorno: Boolean?) {
        this.retorno = retorno
    }

}
