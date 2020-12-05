package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * Clase de leer
 * <leer> ::= LEER idVariable "%"
 ** @author valentina osorio
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

    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) { // TODO Auto-generated method stub
    }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) { // TODO Auto-generated method stub
    }

    override fun traducir(): String? {
        val idVariable: String = id?.lexema?.replace("#", "")!!
        return "${idVariable}=JOptionPane.showInputDialog(null);"
    }

    /**
     * Metodo constructor de la clase
     * @param idontificadorVariable
     */
    init {
        this.id = id
    }
}
