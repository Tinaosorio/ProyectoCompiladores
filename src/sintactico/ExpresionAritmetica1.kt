package sintactico

import lexico.Token
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author Valentina osorio

 * <expresionAritmetica> ::=
 * <termino>|<Termino><OAritmetico><agruIzq><ExpresionArit><AgrupDer>|<Termino><OAritmetico><Termino>
 *
 *
</Termino></OAritmetico></Termino></AgrupDer></ExpresionArit></agruIzq></OAritmetico></Termino></termino></expresionAritmetica> */
  class ExpresionAritmetica1 : Expresion {
    /**
     * @return the expresionA
     */
    /**
     * @param expresionA the expresionA to set
     */
    var expresionA: ExpresionAritmetica1? = null
    private var operadorAritmetico: Token? = null
    private var agruIzq: Token? = null
    private var agruDer: Token? = null
    /**
     * @return the termino
     */
    /**
     * @param termino the termino to set
     */
    var termino: Termino?
    /**
     * @return the termino2
     */
    /**
     * @param termino2 the termino2 to set
     */
    var termino2: Termino? = null

    constructor(termino: Termino?) {
        this.termino = termino
    }

    constructor(termino: Termino?, operador: Token?, termino2: Termino?) {
        this.termino = termino
        operadorAritmetico = operador
        this.termino2 = termino2
    }

    constructor(termino: Termino?, operador_aritmetico: Token?, agruIzq: Token?,
                expresionA: ExpresionAritmetica1?, agruDer: Token?) {
        this.termino = termino
        operadorAritmetico = operador_aritmetico
        this.agruIzq = agruIzq
        this.expresionA = expresionA
        this.agruDer = agruDer
    }

    /**
     * @return the operadorAritmetico
     */
    fun getOperadorAritmetico(): Token? {
        return operadorAritmetico
    }

    /**
     * @return the agruIzq
     */
    fun getAgruIzq(): Token? {
        return agruIzq
    }

    /**
     * @return the agruDer
     */
    fun getAgruDer(): Token? {
        return agruDer
    }

    /**
     * @param operadorAritmetico the operadorAritmetico to set
     */
    fun setOperadorAritmetico(operadorAritmetico: Token?) {
        this.operadorAritmetico = operadorAritmetico
    }

    /**
     * @param agruIzq the agruIzq to set
     */
    fun setAgruIzq(agruIzq: Token?) {
        this.agruIzq = agruIzq
    }

    /**
     * @param agruDer the agruDer to set
     */
    fun setAgruDer(agruDer: Token?) {
        this.agruDer = agruDer
    }

    override fun toString(): String {
        return ("ExpresionAritmetica [expresionA=" + expresionA.toString() + ", operadorAritmetico=" + operadorAritmetico
                + ", termino=" + termino.toString() + "]")
    }

    override val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Expresion Aritmetica")
            if (termino != null) {
                nodo.add(termino!!.arbolVisual)
                if (operadorAritmetico != null) {
                    nodo.add(DefaultMutableTreeNode(operadorAritmetico!!.lexema))
                    if (termino2 != null) {
                        nodo.add(termino2!!.arbolVisual)
                    } else if (agruIzq != null) {
                        if (expresionA != null) {
                            nodo.add(expresionA!!.arbolVisual)
                        }
                    }
                } else {
                    nodo.add(termino!!.arbolVisual)
                }
            }
            return nodo
        }

    override fun traducir(): String? {
        var operador = ""
        if (termino != null) {
            val term1 = termino!!.traducir()
            if (operadorAritmetico != null) {
                when (operadorAritmetico!!.lexema) {
                    "(+)" -> operador = "+"
                    "(-)" -> operador = "-"
                    "(/)" -> operador = "/"
                    "(*)" -> operador = "*"
                    else -> {
                    }
                }
                if (expresionA != null) {
                    val exp = expresionA!!.traducir()
                    return "$term1 $operador ($exp )"
                }
                if (termino2 != null) {
                    val term2 = termino2!!.traducir()
                    return "$term1 $operador $term2"
                }
            } else {
                return termino!!.traducir()
            }
        }
        return null
    }

}
