package sintaxis
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 * Clase de una expresion relacional
 * <expresionRelacional> ::= <expresionAritmetica> <operadorRelacional> <expresion Aritmetica> | <expresion Cadena>
 * <operadorRelacional> <expresion Cadena>
 *
 * @author Viviana Andrea Guerrero
 * @author Mauricio Espinosa Chacon
 * @author Luis Fernando Medina Gallego
 */
 class ExpresionRelacional:Expresion {
    //TODO
      var expresionAritmetica:ExpresionAritmetica1? = null
    private   var expresionAritmetica2:ExpresionAritmetica1? = null
    private var opRelacional: Token?
    private   var expresionC:ExpresionCadena? = null
    private   var expresionC1:ExpresionCadena? = null
    /**
     * Metodo para agregar un nodo al arbol
     *
     * @return
     */
    override val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Expresion Relacional")
            if (expresionAritmetica != null)
            {
                nodo.add(expresionAritmetica!!.arbolVisual)
                if (opRelacional != null)
                {
                    nodo.add(DefaultMutableTreeNode(opRelacional!!.lexema))
                    if (expresionAritmetica2 != null)
                    {
                        nodo.add(expresionAritmetica2!!.arbolVisual)
                    }
                }
            }
            else if (expresionC != null)
            {
                nodo.add(expresionC!!.arbolVisual)
                if (opRelacional != null)
                {
                    nodo.add(DefaultMutableTreeNode(opRelacional!!.lexema))
                    if (expresionC1 != null)
                    {
                        nodo.add(expresionC1!!.arbolVisual)
                    }
                }
            }
            return nodo
        }
    /**
     * Metodo constructor de la clase
     *
     * @param expresionA
     * @param opRelacional
     * @param expresionA2
     */
    constructor(expresionA:ExpresionAritmetica1?, opRelacional:Token?, expresionA2:ExpresionAritmetica1?) {
        this.expresionAritmetica = expresionA
        this.opRelacional = opRelacional
        this.expresionAritmetica2 = expresionA2
    }
    /**
     * @param opRelacional
     * @param expresionC
     * @param expresionC1
     */
    constructor(expresionC:ExpresionCadena?, opRelacional:Token?, expresionC1:ExpresionCadena?) : super() {
        this.opRelacional = opRelacional
        this.expresionC = expresionC
        this.expresionC1 = expresionC1
    }
    override fun traducir():String? {
        //TODO
        var operador = ""
        if (expresionAritmetica != null)
        {
            val exp1 = expresionAritmetica!!.traducir()
            if (opRelacional != null)
            {
                when (opRelacional!!.lexema) {
                    ">>" -> operador = ">"
                    ">:" -> operador = ">="
                    "<<" -> operador = "<"
                    "<:" -> operador = "<="
                    "-:" -> operador = "!="
                    ":" -> operador = "=="
                    else -> {}
                }
                if (expresionAritmetica2 != null)
                {
                    val exp2 = expresionAritmetica2!!.traducir()
                    return exp1 + " " + operador + " (" + exp2 + " )"
                }
            }
        }
        return null
    }

    override fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?, ambito: Simbolo?) {
        if (expresionAritmetica != null)
        {
            expresionAritmetica!!.analizarSemantica(errores, ts, ambito)
            if (expresionAritmetica2 != null)
            {
                expresionAritmetica2!!.analizarSemantica(errores, ts, ambito)
            }
        }
        if (expresionC != null)
        {
            expresionC!!.analizarSemantica(errores, ts, ambito)
            if (expresionC1 != null)
            {
                analizarSemantica(errores, ts, ambito)
            }
        } }

    override fun obtenerTipo(errores: ArrayList<String?>?, ts: TablaSimbolos?): String? {

        return "BOOLEANO"
    }
}
