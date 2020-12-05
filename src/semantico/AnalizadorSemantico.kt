package semantico

import java.util.ArrayList


import sintaxis.UnidadDeCompilacion

/**
 * Clase que realiza el análisis semántico
 *
 * @author valentina osorio
 */
class AnalizadorSemantico
/**
 * @param unidadCompilacion
 * @param ts
 * @param errores
 */
(
        /**
         * @return the unidadCompilacion
         */
        /**
         * @param unidadCompilacion the unidadCompilacion to set
         */
        var unidadCompilacion: UnidadDeCompilacion?, ts: TablaSimbolos,
        /**
         * @return the errores
         */
        /**
         * @param errores the errores to set
         */
        var errores: ArrayList<String?>?) {
    /**
     * @return the ts
     */
    /**
     * @param ts the ts to set
     */
    var ts: TablaSimbolos? = null

    init {
        this.ts = TablaSimbolos(this.errores!!)
    }

    fun llenarTablaSimbolos() {
        unidadCompilacion?.llenarTablaSimbolos(ts, errores)
    }

    fun analizarSemantica() {
        unidadCompilacion?.analizarSemantica(ts, errores)
    }


}
