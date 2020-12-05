/**
 *
 */
package traductor

import sintaxis.UnidadDeCompilacion

/**
 * Clase que realiza la traducci�n a lenguaje Java
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
     * M�todo que traduce desde la unidad de compilaci�n
     *
     * @return
     */
    fun traducir(): String {
        return unidadCompilacion!!.traducir()
    }

}
