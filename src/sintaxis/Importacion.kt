package sintaxis

import lexico.Token
import javax.swing.tree.DefaultMutableTreeNode

/**
 * <Importacion> ::="IMPORTAR" nombrePaquete "." <IdentificadorClase> "%"
 * @author valentina osorio
</IdentificadorClase></Importacion> */
class Importacion(private val palabra_reservada_importar: Token?,
                  /**
         * @return  nombre_paquete
         */
                  private val nombre_paquete: String?, private val identificador_clase: Token?) {
    private val punto: Token? = null
    private val identificador_terminal: Token? = null
    /**
     * @return  palabra_reservada_importar
     */
    fun getPalabra_reservada_importar(): Token? {
        return palabra_reservada_importar
    }

    /**
     * @return  identificador_clase
     */
    fun getIdentificador_clase(): Token? {
        return identificador_clase
    }

    /**
     * @return  identificador_terminal
     */
    fun getIdentificador_terminal(): Token? {
        return identificador_terminal
    }

    /**
     * @return the punto
     */
    fun getPunto(): Token? {
        return punto
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return ("Importacion [palabra_reservada_importar=" + palabra_reservada_importar + ", nombre_paquete="
                + nombre_paquete + ", punto=" + punto + ", identificador_clase=" + identificador_clase
                + ", identificador_terminal=" + identificador_terminal + "]")
    }

    /**
     * Método para retornar el nodo de un arbol visual
     * @return
     */
    val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("importacion")
            nodo.add(DefaultMutableTreeNode(nombre_paquete))
            nodo.add(DefaultMutableTreeNode(identificador_clase!!.lexema))
            return nodo
        }

    fun traducir(): String {
        var code = ""
        if (palabra_reservada_importar!!.lexema.equals("IMPORTAR")) {
            code += "import "
        }
        return code + "  " + nombre_paquete + punto!!.lexema + identificador_clase!!.lexema + ";"
    }

}
