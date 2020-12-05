package sintaxis

import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author valentina osorio
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

    fun analizarSemantica(ts: TablaSimbolos?, errores: ArrayList<String?>?) {
        clase.cuerpoClase!!.analizarSemantica(errores, ts)
    }

    fun llenarTablaSimbolos(ts: TablaSimbolos?, errores: ArrayList<String?>?) {
        clase.cuerpoClase!!.llenarTablaSimbolos(ts, errores)
    }

    fun traducir(): String {
        var code = ""
        var p = ""
        p = paquete.traducir()
        for (i in listaImportaciones!!.indices) {
            code += if (i == listaImportaciones!!.size - 1) {
                listaImportaciones!![i].traducir()
            } else {
                listaImportaciones!![i].traducir() + ", "
            }
        }
        return p + " \n" + code + "\n" + clase.traducir() + ""
    }
}
