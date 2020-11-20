package sintactico

import lexico.Token
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

 *
 * <Parametro>::=<IdentificadorVariable><TipoDeDato>
 *
</TipoDeDato></IdentificadorVariable></Parametro> */
class Parametro(identificadorVariable: Token?, tipoDedato: Token?) {
    private val identificadorVariable: Token?
    private val tipoDedato: Token?
    /**
     * @return the identificadorVariable
     */
    fun getIdentificadorVariable(): Token? {
        return identificadorVariable
    }

    /**
     * @return  tipoDedato
     */
    fun getTipoDedato(): Token? {
        return tipoDedato
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return "Parametro [identificadorVariable=$identificadorVariable, tipoDedato=$tipoDedato]"
    }

    val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Parametro")
            nodo.add(DefaultMutableTreeNode(identificadorVariable?.lexema))
            nodo.add(DefaultMutableTreeNode(tipoDedato?.lexema))
            return nodo
        }

    fun traducir(): String {
        var code = ""
        var st = ""
        if (tipoDedato?.lexema.equals("CADENA")) {
            code += "String "
        }
        if (tipoDedato?.lexema.equals("ENTERO")) {
            code += "int "
        }
        if (tipoDedato?.lexema.equals("BOOLEANO")) {
            code += "boolean "
        }
        if (tipoDedato?.lexema.equals("REAL")) {
            code += "float "
        }
        st = identificadorVariable?.lexema?.replace("#", "") !!
        return "$code $st"
    }

    init {
        this.identificadorVariable = identificadorVariable
        this.tipoDedato = tipoDedato
    }
}
