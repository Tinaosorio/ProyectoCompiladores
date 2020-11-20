package sintactico
import lexico.Token
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 *  * @author Valentina osorio
 *
* <Retorno>::="RETURN"<Termino>"%"
*/
  class Retorno/**
 * @param palabra_reservada
 * @param termino
 * @param terminal
 */
(palabra_reservada:Token?, termino:Termino):Sentencia() {
    /**
     * @return the palabra_reservada
     */
    /**
     * @param palabra_reservada the palabra_reservada to set
     */
    var palabra_reservada:Token
    /**
     * @return the termino
     */
    /**
     * @param termino the termino to set
     */
    var termino:Termino
    /**
     * @return the terminal
     */
    /**
     * @param terminal the terminal to set
     */
      var terminal:Token? = null
    override val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Retorno")
            nodo.add(DefaultMutableTreeNode(termino.arbolVisual))
            return nodo
        }


    init{
        this.palabra_reservada = palabra_reservada!!
        this.termino = termino
    }
    /* (non-Javadoc)
       * @see java.lang.Object#toString()
       */
    override fun toString():String {
        return ("Retorno [palabra_reservada=" + palabra_reservada + ", termino=" + termino.toString() + ", terminal=" + terminal
                + "]")
    }

}
