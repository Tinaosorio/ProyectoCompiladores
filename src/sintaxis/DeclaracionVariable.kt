package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * <DeclaracionVariable>::=<IdentificadorVariable><TipoDato>"%"
 * @author valentina osorio
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


    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) { // TODO Auto-generated method stub
    }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {

    }


    override fun traducir(): String {
        var code = ""
        var st = ""
        if (tipoDato.lexema == "CADENA") {
            code = "String "
        }
        if (tipoDato.lexema == "ENTERO") {
            code = "int "
        }
        if (tipoDato.lexema == "BOOLEANO") {
            code = "boolean "
        }
        if (tipoDato.lexema == "REAL") {
            code = "float "
        }
        st = identificadorVariable?.lexema!!.replace("#".toRegex(), "")
        return "$code $st ;"
    }

}
