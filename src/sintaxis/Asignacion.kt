package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos

import java.util.ArrayList

import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author valentina osorio
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

    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        val s = tS?.buscarSimboloVariables(identificadorVariable!!.lexema!!, ambito!!)
        if (s == null) {
            error?.add("la variable " + identificadorVariable!!.lexema + " no existe")
        } else {
            if (expresion != null) {
                if (s.tipo != expresion!!.obtenerTipo(error, tS)) {
                    error?.add("El tipo de la expresion no es correcto")
                }
            }
        }
        if (expresion != null) {
            expresion!!.analizarSemantica(error, tS, ambito)
        }  }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun traducir(): String {
        var code = ""
        var id = ""
        var tipo = ""
        if (tipoDato!!.lexema == "CADENA") {
            tipo = "String "
        }
        if (tipoDato!!.lexema == "ENTERO") {
            tipo = "int "
        }
        if (tipoDato!!.lexema == "BOOLEANO") {
            tipo = "boolean "
        }

        if (tipoDato!!.lexema == "REAL") {
            tipo = "float "
        }
        if (operadorAsignacion!!.lexema == ":+") {
            code = "+="
        }
        if (operadorAsignacion!!.lexema == ":-") {
            code = "-="
        }
        if (operadorAsignacion!!.lexema == "::") {
            code = "="
        }
        if (operadorAsignacion!!.lexema == ":*") {
            code = "*="
        }
        if (operadorAsignacion!!.lexema == ":/") {
            code = "/="

        }
        id = identificadorVariable!!.lexema!!.replace("#".toRegex(), "")
        return tipo + " " + id + " " + code + " " + expresion!!.traducir() + ";"
    }





}
