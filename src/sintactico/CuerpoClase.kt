package sintactico

import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * Clase que representa el cuerpo de la clase, (Funciones y variables)
 *
 * @author Valentina

 */
class CuerpoClase {
    /**
     * @return the metodod
     */
    // Variables
    var metodo: Metodo? = null
        private set
    /**
     * @return the declaracionVariable
     */
    /**
     * @param declaracionVariable
     * the declaracionVariable to set
     */
    var declaracionVariable: DeclaracionVariable? = null
    /**
     * @return the cuerpoClase
     */
    /**
     * @param cuerpoClase
     * the cuerpoClase to set
     */
    var cuerpoClase: CuerpoClase? = null

    /**
     * Constructor con una funcion y posibilidad de agregar mas cuerpos de clase
     *
     * @param metodo
     * @param cuerpoClase
     */
    constructor(metodo: Metodo?, cuerpoClase: CuerpoClase?) : super() {
        this.metodo = metodo
        this.cuerpoClase = cuerpoClase
    }

    /**
     * Constructor con una funcion y posibilidad de agregar mas cuerpos de clase
     *
     * @param declaracionVariable
     * @param cuerpoClase
     */
    constructor(declaracionVariable: DeclaracionVariable?, cuerpoClase: CuerpoClase?) : super() {
        this.declaracionVariable = declaracionVariable
        this.cuerpoClase = cuerpoClase
    }

    /**
     * Constructor con solo un metodo
     *
     * @param funcion
     */
    constructor(metodo: Metodo?) : super() {
        this.metodo = metodo
    }

    /**
     * Constructor con solo una declaracion de variable
     *
     * @param declaracionVariable
     */
    constructor(declaracionVariable: DeclaracionVariable?) : super() {
        this.declaracionVariable = declaracionVariable
    }

    /**
     * @param funcion
     * the metodo to set
     */
    fun setFuncion(metodo: Metodo?) {
        this.metodo = metodo
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return if (metodo != null) {
            if (cuerpoClase != null) "CuerpoClase [funcion=" + metodo + "cuerpoClase=" + cuerpoClase + "]" else {
                "CuerpoClase [funcion=$metodo]"
            }
        } else if (declaracionVariable != null) {
            if (cuerpoClase != null) "CuerpoClase [declaracionVariable=" + declaracionVariable + "cuerpoClase=" + cuerpoClase + "]" else {
                "CuerpoClase [declaracionVariable=$declaracionVariable]"
            }
        } else {
            ""
        }
    }

    /**
     * Método para un nodo proviniente de otro cuerpo de clase
     *
     * @param node
     * @return
     */
    fun getArbolVisual(nodo: DefaultMutableTreeNode): DefaultMutableTreeNode {
        if (metodo != null) {
            nodo.add(metodo!!.arbolVisual)
            if (cuerpoClase != null) {
                return cuerpoClase!!.getArbolVisual(nodo)
            }
        } else if (declaracionVariable != null) {
            nodo.add(declaracionVariable!!.arbolVisual)
            if (cuerpoClase != null) {
                return cuerpoClase!!.getArbolVisual(nodo)
            }
        }
        return nodo
    }

    /**
     * Método para retornar de otro cuerpo de clase
     *
     * @param node
     * @return
     */
    val arbolVisual: DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Cuerpo clase")
            if (metodo != null) {
                nodo.add(metodo!!.arbolVisual)
            }
            if (declaracionVariable != null) {
                nodo.add(declaracionVariable!!.arbolVisual)
            }
            if (cuerpoClase != null) {
                nodo.add(cuerpoClase!!.arbolVisual)
            }
            return nodo
        }
    /**
     *
     * @param errores
     * @param ts
     */
/*
	 * public void analizarSemantica(ArrayList<String> errores, TablaSimbolos ts) {
		if (metodo != null) {
			metodo.analizarSemantica(errores, ts);
			if (cuerpoClase != null) {
				cuerpoClase.analizarSemantica(errores, ts);
			}
		} else if (declaracionVariable != null) {
			declaracionVariable.analizarSemantica(errores, ts, null);
			if (cuerpoClase != null) {
				cuerpoClase.analizarSemantica(errores, ts);
			}
		}
	}
	 */
    /**
     *
     * @param ts
     */
    /**
     * public void llenarTablaSimbolos(TablaSimbolos ts) {
     * if (funcion != null) {
     * ArrayList<String> tipos = new ArrayList<>();
     * if (funcion.getListaParametros() != null) {
     * for (Parametro param : funcion.getListaParametros()) {
     * tipos.add(param.getTipoDato().getLexema());
     * }
     * }
     *
     * funcion.setAmbito(ts.agregarFuncion(funcion.getIdentificadorFuncion().getLexema(),
     * funcion.getTipoRetorno().getTipoRetorno().getLexema(), tipos));
     * funcion.llenarTablaSimbolos(ts);
     *
     * if (cuerpoClase != null) {
     * cuerpoClase.llenarTablaSimbolos(ts);
     * }
     * } else if (declaracionVariable != null) {
     * declaracionVariable.llenarTablaSimbolos(ts, null);
     * if (cuerpoClase != null) {
     * cuerpoClase.llenarTablaSimbolos(ts);
     * }
     * }
     * }
    </String> */
    /**
     *
     *
     * @return
     */

}
