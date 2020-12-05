package sintaxis

import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/*
* <SentenciaIncremento>::=<IdentificadorVariable><Incremento>
*
*/
  class SentenciaIncremento(identificadorVariable: Token?, incremento: Token?) : Sentencia() {
    private var identificadorVariable: Token?
    private var incremento: Token?
    /**
     * @return the identificadorVariable
     */
    fun getIdentificadorVariable(): Token {
        return this.identificadorVariable!!
    }

    /**
     * @return the incremento
     */
    fun getIncremento(): Token {
        return this.incremento!!
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
    fun setIncremento(incremento: Token) {
        this.incremento = incremento
    }

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    override fun toString(): String {
        return "SentenciaIncremento [identificadorVariable=$identificadorVariable, incremento=$incremento]"
    }





    override val arbolVisual: DefaultMutableTreeNode?
        get(){val nodo = DefaultMutableTreeNode("Sentencia incremento")
            nodo.add(DefaultMutableTreeNode(identificadorVariable?.lexema))
            nodo.add(DefaultMutableTreeNode(incremento?.lexema))
            return nodo}
    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun traducir(): String {
        return identificadorVariable?.lexema?.replace("#", "").toString() + "++"
    }

    /**
     * @param identificadorVariable
     * @param incremento
     */
    init {
        this.identificadorVariable = identificadorVariable
        this.incremento = incremento
    }
}
