package sintactico
import lexico.Token
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 * @author Valentina osorio

 * <InvocacionMetodo>::=<IdentificadorClase>"."<IdentificadorMetodo> "<-" [<ListaParametros>] ->" "%"

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


    init{
        this.identificadorClase = identificadorClase
        this.identificadorMetodo = identificadorMetodo
    }


}
