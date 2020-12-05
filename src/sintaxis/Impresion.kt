package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * Clase de impresion
 * @author valentina osorio
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

    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        exp?.analizarSemantica(error, tS, ambito)
    }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) { // TODO Auto-generated method stub
    }

    override fun traducir(): String? {
        val expresion = exp!!.traducir()
        return "JOptionPane.showMessageDialog(null, $expresion);"
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
