package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/*
* <SentenciaDecremento>::=<IdentificadorVariable><Decremento>
*
*/
  class SentenciaDecremento(identificadorVariable: Token?, decremento: Token?) : Sentencia() {
    private var identificadorVariable: Token?
    private var decremento: Token?
    /**
     * @return the identificadorVariable
     */
    fun getIdentificadorVariable(): Token? {
        return identificadorVariable
    }

    /**
     * @return the decremento
     */
    fun getDecremento(): Token? {
        return decremento
    }

    /**
     * @param identificadorVariable the identificadorVariable to set
     */
    fun setIdentificadorVariable(identificadorVariable: Token) {
        this.identificadorVariable = identificadorVariable
    }

    /**
     * @param incremento the incremento to set
     */
    fun setDecremento(decremento: Token) {
        this.decremento = decremento
    }

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    override fun toString(): String {
        return "SentenciaDecremento [identificadorVariable=$identificadorVariable, decremento=$decremento]"
    }


    override val arbolVisual: DefaultMutableTreeNode?
        get(){val nodo = DefaultMutableTreeNode("Sentencia incremento")
            nodo.add(DefaultMutableTreeNode(identificadorVariable?.lexema))
            nodo.add(DefaultMutableTreeNode(decremento?.lexema))
            return nodo}
    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun traducir(): String {
        return identificadorVariable?.lexema?.replace("#", "").toString() + "--"
    }

    /**
     * @param identificadorVariable
     * @param decremento
     */
    init {
        this.identificadorVariable = identificadorVariable
        this.decremento = decremento
    }
}
