package sintaxis

import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * <Expresion>::= <ExpresionAritmetica> | <ExpresionRelacional> | <ExpresionCadena>
 *
 *
</ExpresionCadena></ExpresionRelacional></ExpresionAritmetica></Expresion> */
abstract class Expresion {
    abstract val arbolVisual: DefaultMutableTreeNode?
    abstract fun traducir(): String?
    abstract fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?, ambito: Simbolo?)
    abstract fun obtenerTipo(errores: ArrayList<String?>?, ts: TablaSimbolos?): String?
}
