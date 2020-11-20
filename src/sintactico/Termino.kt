package sintactico

import lexico.Categoria
import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

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

    init {
        termino = identificadorVariable
    }
}
