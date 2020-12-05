package sintaxis

import lexico.Categoria
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/*
* <Termino>::=<IdentificadorVariable>|<Entero>|<Real>
*/
class Termino(identificadorVariable: Token?) {
    internal var termino: Token?

    /**
     * @return the termino
     */
    fun getTermino(): Token? {
        return termino
    }

    /**
     * @param termino the termino to set
     */
    fun setTermino(termino: Token?) {
        this.termino = termino
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return "Termino [termino=$termino]"
    }

    val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Termino")
            if (termino != null) {
                nodo.add(DefaultMutableTreeNode(termino!!.lexema))
            }
            return nodo
        }

    fun traducir(): String {
        var st = ""
        if (termino != null && termino!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
            st = termino!!.lexema!!.replace("#", "")
            return st
        }
        return termino!!.lexema!!
    }

    fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?, ambito: Simbolo?) {
        if (termino != null) {
            for (simbolo in ts?.tablaSimbolos!!) {
                if (simbolo.ambito != null) {
                    if (termino!!.lexema.equals(simbolo.nombre) && !simbolo.isEsFuncion
                            && simbolo.ambito!!.equals(ambito)) {
                        return
                    }
                } else {
                    if (termino!!.lexema.equals(simbolo.nombre) && !simbolo.isEsFuncion) {
                        return
                    }
                }
            }
            if (ambito?.ambitoPadre != null) {
                analizarSemantica(errores, ts, ambito.ambitoPadre!!)
            } else {
                errores!!.add(termino!!.lexema.toString() + " No se encontró la variable invocada")
            }
        }
    }

    fun obtenerTipo(errores: ArrayList<String?>?, ts: TablaSimbolos?): String? {
        if (termino?.categoria === Categoria.ENTERO) {
            return "ENTERO"
        } else if (termino?.categoria === Categoria.REAL) {
            return "REAL"
        } else if (termino?.categoria === Categoria.CADENA_CARACTERES) {
            return "CADENA"
        }
        return null
    }

    init {
        termino = identificadorVariable
    }
}
