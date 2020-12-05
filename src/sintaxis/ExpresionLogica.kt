package sintaxis
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/*
* <ExpresionLogica>::=<ExpresionRelacional><OperadorLogico><ExpresionRelacional>
*/
  class ExpresionLogica/**
 * @param expresion1
 * @param operadorLogico
 * @param expresion2
 */
(expresion1:ExpresionRelacional, operadorLogico:Token?, expresion2:ExpresionRelacional):Expresion() {
    /**
     * @return the expresion1
     */
    /**
     * @param expresion1 the expresion1 to set
     */
    var expresion1:ExpresionRelacional
    /**
     * @return the operadorLogico
     */
    /**
     * @param operadorLogico the operadorLogico to set
     */
    var operadorLogico:Token?
    /**
     * @return the expresion2
     */
    /**
     * @param expresion2 the expresion2 to set
     */
    var expresion2:ExpresionRelacional
    override val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Expresion logica")
            nodo.add(expresion1.arbolVisual)
            nodo.add(DefaultMutableTreeNode(operadorLogico?.lexema))
            nodo.add(expresion2.arbolVisual)
            return nodo
        }
    init{
        this.expresion1 = expresion1
        this.operadorLogico = operadorLogico
        this.expresion2 = expresion2
    }
    /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
    override fun toString():String {
        return ("ExpresionLogica [expresion1=" + expresion1.toString() + ", operadorLogico=" + operadorLogico + ", expresion2="
                + expresion2.toString() + "]")
    }
    override fun traducir():String? {
        var operador = ""
        if (expresion1 != null)
        {
            val expA = expresion1.traducir()
            if (operadorLogico != null)
            {
                when (operadorLogico!!.lexema) {
                    "&:" -> operador = "&&"
                    "|:" -> operador = "||"
                    "~:" -> operador = "!"
                    else -> {}
                }
                if (expresion2 != null)
                {
                    val exp = expresion2.traducir()
                    return expA + " " + operador + " " + exp
                }
            }
        }
        return null
    }

    override fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun obtenerTipo(errores: ArrayList<String?>?, ts: TablaSimbolos?): String? {
        return "BOOLEANO"
    }

}
