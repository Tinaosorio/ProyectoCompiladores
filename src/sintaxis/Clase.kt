package sintaxis

import lexico.Token
import semantico.TablaSimbolos
import java.util.*
import javax.swing.tree.DefaultMutableTreeNode

/**
 * @author valentina osorio
 *
 * <Clase>::=<Modificador> "CLASE" <IdentificadorClase> "{" [<CuerpoClase>] "}"
</CuerpoClase></IdentificadorClase></Modificador></Clase> */
class Clase {
    private var modificador: Token?
    private var palabra_reservada_clase: Token?
    private var identificador_clase:Token?=null
    /**
     * @return the cuerpoClase
     */
    /**
     * @param cuerpoClase the cuerpoClase to set
     */
    var cuerpoClase: CuerpoClase? = null

    /**
     * @param modificador
     * @param palabra_reservada_clase
     * @param identificador_clase
     *
     * @param listaMetodos
     */
    constructor(modificador: Token?, palabra_reservada_clase: Token?, identificador_clase: Token?,
                cuerpoClase: CuerpoClase?) : super() {
        this.modificador = modificador
        this.palabra_reservada_clase = palabra_reservada_clase
        this.identificador_clase = identificador_clase
        this.cuerpoClase = cuerpoClase
    }

    /**
     * @param modificador
     * @param palabra_reservada_clase
     * @param identificador_clase
     * @param simbolo_apertura
     * @param simbolo_cierre
     */
    constructor(modificador: Token?, palabra_reservada_clase: Token?, identificador_clase: Token?) : super() {
        this.modificador = modificador
        this.palabra_reservada_clase = palabra_reservada_clase
        this.identificador_clase = identificador_clase
    }

    /**
     * @return modificador
     */
    fun getModificador(): Token? {
        return modificador
    }

    /**
     * @return palabra_reservada
     */
    val palabra_reservada: Token
        get() = this.palabra_reservada_clase!!

    /**
     * @return identificador_clase
     */
    fun getIdentificador_clase(): Token? {
        return identificador_clase
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return ("Clase [modificador=" + modificador + ", palabra_reservada_clase=" + palabra_reservada_clase
                + ", identificador_clase=" + identificador_clase + ", cuerpoClase=" + cuerpoClase + "]")
    }//TODO

    /**
     * Método para retornar el nodo de un arbol visual
     * @return
     */
    val arbolVisual: DefaultMutableTreeNode
        get() { //TODO
            val nodo = DefaultMutableTreeNode("Clase")
            if (cuerpoClase != null) {
                nodo.add(DefaultMutableTreeNode(identificador_clase?.lexema))
                nodo.add(cuerpoClase!!.arbolVisual)
                return nodo
            }
            return nodo
        }

    fun analizarSemantica(errores: ArrayList<String?>?, ts: TablaSimbolos?) {}
    fun llenarTablaSimbolos(ts: TablaSimbolos?, errores: ArrayList<String?>?) {}
    fun traducir(): String {
        var code = ""
        var id = ""
        var cc = ""
        if (modificador?.lexema.equals("PRIVADO")) {
            code = "private "
        }
        if (modificador?.lexema.equals("PUBLICO")) {
            code = "public  "
        }
        if (identificador_clase != null) {
            id = " " + identificador_clase!!.lexema?.replace("@", "")!!.toLowerCase()
        }
        cc = cuerpoClase!!.traducir()
        return "$code  class$id(){ \n$cc\n }"
    }
}
