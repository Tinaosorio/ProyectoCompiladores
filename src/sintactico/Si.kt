package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

 * <Si>::="SI" "<-" <ExpresionRelacional> "->" "{" [<ListaSentencias>] "}"
*/    class Si : Sentencia {
    private var si: Token?
    /**
     * @return the expresion
     */
    /**
     * @param expresion the expresion to set
     */
    var expresion: ExpresionRelacional?
    /**
     * @return the listaSentencias
     */
    /**
     * @param listaSentencias the listaSentencias to set
     */
    var listaSentencias: ArrayList<Sentencia>? = null

    /**
     * @param si
     *
     * @param expresion
     */
    constructor(si: Token?, expresion: ExpresionRelacional?) : super() {
        this.si = si
        this.expresion = expresion
    }

    /**
     * @param si
     *
     * @param expresion
     *
     * @param listaSentencias
     */
    constructor(si: Token?, expresion: ExpresionRelacional?,
                listaSentencias: ArrayList<Sentencia>?) : super() {
        this.si = si
        this.expresion = expresion
        this.listaSentencias = listaSentencias
    }

    /**
     * @return the si
     */
    fun getSi(): Token? {
        return si
    }

    /**
     * @param si the si to set
     */
    fun setSi(si: Token) {
        this.si = si
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return "Si [si=" + si + ", expresion=" + expresion.toString() + ", listaSentencias=" + listaSentencias.toString() + "]"
    }


    override val arbolVisual: DefaultMutableTreeNode?
        get() {
            val nodo = DefaultMutableTreeNode("Si")
            nodo.add(expresion?.arbolVisual)
            if (listaSentencias != null) {
                for (sentencia in listaSentencias!!) {
                    nodo.add(sentencia.arbolVisual)
                }
            }
            return nodo
        }

}
