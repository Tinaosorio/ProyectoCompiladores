package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/*
* <Si>::="SI" "<-" <ExpresionRelacional> "->" "{" [<ListaSentencias>] "}"
*/    class Si : Sentencia {
    private var si: Token?
    /**
     * @return the expresion
     */
    /**
     * @param expresion the expresion to set
     */
    var expresion: ExpresionRelacional?
    /**
     * @return the listaSentencias
     */
    /**
     * @param listaSentencias the listaSentencias to set
     */
    var listaSentencias: ArrayList<Sentencia>? = null

    /**
     * @param si
     *
     * @param expresion
     */
    constructor(si: Token?, expresion: ExpresionRelacional?) : super() {
        this.si = si
        this.expresion = expresion
    }

    /**
     * @param si
     *
     * @param expresion
     *
     * @param listaSentencias
     */
    constructor(si: Token?, expresion: ExpresionRelacional?,
                listaSentencias: ArrayList<Sentencia>?) : super() {
        this.si = si
        this.expresion = expresion
        this.listaSentencias = listaSentencias
    }

    /**
     * @return the si
     */
    fun getSi(): Token? {
        return si
    }

    /**
     * @param si the si to set
     */
    fun setSi(si: Token) {
        this.si = si
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return "Si [si=" + si + ", expresion=" + expresion.toString() + ", listaSentencias=" + listaSentencias.toString() + "]"
    }


    override val arbolVisual: DefaultMutableTreeNode?
        get() {
            val nodo = DefaultMutableTreeNode("Si")
            nodo.add(expresion?.arbolVisual)
            if (listaSentencias != null) {
                for (sentencia in listaSentencias!!) {
                    nodo.add(sentencia.arbolVisual)
                }
            }
            return nodo
        }



    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {
        if (expresion != null) { //no entiendo
        }
        for (sentencia in listaSentencias!!) {
            sentencia.llenarTablaSimbolos(ts, erroresSemanticos, ambito)
        }
    }

    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        if (expresion != null) {
            expresion!!.analizarSemantica(error, tS, ambito)
        }
        for (sentencia in listaSentencias!!) {
            sentencia.analizarSemantica(error, tS, ambito)
        }
    }

    override fun traducir(): String {
        val code = "if"
        var sent = ""
        for (i in listaSentencias!!.indices) {
            sent += if (i == listaSentencias!!.size - 1) {
                listaSentencias!![i].traducir()
            } else {
                listaSentencias!![i].traducir() + ", "
            }
        }
        return code + " (" + expresion!!.traducir() + " ) " + " { \n" + sent + "\n }"
    }
}
