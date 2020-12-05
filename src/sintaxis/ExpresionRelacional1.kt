package sintaxis
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 *<ExpresionRelacional>::= ".(" <termino>| <ExpresionRelacional>|<ExpresionRelacional> <operadorLogico> <ExpresionRelacional> ")."
 *
 * @author valentina osorio
 */
 class ExpresionRelacional1:Expresion {
    /**
     * @return the expresionR
     */
    /**
     * @param expresionR the expresionR to set
     */
    lateinit var expresionR:ExpresionRelacional
    /**
     * @return the expresionA1
     */
    /**
     * @param expresionA1 the expresionA1 to set
     */
    lateinit var expresionA1:ExpresionRelacional
    /**
     * @return the operadorAritmatico
     */
    /**
     * @param operadorAritmatico the operadorAritmatico to set
     */
    lateinit var operadorAritmatico: Token
    /**
     * @return the termino
     */
    /**
     * @param termino the termino to set
     */
    lateinit var termino:Termino
    override val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Expresion Relacional")
            nodo.add(expresionR.arbolVisual)
            nodo.add(DefaultMutableTreeNode(operadorAritmatico.lexema))
            nodo.add(expresionA1.arbolVisual)
            return nodo
        }
    constructor(termino:Termino) {
        this.termino = termino
    }
    constructor(expresionR:ExpresionRelacional) {
        this.expresionR = expresionR
    }
    constructor(expresionR:ExpresionRelacional, operador_aritmetico:Token, expresionR1:ExpresionRelacional) {
        this.expresionR = expresionR
        this.operadorAritmatico = operador_aritmetico
        this.expresionA1 = expresionR1
    }
    constructor(termino:Termino, operador_aritmetico:Token, expresionR:ExpresionRelacional) {
        this.termino = termino
        this.expresionR = expresionR
        this.operadorAritmatico = operador_aritmetico
    }
    /* (non-Javadoc)
       * @see java.lang.Object#toString()
       */
    override fun toString():String {
        return ("ExpresionRelacional [expresionR=" + expresionR.toString() + ", expresionA1=" + expresionA1.toString()
                + ", operadorAritmatico=" + operadorAritmatico + ", termino=" + termino.toString() + "]")
    }
    override fun traducir():String? {
        // TODO Auto-generated method stub
        return null
    }

    override fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun obtenerTipo(errores: ArrayList<String?>?, ts: TablaSimbolos?): String? {
        return "BOOLEANO"}

}
