package sintactico

import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

 * <UnidadDeCompilacion>::=<Paquete>[<listaImportaciones>]<Clase>
</Clase></listaImportaciones></Paquete></UnidadDeCompilacion> */
class UnidadDeCompilacion {
    var paquete: Paquete
        private set
    var listaImportaciones: ArrayList<Importacion>? = null
        private set
    var clase: Clase
        private set

    constructor(paquete: Paquete, listaImportaciones: ArrayList<Importacion>?, clase: Clase) : super() {
        this.paquete = paquete
        this.listaImportaciones = listaImportaciones
        this.clase = clase
    }

    /**
     * @param paquete
     * @param clase
     */
    constructor(paquete: Paquete, clase: Clase) : super() {
        this.paquete = paquete
        this.clase = clase
    }

    override fun toString(): String {
        return "UnidadDeCompilacion [" + paquete.toString() + "listaImportaciones=" + listaImportaciones.toString() + "Clase=" + clase.toString() + "]"
    }

    /**
     * Método para retornar el nodo de un arbol visual
     * @return
     */
    val arbolVisual: DefaultMutableTreeNode
        get() {

            val nodo = DefaultMutableTreeNode("Unidad de compilacion")
            print("asd")
            nodo.add(paquete.arbolVisual)
            print(nodo)
            if (listaImportaciones != null) {
                for (importacion in listaImportaciones!!) {
                    nodo.add(importacion.arbolVisual)
                }
            }
            print(nodo)
            nodo.add(clase.arbolVisual)
            return nodo
        }
}
