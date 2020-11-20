package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author Valentina osorio
 * <DeclaracionVariable>::=<IdentificadorVariable><TipoDato>"%"

 </TipoDato></IdentificadorVariable></DeclaracionVariable> */
class DeclaracionVariable
/**
 * @param identificadorVariable
 * @param tipoDato
 * @param terminal
 */(
        /**
         * @param identificadorVariable the identificadorVariable to set
         */
        var identificadorVariable: Token?,
        /**
         * @param tipoDato the tipoDato to set
         */
        var tipoDato: Token) : Sentencia() {
    /**
     * @return the identificadorVariable
     */
    /**
     * @return the tipoDato
     */
    /**
     * @return the terminal
     */
    /**
     * @param terminal the terminal to set
     */
    var terminal: Token? = null

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    override fun toString(): String {
        return ("DeclaracionVariable [identificadorVariable=" + identificadorVariable + ", tipoDato=" + tipoDato
                + ", terminal=" + terminal + "]")
    }

    override val arbolVisual: DefaultMutableTreeNode?
        get() { val nodo = DefaultMutableTreeNode("Declaracion variable")
            nodo.add(DefaultMutableTreeNode(identificadorVariable?.lexema))
            nodo.add(DefaultMutableTreeNode(tipoDato.lexema))
            return nodo}


}
