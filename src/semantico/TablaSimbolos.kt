package semantico

import java.util.ArrayList

import sintaxis.Expresion

/**
 * Clase que representa la tabla de simbolos almacenados dentro del código
 *
 * @author valentina osorio
 */
class TablaSimbolos(val errores: ArrayList<String?>) {

    val tablaSimbolos: ArrayList<Simbolo>

    init {
        this.tablaSimbolos = ArrayList()
    }

    fun guardarSimboloVariable(nombre: String, tipo: String, fila: Int, columna: Int, ambito: Simbolo, expresion: Expresion?): Simbolo? {
        val s = buscarSimboloVariables(nombre, ambito)
        if (s == null) {
            val nuevo = Simbolo(nombre, tipo, ambito, fila, columna, expresion)
            tablaSimbolos.add(nuevo)
            return nuevo
        } else {
            errores.add("La variable$nombre ya existe en el ambito $ambito")
        }
        return null
    }

    fun guardarSimboloMetodo(nombre: String?, tipo: String?, tipoParametros: ArrayList<String>?): Simbolo? {

        val s = buscarSimboloMetodo(nombre, tipoParametros)
        if (s == null) {
            val nuevo = Simbolo(nombre, tipo, tipoParametros)
            tablaSimbolos.add(nuevo)
        } else {
            errores.add("El metodo $nombre ya existe!")
        }
        return null
    }

    fun buscarSimboloVariables(nombre: String, ambito: Simbolo): Simbolo? {

        for (simbolo in tablaSimbolos) {
            if (simbolo.ambito != null) {
                if (nombre == simbolo.nombre && ambito == simbolo.ambito) {
                    return simbolo
                }
            }
        }
        return null
    }

    fun buscarSimboloMetodo(nombre: String?, tiposParametros: ArrayList<String>?): Simbolo? {

        for (simbolo in tablaSimbolos) {
            if (simbolo.tipos != null) {
                if (nombre == simbolo.nombre && tiposParametros == simbolo.tipos) {
                    return simbolo
                }
            }
        }
        return null
    }

}
