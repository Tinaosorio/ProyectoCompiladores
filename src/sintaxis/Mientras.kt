package sintaxis
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/*
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

    override fun analizarSemantica(error: ArrayList<String?>?, tS: TablaSimbolos?, ambito: Simbolo?) {
        if (expresionLogica != null)
        {
            expresionLogica.analizarSemantica(error, tS, ambito)
        }
        if (listaSentencias != null)
        {
            for (sentencia in listaSentencias)
            {
                sentencia.analizarSemantica(error, tS, ambito)
            }
        }   }

    override fun llenarTablaSimbolos(ts: TablaSimbolos?, erroresSemanticos: ArrayList<String>, ambito: Simbolo?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    override fun traducir():String {
        val code = "while"
        var sent = ""
        for (i in listaSentencias.indices)
        {
            if (i == listaSentencias.size - 1)
            {
                sent += listaSentencias.get(i).traducir()
            }
            else
            {
                sent += listaSentencias.get(i).traducir() + ", "
            }
        }
        return code + " (" + expresionLogica.traducir() + " ) {" + "\n" + sent + "\n }"
    }
}
