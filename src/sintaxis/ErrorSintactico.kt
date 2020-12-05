package sintaxis

class ErrorSintactico(
        /**
         * @param mensaje the mensaje to set
         */
        var mensaje: String,
        /**
         * @param fila the fila to set
         */
        var fila: Int,
        /**
         * @param columna the columna to set
         */
        var columna: Int) {
    /**
     * @return the mensaje
     */
    /**
     * @return the fila
     */
    /**
     * @return the columna
     */

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return "ErrorSintactico [mensaje=$mensaje, fila=$fila, columna=$columna]"
    }

}
