package sintactico
import lexico.Token
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 * @author Valentina osorio

 *
 * <Metodo>::=<Modificador> <TipoRetorno> <IdentificadorMetodo> "<-"
 * [<ListaParametros>]" ->" "{" [<ListaSentencias>] "}"
 *
 */
 class Metodo {
    /**
     * @return modificador
     */
    val modificador:Token?
    /**
     * @return tipoRetorno
     */
    val tipoRetorno:Token?
    /**
     * @return identificadorMetodo
     */
    val identificadorMetodo:Token?
    /**
     * @return listaParametros
     */
    var listaParametros:ArrayList<Parametro>? = null
    /**
     * @return listaSentencias
     */
    var listaSentencias:ArrayList<Sentencia>? = null
    /**
     * @return the ambito
     */
    /**
     * @param ambito the ambito to set
     */
    val tipoParametros:ArrayList<String>
        get() {
            val l = ArrayList<String>()
            for (p in this!!.listaParametros!!)
            {
                l.add(p.getTipoDedato()?.lexema!!)
            }
            return l
        }
    /**
     * Método para retornar el nodo de un arbol visual
     * @return
     */
    val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Metodo")
            nodo.add(DefaultMutableTreeNode(tipoRetorno!!.lexema))
            nodo.add(DefaultMutableTreeNode(identificadorMetodo?.lexema))
            if (listaParametros != null)
            {
                for (parametro in listaParametros!!)
                {
                    nodo.add(parametro.arbolVisual)
                }
            }
            if (listaSentencias != null)
            {
                for (sentencia in listaSentencias!!)
                {
                    nodo.add(sentencia.arbolVisual)
                }
            }
            return nodo
        }
    /**
     * @param modificador
     * @param tipoRetorno
     * @param identificadorMetodo
     *
     * @param listaParametros
     *
     * @param listaSentencias
     *
     */
    constructor(modificador: Token?, tipoRetorno:Token?, identificadorMetodo:Token?,
                listaParametros:ArrayList<Parametro>?,
                listaSentencias:ArrayList<Sentencia>?) : super() {
        this.modificador = modificador
        this.tipoRetorno = tipoRetorno
        this.identificadorMetodo = identificadorMetodo
        this.listaParametros = listaParametros
        this.listaSentencias = listaSentencias
    }
    /**
     * @param modificador
     * @param tipoRetorno
     * @param identificadorMetodo
     *
     */
    constructor(modificador:Token, tipoRetorno:Token, identificadorMetodo:Token) : super() {
        this.modificador = modificador
        this.tipoRetorno = tipoRetorno
        this.identificadorMetodo = identificadorMetodo
    }
    /**
     * @param modificador
     * @param tipoRetorno
     * @param identificadorMetodo

     * @param listaParametros
     *
     */
    constructor(modificador:Token, tipoRetorno:Token, identificadorMetodo:Token, listaParametros:ArrayList<Parametro>) : super() {
        this.modificador = modificador
        this.tipoRetorno = tipoRetorno
        this.identificadorMetodo = identificadorMetodo
        this.listaParametros = listaParametros
    }
    /**
     * @param modificador
     * @param tipoRetorno
     * @param identificadorMetodo

     * @param listaSentencias

     */
    constructor(modificador:Token, tipoRetorno:Token, listaSentencias:ArrayList<Sentencia>, identificadorMetodo:Token) : super() {
        this.modificador = modificador
        this.tipoRetorno = tipoRetorno
        this.identificadorMetodo = identificadorMetodo
        this.listaSentencias = listaSentencias
    }
    /* (non-Javadoc)
       * @see java.lang.Object#toString()
       */
    override fun toString():String {
        return ("Metodo [modificador=" + modificador + ", tipoRetorno=" + tipoRetorno + ", identificadorMetodo="
                + identificadorMetodo + ", listaParametros=" + listaParametros + ", listaSentencias=" + listaSentencias
                + "]")
    }

}
