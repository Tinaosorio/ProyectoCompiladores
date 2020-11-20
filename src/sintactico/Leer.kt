package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author Valentina osorio

 *
 * Clase leer
 * <leer> ::= LEER idVariable "%"
 *
</leer> */
class Leer(id: Token?) : Sentencia() {
    private val palabraReservada: Token? = null
    private val id: Token?
    private val terminal: Token? = null
    //nodo.add(expresionA.getArbolVisual());
    override val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Leer")
            //nodo.add(expresionA.getArbolVisual());
            nodo.add(DefaultMutableTreeNode(id?.lexema))
            return nodo
        }

    override fun toString(): String {
        return "Leer [palabraReservada=$palabraReservada, id=$id, terminal=$terminal]"
    }


    /**
     * Metodo constructor de la clase
     * @param idontificadorVariable
     */
    init {
        this.id = id
    }
}
