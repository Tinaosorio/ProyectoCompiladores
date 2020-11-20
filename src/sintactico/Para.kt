package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author Valentina osorio

 *
* <Para>::="PARA" "<-" <DeclaracionVarible><Expresion><Incremento>"->" "{" [<ListaSentencias>] "}"
* 			|"PARA" "<-" <DeclaracionVarible><Expresion><Decremento>"->" "{" [<ListaSentencias>] "}"
*/
  class Para : Sentencia() {
    private var para: Token? = null
    /**
     * @return the expresion
     */
    /**
     * @param expresion the expresion to set
     */
    var expresion: Expresion? = null
    /**
     * @return the decremento
     */
    /**
     * @param decremento the decremento to set
     */
    var decremento: SentenciaDecremento? = null
    /**
     * @return the incremento
     */
    /**
     * @param incremento the incremento to set
     */
    var incremento: SentenciaIncremento? = null
    /**
     * @return the listaSentencias
     */
    /**
     * @param listaSentencias the listaSentencias to set
     */
    var listaSentencias: ArrayList<Sentencia>? = null

    /**
     * @return the para
     */
    fun getPara(): Token? {
        return para
    }

    /**
     * @param para the para to set
     */
    fun setPara(para: Token?) {
        this.para = para
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return ("Para [para=" + para + ", expresion=" + expresion.toString() + ", decremento="
                + decremento + ", incremento=" + incremento + ", listaSentencias=" + listaSentencias + "]")
    }

    override val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Para")
            nodo.add(expresion!!.arbolVisual)
            if (decremento != null) {
                if (listaSentencias != null) {
                    for (sentencia in listaSentencias!!) {
                        nodo.add(sentencia.arbolVisual)
                    }
                }
            }
            if (incremento != null) {
                if (listaSentencias != null) {
                    for (sentencia in listaSentencias!!) {
                        nodo.add(sentencia.arbolVisual)
                    }
                }
            }
            return nodo
        }
}
