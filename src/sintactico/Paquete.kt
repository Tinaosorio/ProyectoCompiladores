package sintactico

import lexico.Token
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio
 *
 * <Paquete>::="PAQUETE" nombrePaquete "%"

</Paquete> */
class Paquete(private val palabra_reservada: Token?,
              /**
               * @return  nombrePaquete
               */
              private val nombrePaquete: String?) {
    private val identificador_terminal: Token? = null
    /**
     * @return  palabra_reservada
     */
    fun getPalabra_reservada(): Token? {
        return palabra_reservada
    }

    /**
     * @return the identificador_terminal
     */
    fun getIdentificador_terminal(): Token? {
        return identificador_terminal
    }

    /* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
    override fun toString(): String {
        return ("Paquete [palabra_reservada=" + palabra_reservada + ", nombrePaquete=" + nombrePaquete
                + ", identificador_terminal=" + identificador_terminal + "]")
    }

    /**
     * Método para retornar el nodo de un arbol visual
     * @return
     */
    val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Paquete")
            nodo.add(DefaultMutableTreeNode(nombrePaquete))
            return nodo
        }

    fun traducir(): String {
        val st = nombrePaquete!!.replace("_".toRegex(), "")
        return "package $st;"
    }


}
