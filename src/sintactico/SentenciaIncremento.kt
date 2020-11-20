package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

 * <SentenciaIncremento>::=<IdentificadorVariable><Incremento>
*
*/
  class SentenciaIncremento(identificadorVariable: Token?, incremento: Token?) : Sentencia() {
    private var identificadorVariable: Token?
    private var incremento: Token?
    /**
     * @return the identificadorVariable
     */
    fun getIdentificadorVariable(): Token {
        return this.identificadorVariable!!
    }

    /**
     * @return the incremento
     */
    fun getIncremento(): Token {
        return this.incremento!!
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
    fun setIncremento(incremento: Token) {
        this.incremento = incremento
    }

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    override fun toString(): String {
        return "SentenciaIncremento [identificadorVariable=$identificadorVariable, incremento=$incremento]"
    }





    override val arbolVisual: DefaultMutableTreeNode?
        get(){val nodo = DefaultMutableTreeNode("Sentencia incremento")
            nodo.add(DefaultMutableTreeNode(identificadorVariable?.lexema))
            nodo.add(DefaultMutableTreeNode(incremento?.lexema))
            return nodo}

    /**
     * @param identificadorVariable
     * @param incremento
     */
    init {
        this.identificadorVariable = identificadorVariable
        this.incremento = incremento
    }
}
