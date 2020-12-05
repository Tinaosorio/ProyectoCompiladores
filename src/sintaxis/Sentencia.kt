package sintaxis

import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * <Sentencia>::=<Mientras>|<Retorno>|<Para>|<InvocacionMetodo>|<DeclaracionVariable>
 * @author valentina osorio
</DeclaracionVariable></InvocacionMetodo></Para></Retorno></Mientras></Sentencia> */
abstract class Sentencia {
    abstract val arbolVisual: DefaultMutableTreeNode?
    abstract fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?)
    abstract fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?)
    abstract fun traducir(): String?
}
