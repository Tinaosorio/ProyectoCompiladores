package sintaxis
import lexico.Token
import semantico.Simbolo
import semantico.TablaSimbolos
import java.util.ArrayList
import javax.swing.tree.DefaultMutableTreeNode
/**
 * <Metodo>::=<Modificador> <TipoRetorno> <IdentificadorMetodo> "<-"
 * [<ListaParametros>]" ->" "{" [<ListaSentencias>] "}"
 *
 * @author valentina osorio
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
    lateinit var ambito: Simbolo
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
    fun analizarSemantica(error:ArrayList<String?>, tS: TablaSimbolos) {
        for (sentencia in this!!.listaSentencias!!)
        {
            sentencia.analizarSemantica(error, tS, ambito)
        }
    }
    fun llenarTablaSimbolos(ts:TablaSimbolos, erroresSemanticos:ArrayList<String>) {
        if (tipoRetorno != null)
        {
            ambito = ts.guardarSimboloMetodo(identificadorMetodo?.lexema!!, tipoRetorno.lexema!!, tipoParametros)!!
        }
        else
        {
            ambito = ts.guardarSimboloMetodo(identificadorMetodo?.lexema!!, null, tipoParametros)!!
        }
        for (parametro in this!!.listaParametros!!)
        {
            ts.guardarSimboloVariable(parametro.getIdentificadorVariable()?.lexema!!, parametro.getTipoDedato()!!.lexema!!, parametro.getIdentificadorVariable()!!.fila, parametro.getIdentificadorVariable()!!.columna, ambito, null)
        }
        for (sentencia in this!!.listaSentencias!!)
        {
            sentencia.llenarTablaSimbolos(ts, erroresSemanticos, ambito)
        }
    }
    fun traducir():String {
        var code = ""
        var tipo = ""
        var param = ""
        var sent = ""
        var st = ""
        st = identificadorMetodo!!.lexema!!.replace("$", "").toLowerCase()
        if (modificador!!.lexema.equals("PRIVADO"))
        {
            code = "private static "
        }
        if (modificador.lexema.equals("PUBLICO"))
        {
            code = "public static "
        }
        if (tipoRetorno?.lexema.equals("CADENA"))
        {
            tipo = "String "
        }
        if (tipoRetorno?.lexema.equals("ENTERO"))
        {
            tipo = "int "
        }
        if (tipoRetorno?.lexema.equals("BOOLEANO"))
        {
            tipo = "boolean "
        }
        if (tipoRetorno?.lexema.equals("NORETORNO"))
        {
            tipo = "void "
        }
        if (tipoRetorno?.lexema.equals("REAL"))
        {
            tipo = "float "
        }
        for (i in listaParametros!!.indices)
        {
            if (i == listaParametros!!.size - 1)
            {
                param += listaParametros!!.get(i).traducir()
            }
            else
            {
                param += listaParametros!!.get(i).traducir() + ", "
            }
        }
        for (i in listaSentencias?.indices!!)
            if (i == listaSentencias!!.size - 1)
            {
                sent += listaSentencias!!.get(i).traducir()
            }
            else
            {
                sent += listaSentencias!!.get(i).traducir() + "\n"
            }
        return code + " " + tipo + st + " (" + param + ")" + "{ \n" + sent + "\n }"
    }
}
