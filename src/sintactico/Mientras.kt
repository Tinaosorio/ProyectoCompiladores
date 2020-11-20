package sintactico
import lexico.Token
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 *  * @author Valentina osorio

 * <Mientras>::="MIENTRAS" "<-"<ExpresionLogica>"->""{" [<listaSentencias>] "}"
*/
  class Mientras:Sentencia {
    /**
     * @return the palabraReservada
     */
    /**
     * @param palabraReservada the palabraReservada to set
     */
    var palabraReservada:Token?
    /**
     * @return the expresionLogica
     */
    /**
     * @param expresionLogica the expresionLogica to set
     */
    var expresionLogica:ExpresionLogica
    /**
     * @return the listaSentencias
     */
    /**
     * @param listaSentencias the listaSentencias to set
     */
    lateinit var listaSentencias:ArrayList<Sentencia>
    override val arbolVisual:DefaultMutableTreeNode
        get() {
            val nodo = DefaultMutableTreeNode("Mientras")
            nodo.add(expresionLogica.arbolVisual)
            if (listaSentencias != null)
            {
                for (sentencia in listaSentencias)
                {
                    nodo.add(sentencia.arbolVisual)
                }
            }
            return nodo
        }


    /**
     * @param palabraReservada
     *
     * @param expresionLogica
     *
     * @param listaSentencias
     *
     */
    constructor(palabraReservada:Token?, expresionLogica:ExpresionLogica, listaSentencias:ArrayList<Sentencia>) : super() {
        this.palabraReservada = palabraReservada
        this.expresionLogica = expresionLogica
        this.listaSentencias = listaSentencias
    }
    /**
     * @param palabraReservada
     * @param expresionLogica
     *
     */
    constructor(palabraReservada:Token?, expresionLogica:ExpresionLogica) : super() {
        this.palabraReservada = palabraReservada
        this.expresionLogica = expresionLogica
    }
    /* (non-Javadoc)
  * @see java.lang.Object#toString()
  */
    override fun toString():String {
        return ("Mientras [palabraReservada=" + palabraReservada + ", expresionLogica=" + expresionLogica
                + ", listaSentencias=" + listaSentencias + "]")
    }

}
