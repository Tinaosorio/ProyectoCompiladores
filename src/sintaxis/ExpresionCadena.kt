package sintaxis

import lexico.Categoria
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * Clase de expresion cadena
 *
 */
 class ExpresionCadena : Expresion {
    private var cadena: Token?
    private var palabraReservada: Token? = null
    private var expresion: Expresion? = null

    /**
     * Metodo constructor
     *
     * @param cadena
     */
    constructor(cadena: Token?) {
        this.cadena = cadena
    }

    /**
     * Metodo constructor
     *
     * @param cadena
     * @param palabraReservada
     * @param expresion
     */
    constructor(cadena: Token?, palabraReservada: Token?, expresion: Expresion?) {
        this.cadena = cadena
        this.palabraReservada = palabraReservada
        this.expresion = expresion
    }

    override val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Expresion Cadena")
            nodo.add(DefaultMutableTreeNode(cadena?.lexema))
            if (palabraReservada != null) {
                nodo.add(expresion!!.arbolVisual)
            }
            return nodo
        }

    override fun toString(): String {
        return ("ExpresionCadena [cadena=" + cadena + ", palabraReservada=" + palabraReservada + ", expresion="
                + expresion + "]")
    }

    override fun traducir(): String? {
        val exp = expresion!!.traducir()
        return cadena.toString() + "+" + exp
    }

    override fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?, ambito: Simbolo?) {}
    override fun obtenerTipo(errores: ArrayList<String?>?, ts: TablaSimbolos?): String? {
        if (cadena != null) {
            if (cadena!!.categoria === Categoria.CADENA_CARACTERES) {
                return "CADENA"
            }
        }
        return null
    }
}
