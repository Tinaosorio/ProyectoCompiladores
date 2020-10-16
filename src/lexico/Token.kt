package lexico
/**
 * Clase que pemite la abstraccion de un token de lenguaje de programacion
 * @author Valentina osorio
 */
class Token {

    var lexema: String? = null
    var fila: Int = 0
    var columna: Int = 0
    var columnaReal: Int = 0
    var categoria: Categoria? = null

    /**
     * Contructor para simbolos
     * @param lexema
     * @param fila
     * @param columna
     * @param categoria
     */
    constructor(lexema: String, fila: Int, columna: Int, categoria: Categoria) {
        this.lexema = lexema
        this.fila = fila
        this.columna = columna
        this.categoria = categoria
    }

    /**
     * Contructor para errores
     * @param lexema
     * @param fila
     * @param columna
     * @param columnaReal
     * @param categoria
     */
    constructor(lexema: String, fila: Int, columna: Int, columnaReal: Int, categoria: Categoria) {
        this.lexema = lexema
        this.fila = fila
        this.columna = columna
        this.categoria = categoria
        this.columnaReal = columnaReal
    }

    /*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    override fun toString(): String {
        return ("Token [lexema=" + lexema + ", fila=" + fila + ", columna=" + columna + ", categoria=" + categoria
                + "]")
    }


}
