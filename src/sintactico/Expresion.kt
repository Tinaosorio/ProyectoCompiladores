package sintactico

import javax.swing.tree.DefaultMutableTreeNode

/**
 *  * @author Valentina osorio

 * <Expresion>::= <ExpresionAritmetica> | <ExpresionRelacional> | <ExpresionCadena>
 *
 *
</ExpresionCadena></ExpresionRelacional></ExpresionAritmetica></Expresion> */
abstract class Expresion {
    abstract val arbolVisual: DefaultMutableTreeNode?
    abstract fun traducir(): String?
}
