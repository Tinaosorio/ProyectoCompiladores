package sintaxis
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 * <InvocacionMetodo>::=<IdentificadorClase>"."<IdentificadorMetodo> "<-" [<ListaParametros>] ->" "%"
 * @author valentina osorio
 */
 class InvocacionMetodo/**
 * @param identificadorClase
 * @param identificadorMetodo
 * @param simboloA
 *
 * @param simboloC
 * @param terminal
 */
(identificadorClase:Token?, identificadorMetodo:Token?):Sentencia() {
    /**
     * @return the identificadorClase
     */
    /**
     * @param identificadorClase the identificadorClase to set
     */
    var identificadorClase:Token?
    /**
     * @return the identificadorMetodo
     */
    /**
     * @param identificadorMetodo the identificadorMetodo to set
     */
    var identificadorMetodo:Token?
    override val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Invocacion metodo")
            nodo.add(DefaultMutableTreeNode(identificadorClase?.lexema))
            nodo.add(DefaultMutableTreeNode(identificadorMetodo?.lexema))
            return nodo
        }

    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init{
        this.identificadorClase = identificadorClase
        this.identificadorMetodo = identificadorMetodo
    }


    override fun traducir():String {
        val idClase = identificadorClase?.lexema?.replace("@", "")?.toLowerCase()
        val idMetodo = identificadorMetodo?.lexema?.replace("$", "")?.toLowerCase()
        return idClase + "." + idMetodo + "(" + ");"
    }
}
