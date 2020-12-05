package sintaxis

/**
 * @author valentina osorio
 */
import lexico.Categoria
import lexico.Token
import java.util.ArrayList

class AnalizadorSintactico(tablaToken: ArrayList<Token>) {
    /**
     * @return the tablaToken
     */
    var tablaToken: ArrayList<Token>? = null
        private set
    /**
     * @return the tablaErrores
     */
    /**
     * @param tablaErrores the tablaErrores to set
     */
    var tablaErrores: ArrayList<ErrorSintactico>? = null
    /**
     * @return the tokenActual
     */
    /**
     * @param tokenActual the tokenActual to set
     */
    var tokenActual: Token? = null
    /**
     * @return the posActual
     */
    /**
     * @param posActual the posActual to set
     */
    var posActual: Int = 0
    /**
     * @return the unidadDeCompilacion
     */
    /**
     * @param unidadDeCompilacion the unidadDeCompilacion to set
     */
    var unidadDeCompilacion: UnidadDeCompilacion? = null

    init {
        this.posActual = 0
        this.tablaToken = tablaToken
        this.tokenActual = tablaToken[posActual]
        this.tablaErrores = ArrayList()
    }

    fun analizar() {
        unidadDeCompilacion = esUnidadDeCompilacion()
    }

    fun obtenerSgteToken() {

        if (posActual < tablaToken!!.size - 1) {
            posActual++
            tokenActual = tablaToken!![posActual]
        } else {
            tokenActual = Token("", 0, 0, Categoria.FINCODIGO)
        }
    }

    /**
     * Metodo para reportar un error sintactico
     *
     * @param mensaje: El mensaje con el error
     * @param fila:    fila del error
     * @param columna: columna del error
     */
    private fun reportarError(mensaje: String, fila: Int, columna: Int) {
        tablaErrores!!.add(ErrorSintactico(mensaje, fila, columna))
    }

    /**
     * <UnidadDeCompilacion>::=<Paquete>[<listaImportaciones>]<Clase>
    </Clase></listaImportaciones></Paquete></UnidadDeCompilacion> */
    fun esUnidadDeCompilacion(): UnidadDeCompilacion? {

        val paquete = esPaquete()

        if (paquete != null) {

            val importaciones = esListaImportaciones()
            if (importaciones != null) {
                // obtenerSgteToken();
                val clase = esClase()
                if (clase != null) {

                    obtenerSgteToken()
                    return UnidadDeCompilacion(paquete, importaciones, clase)
                } else {
                    reportarError("Falta la clase en UC", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                val clase = esClase()
                if (clase != null) {
                    obtenerSgteToken()
                    return UnidadDeCompilacion(paquete, clase)
                } else {
                    reportarError("Falta la clase en UC", tokenActual!!.fila, tokenActual!!.columna)
                }
            }

        }
        return null

    }

    /**
     * <Paquete>::="PAQUETE" nombrePaquete "%"
     *
     * @return
    </Paquete> */
    fun esPaquete(): Paquete? {
        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA && tokenActual!!.lexema.equals("PAQUETE")) {
            val paquete = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.CADENA_CARACTERES) {
                val nombrePaquete = tokenActual!!.lexema
                obtenerSgteToken()
                if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {
                    obtenerSgteToken()
                    return Paquete(paquete, nombrePaquete)

                } else {
                    reportarError("Falta el terminal % en paquete", tokenActual!!.fila, tokenActual!!.columna)
                }

            } else {
                reportarError("Falta el nombre del paquete", tokenActual!!.fila, tokenActual!!.columna)
            }
        }
        return null
    }

    /**
     * <listaImportaciones> ::=<Importacion> [<ListaImportaciones>]
    </ListaImportaciones></Importacion></listaImportaciones> */
    fun esListaImportaciones(): ArrayList<Importacion> {

        val importaciones = ArrayList<Importacion>()

        var f = esImportacion()

        while (f != null) {

            importaciones.add(f)

            f = esImportacion()
        }

        return importaciones
    }

    /**
     * <Importacion> ::="IMPORTAR" nombrePaquete "." <IdentificadorClase> "%"
    </IdentificadorClase></Importacion> */
    fun esImportacion(): Importacion? {

        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA && tokenActual!!.lexema.equals("IMPORTAR")) {

            val palabra_reservada_importar = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.CADENA_CARACTERES) {
                val nombrePaquete = tokenActual!!.lexema
                obtenerSgteToken()

                if (tokenActual!!.categoria === Categoria.PUNTO) {

                    obtenerSgteToken()
                    if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_CLASE) {
                        val identificador_clase = tokenActual
                        obtenerSgteToken()

                        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {

                            obtenerSgteToken()

                            return Importacion(palabra_reservada_importar, nombrePaquete, identificador_clase)

                        } else {
                            reportarError("Falta el terminal en importacion", tokenActual!!.fila,
                                    tokenActual!!.columna)
                        }
                    } else {
                        reportarError("Falta el identificador de clase en importacion", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }

                } else {
                    reportarError("Falta el punto en importacion", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta el nombre del paquete en importacion", tokenActual!!.fila,
                        tokenActual!!.columna)
            }

        }

        return null

    }

    /**
     * <Clase>::=<Modificador> "CLASE" <IdentificadorClase> "{" [<CuerpoClase>] "}"
     *
     * @return
    </CuerpoClase></IdentificadorClase></Modificador></Clase> */
    fun esClase(): Clase? {

        var modificador: Token? = null
        modificador = esModificador()
        if (modificador != null) {

            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA && tokenActual!!.lexema.equals("CLASE")) {
                val clase = tokenActual
                obtenerSgteToken()
                if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_CLASE) {
                    val identificadorClase = tokenActual
                    obtenerSgteToken()
                    if (tokenActual!!.categoria === Categoria.LLAVE_IZQ) {
                        obtenerSgteToken()
                        val cuerpoClase = esCuerpoClase()

                        if (cuerpoClase != null) {

                            if (tokenActual!!.categoria === Categoria.LLAVE_DER) {

                                obtenerSgteToken()
                                return Clase(modificador, clase, identificadorClase, cuerpoClase)
                            } else {
                                reportarError("Falta el simbolo de cierre } en clase", tokenActual!!.fila,
                                        tokenActual!!.columna)
                            }
                        }
                        if (tokenActual!!.categoria === Categoria.LLAVE_DER) {

                            obtenerSgteToken()
                            return Clase(modificador, clase, identificadorClase)
                        } else {
                            reportarError("Falta el simbolo de cierre } en clase", tokenActual!!.fila,
                                    tokenActual!!.columna)
                        }
                    } else {
                        reportarError("Falta el simbolo de apertura { en clase", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }
                } else {
                    reportarError("Falta el identificador de la clase", tokenActual!!.fila,
                            tokenActual!!.columna)
                }

            } else {
                reportarError("Falta la palabra reservada CLASE", tokenActual!!.fila, tokenActual!!.columna)
            }

        }
        return null
    }

    /**
     * Metodo que verifica si es cuerpo de clase
     *
     * <CuerpoClase>::= < metodo> [<CuerpoClase>] | <DeclaracionVariable> [<
     * CuerpoClase>]
     *
     * @return cuerpoClase{CuerpoClase}
    </DeclaracionVariable></CuerpoClase></CuerpoClase> */
    fun esCuerpoClase(): CuerpoClase? {

        val posInicial = posActual
        val metodo = esMetodo()

        if (metodo != null) {

            obtenerSgteToken()
            val cuerpoClase = esCuerpoClase()
            return if (cuerpoClase != null) {
                CuerpoClase(metodo, cuerpoClase)
            } else {
                CuerpoClase(metodo)
            }
        } else {
            hacerBactracking(posInicial)
            val declaracionVariable = esDeclaracionVariable()
            if (declaracionVariable != null) {

                val cuerpoClase1 = esCuerpoClase()
                return if (cuerpoClase1 != null) {
                    CuerpoClase(declaracionVariable, cuerpoClase1)
                } else {
                    CuerpoClase(declaracionVariable)
                }
            }
        }
        return null
    }

    fun esListaMetodos(): ArrayList<Metodo> {
        val metodos = ArrayList<Metodo>()

        var m = esMetodo()

        while (m != null) {
            metodos.add(m)
            m = esMetodo()
        }

        return metodos

    }

    /**
     * <Metodo>::=<Modificador> <TipoRetorno> <IdentificadorMetodo> "<-"
     * [<ListaParametros>]"->" "{" [<ListaSentencias>] "}"
     *
     * @return
    </ListaSentencias></ListaParametros></IdentificadorMetodo></TipoRetorno></Modificador></Metodo> */
    fun esMetodo(): Metodo? {
        var modificador: Token? = null
        var tipoRetorno: Token? = null
        modificador = esModificador()
        if (modificador != null) {

            obtenerSgteToken()

            tipoRetorno = esTipoRetorno()
            if (tipoRetorno != null) {

                obtenerSgteToken()
                if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_METODO) {
                    val identificadorMetodo = tokenActual
                    obtenerSgteToken()
                    if (tokenActual!!.categoria === Categoria.AGRUPADOR_IZQUIERDO) {

                        obtenerSgteToken()
                        val parametros = esListaParametros()

                        if (tokenActual!!.categoria === Categoria.AGRUPADOR_DERECHO) {
                            obtenerSgteToken()
                            if (tokenActual!!.categoria === Categoria.LLAVE_IZQ) {
                                obtenerSgteToken()
                                val listaSentencias = esListaSentencias()

                                if (tokenActual!!.categoria === Categoria.LLAVE_DER) {
                                    obtenerSgteToken()

                                    return Metodo(modificador, tipoRetorno, identificadorMetodo, parametros,
                                            listaSentencias)
                                } else {
                                    reportarError("Falta llave derecha en metodo", tokenActual!!.fila,
                                            tokenActual!!.columna)
                                }

                            } else {
                                reportarError("Falta llave izquierda en metodo", tokenActual!!.fila,
                                        tokenActual!!.columna)
                            }
                        } else {
                            reportarError("Falta agrupador derecho -> en metodo", tokenActual!!.fila,
                                    tokenActual!!.columna)
                        }

                    } else {
                        reportarError("Falta simbolo Apertura <- en metodo", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }

                } else {
                    reportarError("Falta identificador Metodo", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta tipo de retorno en metodo", tokenActual!!.fila, tokenActual!!.columna)
            }
        }
        return null
    }

    /*
	 * <TipoRetorno> ::= ENTERO | CADENA | REAL|NORETORNO|BOOLEANO
	 */
    fun esTipoRetorno(): Token? {
        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA) {

            if (tokenActual!!.lexema.equals("ENTERO") || tokenActual!!.lexema.equals("CADENA")
                    || tokenActual!!.lexema.equals("REAL") || tokenActual!!.lexema.equals("NORETORNO")
                    || tokenActual!!.lexema.equals("BOOLEANO")) {
                return tokenActual
            }

        }
        return null
    }

    /*
	 * <listaSentencias>:: = <Sentencia>|[<listaSentencias>]
	 */
    fun esListaSentencias(): ArrayList<Sentencia> {

        val sentencias = ArrayList<Sentencia>()
        var s = esSentencia()

        while (s != null) {

            sentencias.add(s)
            s = esSentencia()

        }

        return sentencias
    }

    /**
     * <Sentencia>::=<Mientras>|<Retorno>|<Para>|<InvocacionMetodo>|<DeclaracionVariable>|<Si>|<Decremento>|<Incremento>
     *
     * @return
    </Incremento></Decremento></Si></DeclaracionVariable></InvocacionMetodo></Para></Retorno></Mientras></Sentencia> */
    fun esSentencia(): Sentencia? {
        var sentencia: Sentencia?

        sentencia = esSentenciaMientras()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esAsignacion()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esSentenciaRetorno()
        if (sentencia != null) {
            return sentencia

        }

        sentencia = esInvocacionMetodo()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esImpresion()
        if (sentencia != null) {
            return sentencia

        }

        sentencia = esDeclaracionVariable()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esSentenciaSI()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esLeer()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esIncremento()
        if (sentencia != null) {
            return sentencia

        }
        sentencia = esDecremento()
        return sentencia
    }

    /*
	 * <Si>::="SI" "<-" <ExpresionRelacional> "->" "{" [<ListaSentencias>] "}"
	 */
    fun esSentenciaSI(): Sentencia? {

        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA && tokenActual!!.lexema.equals("SI")) {
            val si = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.AGRUPADOR_IZQUIERDO) {
                obtenerSgteToken()
                val expresion = esExpresionRelacional()
                if (expresion != null) {
                    //obtenerSgteToken();
                    if (tokenActual!!.categoria === Categoria.AGRUPADOR_DERECHO) {
                        obtenerSgteToken()
                        if (tokenActual!!.categoria === Categoria.LLAVE_IZQ) {

                            obtenerSgteToken()
                            val listaSentencias = esListaSentencias()
                            if (listaSentencias != null) {
                                // obtenerSgteToken();
                                if (tokenActual!!.categoria === Categoria.LLAVE_DER) {

                                    obtenerSgteToken()
                                    return Si(si, expresion, listaSentencias)
                                } else {
                                    reportarError("Falta simbolo de cierre } en SI", tokenActual!!.fila,
                                            tokenActual!!.columna)
                                }
                            } else {
                                if (tokenActual!!.categoria === Categoria.LLAVE_DER) {
                                    obtenerSgteToken()
                                    return Si(si, expresion)
                                } else {
                                    reportarError("Falta simbolo de cierre } en SI", tokenActual!!.fila,
                                            tokenActual!!.columna)
                                }

                            }
                        } else {
                            reportarError("Falta simbolo de apertura { en SI", tokenActual!!.fila,
                                    tokenActual!!.columna)
                        }
                    } else {
                        reportarError("Falta simbolo de cierre -> en SI", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }
                } else {
                    reportarError("Falta una expresion relacional en SI", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta el simbolo de apertura <- en SI", tokenActual!!.fila, tokenActual!!.columna)
            }
        }
        return null
    }

    /*
	 * <DeclaracionVariable>::=<IdentificadorVariable><TipoDato>"%"
	 */
    fun esDeclaracionVariable(): DeclaracionVariable? {

        val posInicial = posActual

        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
            val identificadorVariable = tokenActual
            obtenerSgteToken()
            val tipoDato = esTipoDato()
            if (tipoDato != null) {
                obtenerSgteToken()

                if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {
                    obtenerSgteToken()
                    return DeclaracionVariable(identificadorVariable, tipoDato)
                } else {
                    reportarError("Falta el terminal en DV", tokenActual!!.fila, tokenActual!!.columna)
                }

            } else {

                if (tokenActual!!.categoria === Categoria.OPERADOR_ASIGNACION
                        || tokenActual!!.categoria === Categoria.INCREMENTO
                        || tokenActual!!.categoria === Categoria.DECREMENTO || tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {
                    hacerBactracking(posInicial)
                } else {
                    reportarError("Falta tipo de dato en DV", tokenActual!!.fila, tokenActual!!.columna)
                }

            }
        }

        return null
    }

    /**
     *
     * @return
     */
    fun esExpresion(): Expresion? {

        var expresion: Expresion? = null

        expresion = esExpresionAritmetica()
        if (expresion != null) {
            return expresion

        }

        expresion = esExpresionRelacional()
        if (expresion != null) {
            return expresion

        }
        expresion = esExpresionLogica()
        if (expresion != null) {
            return expresion

        }
        expresion = esExpresionCadena()
        return expresion

    }

    /**
     * <expresionCadena> ::= <cadena> | <cadena> con <expresion>
     *
     * Metodo para verificar si es una expresion cadena
     *
     * @return
    </expresion></cadena></cadena></expresionCadena> */
    fun esExpresionCadena(): ExpresionCadena? {

        if (tokenActual!!.categoria === Categoria.CADENA_CARACTERES) {
            val cadena = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.CONCATENACION) {
                val concatenacion = tokenActual
                obtenerSgteToken()
                val exp = esExpresion()
                if (exp != null) {
                    obtenerSgteToken()
                    return ExpresionCadena(cadena, concatenacion, exp)
                } else {
                    reportarError("Falta la palabra con", tokenActual!!.fila, tokenActual!!.columna)

                }
            } else {
                obtenerSgteToken()
                return ExpresionCadena(cadena)
            }
        }

        return null
    }

    /**
     * <expresionRelacional> ::= <expresion Aritmetica> <operadorRelacional>
     * <expresion Aritmetica> | <expresion Cadena> <operadorRelacional> <expresion Cadena>
     *
     * @return
    </expresion></operadorRelacional></expresion></expresion></operadorRelacional></expresion></expresionRelacional> */
    fun esExpresionRelacional(): ExpresionRelacional? {

        val expAritmetica = esExpresionAritmetica()
        if (expAritmetica != null) {
            //obtenerSgteToken();
            if (tokenActual!!.categoria === Categoria.OPERADOR_RELACIONAL) {
                val opRelacional = tokenActual
                obtenerSgteToken()
                val expArit = esExpresionAritmetica()
                if (expArit != null) {
                    return ExpresionRelacional(expAritmetica, opRelacional, expArit)
                } else {
                    reportarError("falta Expresión Aritmetica en ER", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta operador Relacional ER", tokenActual!!.fila, tokenActual!!.columna)
            }
        }
        val expCad1 = esExpresionCadena()
        if (expCad1 != null) {
            //obtenerSgteToken();
            if (tokenActual!!.categoria === Categoria.OPERADOR_RELACIONAL) {
                val opRelacional = tokenActual
                obtenerSgteToken()
                val expCad2 = esExpresionCadena()
                if (expCad2 != null) {
                    return ExpresionRelacional(expCad1, opRelacional, expCad2)
                } else {
                    reportarError("falta Expresión Cadena en ER", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta operador Cadena en ER", tokenActual!!.fila, tokenActual!!.columna)
            }
        }

        return null
    }

    /**
     * <expresionAritmetica> ::= <termino>|<Termino><OAritmetico><agruIzq><ExpresionArit><AgrupDer>|<Termino><OAritmetico><Termino>
     *
     * @return
    </Termino></OAritmetico></Termino></AgrupDer></ExpresionArit></agruIzq></OAritmetico></Termino></termino></expresionAritmetica> */
    fun esExpresionAritmetica(): ExpresionAritmetica1? {
        val posInicial = posActual
        val term1 = esTermino()
        if (term1 != null) {
            // obtenerSgteToken();
            if (tokenActual!!.categoria === Categoria.OPERADOR_ARITMETICO) {
                val operAritm = tokenActual
                obtenerSgteToken()
                val term2 = esTermino()
                if (term2 != null) {
                    return ExpresionAritmetica1(term1, operAritm, term2)
                } else if (tokenActual!!.categoria === Categoria.AGRUPADOR_IZQUIERDO) {
                    val agruIzq = tokenActual
                    obtenerSgteToken()
                    val exprArit = esExpresionAritmetica()
                    if (exprArit != null) {

                        //obtenerSgteToken();
                        if (tokenActual!!.categoria === Categoria.AGRUPADOR_DERECHO) {
                            val agruDer = tokenActual
                            obtenerSgteToken()
                            return ExpresionAritmetica1(term1, operAritm, agruIzq, exprArit, agruDer)
                        } else {
                            reportarError("Falta agrupador derecho en EA", tokenActual!!.fila, tokenActual!!.columna)
                        }
                    } else {
                        reportarError("falta expresion aritmetica en EA", tokenActual!!.fila, tokenActual!!.columna)
                    }
                } else {
                    reportarError("Falta agrupador izquierdo en EA", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                if (tokenActual!!.categoria === Categoria.OPERADOR_RELACIONAL || tokenActual!!.categoria === Categoria.CONCATENACION) {
                    hacerBactracking(posInicial)

                }
                //obtenerSgteToken();
                return ExpresionAritmetica1(term1)
            }

        }

        return null

    }

    /*
	 * <Termino>::=<IdentificadorVariable>|<Entero>|<Real>
	 *
	 */
    fun esTermino(): Termino? {
        val termino: Token
        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
            termino = tokenActual!!
            obtenerSgteToken()
            return Termino(termino)

        } else if (tokenActual!!.categoria === Categoria.ENTERO) {
            termino = tokenActual!!
            obtenerSgteToken()
            return Termino(termino)

        } else if (tokenActual!!.categoria === Categoria.REAL) {
            termino = tokenActual!!
            obtenerSgteToken()
            return Termino(termino)

        }

        return null
    }

    /*
	 * <Asignacion>::=<TipoDato><identificadorVariable><OperadorAsignacion><Expresion> '%'
	 */
    fun esAsignacion(): Asignacion? {

        val tipoDato = esTipoDato()
        if (tipoDato != null) {
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
                val identificadorVariable = tokenActual
                obtenerSgteToken()
                if (tokenActual!!.categoria === Categoria.OPERADOR_ASIGNACION) {
                    val operador = tokenActual
                    obtenerSgteToken()
                    val expresion = esExpresion()
                    if (expresion != null) {

                        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {

                            obtenerSgteToken()
                            return Asignacion(tipoDato, identificadorVariable, operador, expresion)
                        } else {
                            reportarError("Falta el terminal en asignacion ", tokenActual!!.fila, tokenActual!!.columna)

                        }
                    } else {
                        reportarError("Falta la expresion en asignacion", tokenActual!!.fila, tokenActual!!.columna)

                    }
                }
                reportarError("Falta el operador de asignacion ", tokenActual!!.fila, tokenActual!!.columna)
            }
            reportarError("Falta el identificafor de variable en asignacion ", tokenActual!!.fila, tokenActual!!.columna)
        }

        return null
    }

    /*
	 * <InvocacionMetodo>::=<IdentificadorClase>"."<IdentificadorMetodo> "<-" " ->" "%"
	 */
    fun esInvocacionMetodo(): InvocacionMetodo? {
        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_CLASE) {
            val identificadorClase = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.PUNTO) {
                obtenerSgteToken()
                if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_METODO) {
                    val identificadorMetodo = tokenActual
                    obtenerSgteToken()
                    if (tokenActual!!.categoria === Categoria.AGRUPADOR_IZQUIERDO) {

                        obtenerSgteToken()

                        if (tokenActual!!.categoria === Categoria.AGRUPADOR_DERECHO) {
                            obtenerSgteToken()
                            if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {
                                obtenerSgteToken()
                                return InvocacionMetodo(identificadorClase, identificadorMetodo)
                            } else {
                                reportarError("Falta terminal en la invocacion", tokenActual!!.fila,
                                        tokenActual!!.columna)
                            }
                        } else {
                            reportarError("Falta el simbolo de cierre -> en la invocacion", tokenActual!!.fila,
                                    tokenActual!!.columna)
                        }


                    } else {
                        reportarError("Falta simbolo de apertura <- en la invocacion", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }
                } else {
                    reportarError("Falta el identificador del metodo", tokenActual!!.fila,
                            tokenActual!!.columna)
                }

            } else {
                reportarError("Falta el punto", tokenActual!!.fila,
                        tokenActual!!.columna)
            }
        }
        return null
    }


    /**
     * <condicion> ::= IMPRIMIR "<-" <expresion> "->" "%"
     *
     * @return
    </expresion></condicion> */
    fun esImpresion(): Impresion? {

        val palabraReservada = tokenActual
        if (palabraReservada!!.categoria === Categoria.PALABRA_RESERVADA && palabraReservada!!.lexema.equals("IMPRIMIR")) {
            obtenerSgteToken()
            val parIzq = tokenActual
            if (parIzq!!.categoria === Categoria.AGRUPADOR_IZQUIERDO) {
                obtenerSgteToken()
                val exp = esExpresion()

                if (exp != null) {
                    val parDer = tokenActual
                    if (parDer!!.categoria === Categoria.AGRUPADOR_DERECHO) {
                        obtenerSgteToken()
                        val finSentencia = tokenActual
                        if (finSentencia!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {
                            obtenerSgteToken()
                            return Impresion(palabraReservada, parIzq, exp, parDer, finSentencia)
                        } else {

                            reportarError("Falta el terminal", tokenActual!!.fila, tokenActual!!.columna)
                        }

                    } else {
                        reportarError("Falta parentesis derecho en la impresion", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }
                } else {
                    reportarError("Falta expresion en la impresion", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {

                reportarError("Falta parentesis izquierdo en la impresion", tokenActual!!.fila,
                        tokenActual!!.columna)
            }

        }

        return null

    }

    /**
     * <Retorno>::="RETORNO"<Termino>"%"
     *
     * @return
    </Termino></Retorno> */
    fun esSentenciaRetorno(): Retorno? {
        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA && tokenActual!!.lexema.equals("RETORNO")) {
            val retorno = tokenActual
            obtenerSgteToken()
            val termino = esTermino()
            if (termino != null) {
                // obtenerSgteToken();
                if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {

                    obtenerSgteToken()
                    return Retorno(retorno, termino)
                } else {
                    reportarError("Falta terminal en retorno", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta termino en retorno", tokenActual!!.fila, tokenActual!!.columna)
            }
        }

        return null
    }

    /*
	 * <Mientras>::="MIENTRAS" "<-"<ExpresionLogica>"->""{" [<listaSentencias>] "}"
	 */
    fun esSentenciaMientras(): Mientras? {

        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA && tokenActual!!.lexema.equals("MIENTRAS")) {
            val mientras = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.AGRUPADOR_IZQUIERDO) {

                obtenerSgteToken()
                val expresionLogica = esExpresionLogica()
                if (expresionLogica != null) {
                    //obtenerSgteToken();
                    if (tokenActual!!.categoria === Categoria.AGRUPADOR_DERECHO) {

                        obtenerSgteToken()
                        if (tokenActual!!.categoria === Categoria.LLAVE_IZQ) {

                            obtenerSgteToken()
                            val listaSentencias = esListaSentencias()
                            if (listaSentencias != null) {
                                //obtenerSgteToken();
                                if (tokenActual!!.categoria === Categoria.LLAVE_DER) {

                                    obtenerSgteToken()
                                    return Mientras(mientras, expresionLogica, listaSentencias)
                                } else {
                                    reportarError("Falta terminal en SM", tokenActual!!.fila,
                                            tokenActual!!.columna)
                                }

                            } else if (tokenActual!!.categoria === Categoria.LLAVE_DER) {

                                obtenerSgteToken()
                                return Mientras(mientras, expresionLogica)
                            }
                        } else {
                            reportarError("Falta llave izquierda  en SM", tokenActual!!.fila,
                                    tokenActual!!.columna)
                        }
                    } else {
                        reportarError("Falta simbolo de cierre -> en SM", tokenActual!!.fila,
                                tokenActual!!.columna)
                    }

                } else {
                    reportarError("Falta la expresion logica en SM", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta el simbolo de apertura <- en SM", tokenActual!!.fila, tokenActual!!.columna)
            }
        }
        return null
    }

    /**
     * <ExpresionLogica>::=<ExpresionRelacional><OperadorLogico><ExpresionRelacional>
     *
     * @return
    </ExpresionRelacional></OperadorLogico></ExpresionRelacional></ExpresionLogica> */
    fun esExpresionLogica(): ExpresionLogica? {

        val expresion1 = esExpresionRelacional()
        if (expresion1 != null) {
            // obtenerSgteToken();
            if (tokenActual!!.categoria === Categoria.OPERADOR_LOGICO) {
                val operador = tokenActual
                obtenerSgteToken()
                val expresion2 = esExpresionRelacional()
                if (expresion2 != null) {
                    // obtenerSgteToken();
                    return ExpresionLogica(expresion1, operador, expresion2)
                } else {
                    reportarError("Falta una expresion en EL ", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta el operador logico en EL", tokenActual!!.fila, tokenActual!!.columna)
            }
        }

        return null
    }

    /*
	 * * <SentenciaIncremento>::=<IdentificadorVariable><Incremento>
	 *
	 */
    fun esIncremento(): SentenciaIncremento? {

        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
            val identificador = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.INCREMENTO) {
                val incremento = tokenActual
                obtenerSgteToken()
                return SentenciaIncremento(identificador, incremento)

            } else {
                reportarError("Falta el operador de incremento  ", tokenActual!!.fila, tokenActual!!.columna)
            }
        }

        return null
    }

    /*
	 * <SentenciaDecremento>::=<IdentificadorVariable><Decremento>
	 *
	 */
    fun esDecremento(): SentenciaDecremento? {

        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
            val identificador = tokenActual
            obtenerSgteToken()
            if (tokenActual!!.categoria === Categoria.DECREMENTO) {
                val decremento = tokenActual
                obtenerSgteToken()
                return SentenciaDecremento(identificador, decremento)

            } else {
                reportarError("Falta el operador de decremento  ", tokenActual!!.fila, tokenActual!!.columna)
            }
        }
        return null
    }

    /**
     * <Modificador> ::= PRIVADO | PUBLICO
    </Modificador> */
    fun esModificador(): Token? {

        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA) {

            if (tokenActual!!.lexema.equals("PRIVADO") || tokenActual!!.lexema.equals("PUBLICO")) {
                return tokenActual
            }

        }
        return null
    }

    /**
     * <TipoDato> ::= ENTERO | CADENA | REAL|BOOLEANO
    </TipoDato> */
    fun esTipoDato(): Token? {

        if (tokenActual!!.categoria === Categoria.PALABRA_RESERVADA) {

            if (tokenActual!!.lexema.equals("ENTERO") || tokenActual!!.lexema.equals("CADENA")
                    || tokenActual!!.lexema.equals("REAL") || tokenActual!!.lexema.equals("BOOLEANO")) {
                return tokenActual
            }

        }
        return null
    }

    /*
	 * <ListaParametros>::=<Parametro>|[<ListaParametros>]
	 */
    fun esListaParametros(): ArrayList<Parametro> {
        val parametros = ArrayList<Parametro>()

        var p = esParametro()

        while (p != null) {
            parametros.add(p)
            p = esParametro()
        }

        return parametros

    }

    /**
     * <leer> ::= LEER idVariable "%"
     *
     * @return leer
    </leer> */
    fun esLeer(): Leer? {

        val palabraReservada = tokenActual

        if (palabraReservada!!.categoria === Categoria.PALABRA_RESERVADA && palabraReservada!!.lexema.equals("LEER")) {
            obtenerSgteToken()

            if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
                val id = tokenActual
                obtenerSgteToken()
                val terminal = tokenActual
                if (terminal!!.categoria === Categoria.IDENTIFICADOR_TERMINAL) {
                    obtenerSgteToken()
                    return Leer(id)
                } else {
                    reportarError("Falta el terminal en el leer", tokenActual!!.fila, tokenActual!!.columna)
                }
            } else {
                reportarError("Falta el idVariable en el leer", tokenActual!!.fila, tokenActual!!.columna)
            }
        }

        return null
    }

    /**
     * <Parametro>::= <IdentificadorVariable><TipoDato>
     *
     * @return Parametro
    </TipoDato></IdentificadorVariable></Parametro> */
    fun esParametro(): Parametro? {
        var tipoDato: Token? = null
        if (tokenActual!!.categoria === Categoria.IDENTIFICADOR_VARIABLE) {
            val identificadorVariable = tokenActual
            obtenerSgteToken()
            tipoDato = esTipoDato()

            if (tipoDato != null) {
                obtenerSgteToken()
                return Parametro(identificadorVariable, tipoDato)
            } else {
                reportarError("Falta tipo de dato en parámetro", tokenActual!!.fila, tokenActual!!.columna)
            }
        }

        return null
    }

    /**
     * Metodo para volver a una instancia anterior del codigo
     *
     * @param posInicial: posicion donde se inicio el analisis
     */
    fun hacerBactracking(posInicial: Int) {
        posActual = posInicial
        tokenActual = tablaToken!![posActual]
    }

    /**
     * @return the tablaSimbolos
     */
    fun getTablaSimbolos(): ArrayList<Token>? {
        return tablaToken
    }

    /**
     * @param tablaSimbolos the tablaSimbolos to set
     */
    fun setTablaSimbolos(tablaSimbolos: ArrayList<Token>) {
        this.tablaToken = tablaSimbolos
    }

}
