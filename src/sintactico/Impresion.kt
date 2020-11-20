package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author Valentina osorio

 * Clase de impresion
 *
 */
class Impresion(palabraReservada: Token?, parIzq: Token?, exp: Expresion?, parDer: Token?, terminal: Token?) : Sentencia() {
    private val palabraReservada: Token?
    private val parIzq: Token?
    private val exp: Expresion?
    private val parDer: Token?
    private val terminal: Token?
    override val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Imprimir")
            val expresiones = ArrayList<Expresion?>()
            expresiones.add(exp)
            for (e in expresiones) {
                nodo.add(e!!.arbolVisual)
            }
            return nodo
        }

    override fun toString(): String {
        return ("Impresion [palabraReservada=" + palabraReservada + ", parIzq=" + parIzq + ", exp=" + exp + ", parDer="
                + parDer + ", finSentencia=" + terminal + "]")
    }


    /**
     * Metodo constructor de la clase
     *
     * @param palabraReservada
     * @param parIzq
     * @param exp
     * @param parDer
     * @param terminal
     */
    init {
        this.palabraReservada = palabraReservada
        this.parIzq = parIzq
        this.exp = exp
        this.parDer = parDer
        this.terminal = terminal
    }
}
