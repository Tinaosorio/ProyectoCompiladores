package sintactico

import lexico.Token

import java.util.ArrayList

import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author valentina

 * <Asignacion>::=<TipoDato><identificadorVariable><OperadorAsignacion><Expresion> '%'
</Expresion></OperadorAsignacion></identificadorVariable></TipoDato></Asignacion> */

class Asignacion
/**
 * @param identificadorVariable
 * @param tipoDato
 * @param operadorAsignacion
 * @param expresion
 * @param terminal
 */
(
        /**
         * @return the tipoDato
         */
        /**
         * @param tipoDato the tipoDato to set
         */
        var tipoDato: Token?,
        /**
         * @return the identificadorVariable
         */
        /**
         * @param identificadorVariable the identificadorVariable to set
         */
        var identificadorVariable: Token?,
        /**
         * @return the operadorAsignacion
         */
        /**
         * @param operadorAsignacion the operadorAsignacion to set
         */
        var operadorAsignacion: Token?,
        /**
         * @return the expresion
         */
        /**
         * @param expresion the expresion to set
         */
        var expresion: Expresion?) : Sentencia() {
    /**
     * @return the terminal
     */
    /**
     * @param terminal the terminal to set
     */
    var terminal: Token? = null


    override fun toString(): String {
        return ("Asignacion [tipoDato=" + tipoDato + ", identificadorVariable=" + identificadorVariable
                + ", operadorAsignacion=" + operadorAsignacion + ", expresion=" + expresion + ", terminal=" + terminal
                + "]")
    }

    override val arbolVisual: DefaultMutableTreeNode?
        get() {
            val nodo = DefaultMutableTreeNode("Asignacion")
            nodo.add(DefaultMutableTreeNode(tipoDato!!.lexema))
            nodo.add(DefaultMutableTreeNode(identificadorVariable!!.lexema))
            nodo.add(DefaultMutableTreeNode(operadorAsignacion!!.lexema))
            nodo.add(expresion!!.arbolVisual)
            return nodo
        }



}
