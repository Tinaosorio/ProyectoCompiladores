package sintactico

import lexico.Token

import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

 * <SentenciaDecremento>::=<IdentificadorVariable><Decremento>
*
*/
  class SentenciaDecremento(identificadorVariable: Token?, decremento: Token?) : Sentencia() {
    private var identificadorVariable: Token?
    private var decremento: Token?
    /**
     * @return the identificadorVariable
     */
    fun getIdentificadorVariable(): Token? {
        return identificadorVariable
    }

    /**
     * @return the decremento
     */
    fun getDecremento(): Token? {
        return decremento
    }

    /**
     * @param identificadorVariable the identificadorVariable to set
     */
    fun setIdentificadorVariable(identificadorVariable: Token) {
        this.identificadorVariable = identificadorVariable
    }

    /**
     * @param incremento the incremento to set
     */
    fun setDecremento(decremento: Token) {
        this.decremento = decremento
    }

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    override fun toString(): String {
        return "SentenciaDecremento [identificadorVariable=$identificadorVariable, decremento=$decremento]"
    }


    override val arbolVisual: DefaultMutableTreeNode?
        get(){val nodo = DefaultMutableTreeNode("Sentencia incremento")
            nodo.add(DefaultMutableTreeNode(identificadorVariable?.lexema))
            nodo.add(DefaultMutableTreeNode(decremento?.lexema))
            return nodo}


    /**
     * @param identificadorVariable
     * @param decremento
     */
    init {
        this.identificadorVariable = identificadorVariable
        this.decremento = decremento
    }
}
