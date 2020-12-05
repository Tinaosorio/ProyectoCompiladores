/**
 *
 */
package traductor

import sintaxis.UnidadDeCompilacion

/**
 * Clase que realiza la traducción a lenguaje Java
 *
 * @author valentina osorio
 */
class Traductor
/**
 * @param unidadCompilacion
 */

        /**
         * @return the unidadCompilacion
         */
        /**
         * @param unidadCompilacion
         * the unidadCompilacion to set
         */
        var unidadCompilacion: UnidadDeCompilacion?) {

    /**
     * Método que traduce desde la unidad de compilación
     *
     * @return
     */
    fun traducir(): String {
        return unidadCompilacion!!.traducir()
    }

}
