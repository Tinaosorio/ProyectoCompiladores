package lexico
import java.util.ArrayList
/**
 * Clase que se encarga de manejar las identificaciones de los tokens y
 * almacenarlos
 * @author VALETINA OSORIO
 */
class AnalizadorLexico
/**
 * @param codigoFuente el codigo fuente a analizar
 */
(private val codigoFuente: String) {
    private var charActual: Char = ' '
    private val finCodigo: Char
    private var posActual: Int = 0
    private var colActual: Int = 0
    private var filaActual: Int = 0
    /**
     * metodo que obtiene la tabla de simbolos
     * @return the tablaSimbolos
     */
    val tablaSimbolos: ArrayList<Token>
    /**
     * metodo que obtiene la tabla de errores
     * @return the tablaErrores
     */
    val tablaErrores: ArrayList<Token>

    init {
        this.charActual = codigoFuente[0]
        this.tablaSimbolos = ArrayList()
        this.tablaErrores = ArrayList()
        this.finCodigo = 0.toChar()
    }

    /**
     * Metodo general para analizar con metodos de predicado
     */
    fun analizar() {

        while (charActual != finCodigo) {
            if (charActual == ' ' || charActual == '\n' || charActual == '\t' || charActual == '\r') {
                obtenerSiguienteCaracter()
                continue
            }
            if (esPalabraPaquete()) {
                continue
            }
            if (esPalabraCLASE())
                continue
            if (esPalabraPrivado())
                continue
            if (esPalabraPublico())
                continue
            if (esPalabraMientras())
                continue
            if (esPalabraRetorno())
                continue
            if (esPalabraPara())
                continue
            if (esPalabraImportar())
                continue
            if (esPalabraSi())
                continue
            if (esPalabraEntero())
                continue
            if (esPalabraLeer())
                continue
            if (esPalabraReal())
                continue
            if (esPalabraCadena())
                continue
            if (esPalabraImprimir())
                continue
            if (esPalabraBooleano())
                continue
            if (esPalabraNoRetorno())
                continue
            if (esTerminal())
                continue
            if (esEntero())
                continue
            if (esReal())
                continue
            if (esCadenaCaracteres())
                continue
            if (esComentarioLinea())
                continue
            if (esOperadorAsignacion())
                continue
            if (esOperadorRelacional())
                continue
            if (esComentarioBloque())
                continue
            if (esIdentificadorVariable())
                continue
            if (esIdentificadorMetodo())
                continue
            if (esIdentificadorClase())
                continue
            if (esOperadorLogico())
                continue
            if (esOperadorAritmetico())
                continue
            if (esCaracter())
                continue
            if (esAgrupadorIzquierdo())
                continue
            if (esAgrupadorDerecho())
                continue
            if (esLlaveIzquierda())
                continue
            if (esLlaveDerecha())
                continue
            if (esSeparador())
                continue
            if (esPunto())
                continue
            if (esIncremento())
                continue
            if (esConcatenacion())
                continue
            if (esDecremento())
                continue

            reportarError("" + charActual, filaActual, colActual, posActual)
            obtenerSiguienteCaracter()
        }
    }
    /**
     * Metodo para devolver el proceso de metodos de predicado
     * @param posInicial posicion hasta donde devolverse
     */
    private fun hacerBacktracking(posInicial: Int) {
        posActual = posInicial
        charActual = codigoFuente[posActual]
    }
    /**
     * Metodo que continua con el siguiente caracter del codigo fuente
     */
    private fun obtenerSiguienteCaracter() {

        if (posActual == codigoFuente.length - 1) {
            charActual = finCodigo
        }
        else {
            if (charActual == '\n') {
                filaActual++
                colActual = 0
            }
            else {
                colActual++
            }
            posActual++
            charActual = codigoFuente[posActual]
        }
    }
    /**
     * Metodo que almacena un simbolo del sistema
     * @param lexema    el lexema que se almacenara¡
     * @param fila      fila donde inicio el simbolo
     * @param columna   columna donde se inicio el simbolo
     * @param categoria categorÃ­a del sÃ­mbolo
     */
    private fun almacenarSimbolo(lexema: String, fila: Int, columna: Int, categoria: Categoria) {

        tablaSimbolos.add(Token(lexema, fila, columna, categoria))
    }
    /**
     * Metodo que almacena un codigo desconocido del sistema
     * @param lexema  el lexema de error
     * @param fila    la fila donde origino el error
     * @param columna la columna donde se origio el error
     */
    private fun reportarError(lexema: String, fila: Int, columna: Int, posInicial: Int) {

        tablaErrores.add(Token(lexema, fila, columna, posInicial - fila, Categoria.DESCONOCIDO))
    }
    // metodos
    /**
     * Metodo para determinar si lo ingresado en codigo es un nÃºmero real
     * @return
     */
    private fun esEntero(): Boolean {
        var palabra = ""
        val filaInicio = filaActual
        val columnaInicio = colActual
        val guardarPosicion = posActual

        if (Character.isDigit(charActual)) {
            palabra += charActual
            obtenerSiguienteCaracter()
            while (Character.isDigit(charActual)) {
                palabra += charActual
                obtenerSiguienteCaracter()
            }
            if (charActual == ',') {
                posActual = guardarPosicion
                charActual = codigoFuente[posActual]
                return false
            }
            almacenarSimbolo(palabra, filaInicio, columnaInicio, Categoria.ENTERO)
            return true
        }
        return false
    }
    /**
     * Metodo para determinar si lo ingresado en codigo es un numero real
     * @return
     */
    private fun esReal(): Boolean {

        var palabra = ""

        if (Character.isDigit(charActual)) {
            palabra += charActual
            obtenerSiguienteCaracter()
            while (Character.isDigit(charActual)) {
                palabra += charActual
                obtenerSiguienteCaracter()
            }
            if (charActual == ',') {
                palabra += charActual
                obtenerSiguienteCaracter()
                if (Character.isDigit(charActual)) {
                    palabra += charActual
                    obtenerSiguienteCaracter()
                    while (Character.isDigit(charActual)) {
                        palabra += charActual
                        obtenerSiguienteCaracter()
                    }
                }
            }
            almacenarSimbolo(palabra, filaActual, colActual, Categoria.REAL)
            return true
        }
        if (charActual == ',') {
            palabra += charActual
            obtenerSiguienteCaracter()
            if (Character.isDigit(charActual)) {
                palabra += charActual
                obtenerSiguienteCaracter()

                while (Character.isDigit(charActual)) {
                    palabra += charActual
                    obtenerSiguienteCaracter()
                }
            }
            almacenarSimbolo(palabra, filaActual, colActual, Categoria.REAL)
            return true
        }
        return false
    }
    /**
     * Metodo para determinar si lo ingresado en codigo es un caracter
     * @return
     */
    private fun esCaracter(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == '*') {

            lexema += charActual
            obtenerSiguienteCaracter()

            if (charActual == '|') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == '*' || charActual == 'n' || charActual == 'b' || charActual == 't'
                        || charActual == 'r') {
                    lexema += charActual
                    obtenerSiguienteCaracter()

                    almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.CARACTER)
                    return true
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else if (charActual == finCodigo) {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
            else if (charActual != '*') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.CARACTER)
                return true
            }
            reportarError(lexema, filaInicial, colActual, posInicial)
            return false
        }
        return false
    }
    /**
     * Metodo para determinar si lo ingresado en codigo es una cadena de caracteres
     * @return
     */
    private fun esCadenaCaracteres(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '_') {

            lexema += charActual
            obtenerSiguienteCaracter()

            while (charActual != '_') {

                if (charActual == '|') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (!(charActual == 'n' || charActual == 't' || charActual == '_' || charActual == 'f'
                                    || charActual == 'b' || charActual == '\'' || charActual == 'r' || charActual == '|')) {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                    else {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        continue
                    }
                }
                else if (charActual == finCodigo) {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
                else {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                }
            }
            lexema += charActual
            obtenerSiguienteCaracter()
            almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.CADENA_CARACTERES)
            return true
        }
        return false
    }
    /**
     * Metodo para determinar si lo ingresado por codigo es un identificador de
     * variable
     * @return
     */
    private fun esIdentificadorVariable(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == '#') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (Character.getType(charActual) == Character.LOWERCASE_LETTER.toInt()) {
                lexema += charActual
                obtenerSiguienteCaracter()
                while (Character.getType(charActual) == Character.LOWERCASE_LETTER.toInt()) {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                }
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.IDENTIFICADOR_VARIABLE)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        else {
            return false
        }
    }
    /**
     * Metodo para determinar si lo ingresado por codigo es un identificador de
     * metodo
     * @return
     */
    private fun esIdentificadorMetodo(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '$') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (Character.getType(charActual) == Character.UPPERCASE_LETTER.toInt()) {
                lexema += charActual
                obtenerSiguienteCaracter()
                while (Character.getType(charActual) == Character.UPPERCASE_LETTER.toInt()) {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                }
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.IDENTIFICADOR_METODO)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        else {
            return false
        }
    }
    /**
     * Metodo para determinar si lo ingresado por codigo es un identificador de
     * metodo
     * @return
     */
    private fun esIdentificadorClase(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == '@') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (Character.getType(charActual) == Character.UPPERCASE_LETTER.toInt()) {
                lexema += charActual
                obtenerSiguienteCaracter()
                while (Character.getType(charActual) == Character.UPPERCASE_LETTER.toInt()) {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                }
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.IDENTIFICADOR_CLASE)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        else {
            return false
        }
    }
    /**
     * Analiza si la parte de codigo analizado es un comentario de linea
     * @return
     */
    private fun esComentarioLinea(): Boolean {
        var lexema = ""
        val colInicial = colActual
        val filaInicial = filaActual
        val posInicial = posActual
        if (charActual == '[') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual != ']') {
                lexema += charActual
                obtenerSiguienteCaracter()
                while (charActual != ']') {
                    if (charActual == finCodigo) {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                    }
                    else {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                    }
                }
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.COMENTARIO_LINEA)
                return true
            }
            else {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.COMENTARIO_LINEA)
                return true
            }
        }
        return false
    }
    /**
     * Identifica si la parte de codigo analizado es comentario de bloque
     * @return
     */
    private fun esComentarioBloque(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == '-') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '[') {
                lexema += charActual
                obtenerSiguienteCaracter()

                while (charActual != ']') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == finCodigo) {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                if (charActual == ']') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == '-') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.COMENTARIO_BLOQUE)
                        return true
                    }
                    else if (charActual == finCodigo) {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
            }
            else if (charActual == finCodigo) {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }

    private fun esOperadorAritmetico(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '(') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '+' || charActual == '-' || charActual == '*' || charActual == '/' || charActual == '%') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == ')') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_ARITMETICO)
                    return true
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
                return false
            }
        }
        else {
            return false
        }
    }
    /**
     * Metodo que permite determinar si una fraccion del codigo es un agrupador
     * derecho
     * @return
     */
    private fun esAgrupadorDerecho(): Boolean {

        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '-') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '>') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.AGRUPADOR_DERECHO)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo que permite determinar si una fraccion del codigo es un agrupador
     * izquierdo
     * @return
     */
    private fun esAgrupadorIzquierdo(): Boolean {

        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '<') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '-') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.AGRUPADOR_IZQUIERDO)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es una llave
     * izquierdo
     * @return true
     */

    private fun esLlaveIzquierda(): Boolean {

        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual

        if (charActual == '{') {
            lexema += charActual
            obtenerSiguienteCaracter()
            almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.LLAVE_IZQ)
            return true
        }
        return false
    }
    /**
     * Metodo que permite determinar si una fraccion del codigo es una llave derecha
     * derecho
     * @return
     */
    private fun esLlaveDerecha(): Boolean {

        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual

        if (charActual == '}') {
            lexema += charActual
            obtenerSiguienteCaracter()
            almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.LLAVE_DER)
            return true
        }
        return false
    }

    /**
     * Metodo que permite determinar si una fraccion del codigo es un operador
     * logico
     * @return
     */
    private fun esOperadorLogico(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == '&') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()

                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_LOGICO)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        else if (charActual == '|') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_LOGICO)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        else if (charActual == '~') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()

                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_LOGICO)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es un separador
     * @return true
     */
    private fun esSeparador(): Boolean {
        if (charActual == '/') {
            almacenarSimbolo("" + charActual, filaActual, colActual, Categoria.SEPARADOR_SENTENCIA)
            obtenerSiguienteCaracter()
            return true
        }
        else {
            return false
        }
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es un terminal
     * @return true
     */
    private fun esTerminal(): Boolean {
        if (charActual == '%') {
            almacenarSimbolo("" + charActual, filaActual, colActual, Categoria.IDENTIFICADOR_TERMINAL)
            obtenerSiguienteCaracter()
            return true
        }
        else {
            return false
        }
    }

    /**
     * Metodo paara identificar si es un invocador a elementos de objetos
     * @return
     */
    private fun esPunto(): Boolean {
        if (charActual == '.') {
            almacenarSimbolo("" + charActual, filaActual, colActual, Categoria.PUNTO)
            obtenerSiguienteCaracter()
            return true
        }
        return false
    }
    /**
     * Metodo que permite identificar si una fracción del codigo es la palabra
     * reservada CLASE
     * @return true
     */
    fun esPalabraCLASE(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'C') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'L') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'A') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'S') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'E') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)
                            return true
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo que permite identificar si una fracción del codigo es la palabra
     * reservada PAQUETE
     * @return true
     */
    fun esPalabraPaquete(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'P') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'A') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'Q') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'U') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'E') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'T') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'E') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                    return true
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    hacerBacktracking(posInicial)
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada PRIVADO
     * @return true
     */
    fun esPalabraPrivado(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'P') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'R') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'I') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'V') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'A') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'D') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'O') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)
                                    return true
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }

    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada PUBLICO
     * @return true
     */
    fun esPalabraPublico(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'P') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'U') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'B') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'L') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'I') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'C') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'O') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                    return true
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada RETORNO
     * @return true
     */
    fun esPalabraRetorno(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'R') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'E') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'T') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'O') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'R') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'N') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'O') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                    return true
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    hacerBacktracking(posInicial)
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada MIENTRAS
     * @return true
     */
    fun esPalabraMientras(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'M') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'I') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'E') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'N') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'T') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'R') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'A') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    if (charActual == 'S') {
                                        lexema += charActual
                                        obtenerSiguienteCaracter()
                                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                        return true
                                    }
                                    else {
                                        reportarError(lexema, filaInicial, colInicial, posInicial)
                                        return true
                                    }
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada PARA
     * @return true
     */
    fun esPalabraPara(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'P') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'A') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'R') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'A') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)
                        return true
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada SI
     * @return true
     */
    fun esPalabraSi(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'S') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'I') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada LEER
     * @return true
     */
    fun esPalabraLeer(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'L') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'E') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'E') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'R') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)
                        return true
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada REAL
     * @return true
     */
    fun esPalabraReal(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'R') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'E') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'A') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'L') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)
                        return true
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada ENTERO
     * @return true
     */
    fun esPalabraEntero(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'E') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'N') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'T') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'E') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'R') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'O') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                return true
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada CADENA
     * @return true
     */
    fun esPalabraCadena(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'C') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'A') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'D') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'E') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'N') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'A') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                return true
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada IMPRIMIR
     * @return true
     */
    fun esPalabraImprimir(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'I') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'M') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'P') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'R') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'I') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'M') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'I') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    if (charActual == 'R') {
                                        lexema += charActual
                                        obtenerSiguienteCaracter()
                                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                        return true
                                    }
                                    else {
                                        reportarError(lexema, filaInicial, colInicial, posInicial)
                                        return true
                                    }
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        hacerBacktracking(posInicial)
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada IMPORTAR
     * @return true
     */
    fun esPalabraImportar(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'I') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'M') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'P') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'O') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'R') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'T') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'A') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    if (charActual == 'R') {
                                        lexema += charActual
                                        obtenerSiguienteCaracter()
                                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                        return true
                                    }
                                    else {
                                        reportarError(lexema, filaInicial, colInicial, posInicial)
                                        return true
                                    }
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        hacerBacktracking(posInicial)
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada BOOLEANO
     * @return true
     */
    fun esPalabraBooleano(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'B') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'O') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'O') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'L') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'E') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'A') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'N') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    if (charActual == 'O') {
                                        lexema += charActual
                                        obtenerSiguienteCaracter()
                                        almacenarSimbolo(lexema, filaActual, colActual, Categoria.PALABRA_RESERVADA)

                                        return true
                                    }
                                    else {
                                        reportarError(lexema, filaInicial, colInicial, posInicial)
                                        return true
                                    }
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        hacerBacktracking(posInicial)
                    }
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es la palabra
     * reservada NORETORNO
     * @return true
     */
    fun esPalabraNoRetorno(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'N') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'O') {
                lexema += charActual
                obtenerSiguienteCaracter()

                if (charActual == 'R') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    if (charActual == 'E') {
                        lexema += charActual
                        obtenerSiguienteCaracter()
                        if (charActual == 'T') {
                            lexema += charActual
                            obtenerSiguienteCaracter()
                            if (charActual == 'O') {
                                lexema += charActual
                                obtenerSiguienteCaracter()
                                if (charActual == 'R') {
                                    lexema += charActual
                                    obtenerSiguienteCaracter()
                                    if (charActual == 'N') {
                                        lexema += charActual
                                        obtenerSiguienteCaracter()
                                        if (charActual == 'O') {
                                            lexema += charActual
                                            obtenerSiguienteCaracter()
                                            almacenarSimbolo(lexema, filaActual, colActual,
                                                    Categoria.PALABRA_RESERVADA)

                                            return true
                                        }
                                        else {
                                            reportarError(lexema, filaInicial, colInicial, posInicial)
                                            return true
                                        }
                                    }
                                    else {
                                        reportarError(lexema, filaInicial, colInicial, posInicial)
                                        return true
                                    }
                                }
                                else {
                                    reportarError(lexema, filaInicial, colInicial, posInicial)
                                    return true
                                }
                            }
                            else {
                                reportarError(lexema, filaInicial, colInicial, posInicial)
                                return true
                            }
                        }
                        else {
                            reportarError(lexema, filaInicial, colInicial, posInicial)
                            return true
                        }
                    }
                    else {
                        reportarError(lexema, filaInicial, colInicial, posInicial)
                        return true
                    }
                }
                hacerBacktracking(posInicial)
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posInicial)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es un operador
     * relacional
     * @return true
     */
    fun esOperadorRelacional(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '>') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '>' || charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_RELACIONAL)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        if (charActual == '<') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '<' || charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_RELACIONAL)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        if (charActual == '-') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_RELACIONAL)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        else if (charActual == ':') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == ':') {
                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_RELACIONAL)
                return true
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo que permite identificar si una fraccion del codigo es un operador de
     * asignación
     * @return true
     */
    fun esOperadorAsignacion(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual

        if (charActual == ':') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '+' || charActual == '-' || charActual == '*' || charActual == '/' || charActual == ':') {

                lexema += charActual
                obtenerSiguienteCaracter()
                almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.OPERADOR_ASIGNACION)
                return true
            }
            else {
                reportarError(lexema, filaInicial, colInicial, posActual)
                return true
            }
        }
        return false
    }
    /**
     * Metodo qye permite identificar si una fraccion del codigo es incremento
     * @return true
     */
    fun esIncremento(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '+') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '+') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == ':') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.INCREMENTO)
                    return true
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                }
            }
            else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    /**
     * Metodo que permite identificar si una fraccion del codigo es decremento
     * @return true
     */
    fun esDecremento(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual

        if (charActual == '-') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == '-') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == ':') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.DECREMENTO)
                    return true
                } else {
                    hacerBacktracking(posInicial)
                }
            } else {
                hacerBacktracking(posInicial)
            }
        }
        return false
    }
    fun esConcatenacion(): Boolean {
        var lexema = ""
        val filaInicial = filaActual
        val colInicial = colActual
        val posInicial = posActual
        if (charActual == 'c') {
            lexema += charActual
            obtenerSiguienteCaracter()
            if (charActual == 'o') {
                lexema += charActual
                obtenerSiguienteCaracter()
                if (charActual == 'n') {
                    lexema += charActual
                    obtenerSiguienteCaracter()
                    almacenarSimbolo(lexema, filaInicial, colInicial, Categoria.CONCATENACION)
                    return true
                }
                else {
                    reportarError(lexema, filaInicial, colInicial, posInicial)
                    return true
                }
            }
            else {
                hacerBacktracking(posInicial)
                return false
            }
        }
        return false
    }
}
