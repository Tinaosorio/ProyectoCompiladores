package sintactico


import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author Valentina osorio
 *
 * <Sentencia>::=<Mientras>|<Retorno>|<Para>|<InvocacionMetodo>|<DeclaracionVariable>

</DeclaracionVariable></InvocacionMetodo></Para></Retorno></Mientras></Sentencia> */
abstract class Sentencia {
    abstract val arbolVisual: DefaultMutableTreeNode?
}
