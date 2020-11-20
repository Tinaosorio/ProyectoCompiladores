package controlador
import java.awt.BorderLayout
import java.awt.Color
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.ArrayList

import javax.swing.JFileChooser
import javax.swing.JOptionPane
import javax.swing.JScrollPane
import javax.swing.JTable
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.table.DefaultTableModel
import javax.swing.text.BadLocationException
import javax.swing.text.DefaultHighlighter
import javax.swing.tree.DefaultTreeModel

import gui.VentanaCompilador
import lexico.AnalizadorLexico

import sintactico.AnalizadorSintactico


/**
 *  * @author Valentina osorio

 * Esta clase se encarga de controlar la interfaz grafica y sus eventos

 */
class ControladorVentana
/**
 * Contructor del controlador de la ventana, recibe la ventana a controlar
 *
 * @param ventanaCompilador
 */
(private val ventanaCompilador: VentanaCompilador) {
    internal var fichero: File? = null
    internal var guardado: Boolean = false

    init {
        organizar()
    }

    /**
     * Metodo para poner numeros en el editor
     */
    private fun organizar() {
        ventanaCompilador.linea?.text = ""
        var aux = ""
        for (i in 1..ventanaCompilador.textEditor?.lineCount!!) {
            aux += i.toString() + "\n"
        }
        ventanaCompilador.linea?.text = aux
    }

    /**
     * Metodo para cargar un archivo
     */
    fun cargar() {
        var aux =""
        var texto = ""
        try {
            val file = JFileChooser()
            val filter = FileNameExtensionFilter(".MG", "mg")
            file.fileFilter = filter
            file.showOpenDialog(null)
            fichero = file.selectedFile
            if (fichero != null) {
                val archivos = FileReader(fichero!!)
                val buff = BufferedReader(archivos)
                aux = buff.readLine()
                while (aux != null) {

                    texto += aux + "\n"
                    aux = buff.readLine()
                }
                buff.close()
            }
            ventanaCompilador.textEditor?.text = texto.substring(0, texto.length - 1)
            ventanaCompilador.editor?.text = ventanaCompilador.textEditor?.text
            organizar()

        } catch (ex: IOException) {
            JOptionPane.showMessageDialog(null, "$ex\nNo se ha encontrado el archivo", "Error",
                    JOptionPane.ERROR_MESSAGE)
        } catch (e2: NullPointerException) {
            JOptionPane.showMessageDialog(null, "Se ha cancelado el cargado")
        }

    }

    /**
     * Metodo para guardar un archivo
     */
    fun guardar() {
        val archivos: FileWriter
        if (guardado) {
            try {
                archivos = FileWriter(fichero!!)
                val buff = BufferedWriter(archivos)
                buff.write(ventanaCompilador.editor?.text)
                buff.close()

                JOptionPane.showMessageDialog(null, "Se ha guardado correctamente")
            } catch (e1: IOException) {
                JOptionPane.showMessageDialog(null, "No se ha guardado")
            } catch (e2: NullPointerException) {
                JOptionPane.showMessageDialog(null, "Se ha cancelado el guardado")
            }

        } else {
            try {
                val fileChooser = JFileChooser()
                fileChooser.dialogTitle = "Elija donde guardar"
                val filter = FileNameExtensionFilter(".MG", "mg")
                fileChooser.fileFilter = filter

                val userSelection = fileChooser.showSaveDialog(null)
                var fileToSave: File? = null
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.selectedFile
                }

                archivos = FileWriter(fileToSave!!.absolutePath + ".MG")
                val buff = BufferedWriter(archivos)
                buff.write(ventanaCompilador.editor?.text)
                buff.close()

                JOptionPane.showMessageDialog(null, "Se ha guardado correctamente")
                fichero = fileToSave.absoluteFile
                guardado = true
            } catch (e1: IOException) {
                JOptionPane.showMessageDialog(null, "No se ha guardao")
            } catch (e2: NullPointerException) {
                JOptionPane.showMessageDialog(null, "Se ha cancelado el guardado")
            }

        }
    }

    /**
     * Metodo para crear un nuevo archivo
     */
    fun crear() {
        ventanaCompilador.editor?.text = ""
        fichero = null
        ventanaCompilador.textEditor?.requestFocus()
        guardado = false
        ventanaCompilador.panelSimbolos?.removeAll()
        ventanaCompilador.panelErrores?.removeAll()
    }

    /**
     * Metodo para la ejecucion del compilador
     */
    fun compilar() {
        ventanaCompilador.isCompilado = false
        ventanaCompilador.analizadorLexico = ventanaCompilador.editor?.text?.let { AnalizadorLexico(it) }
        ventanaCompilador.analizadorLexico!!.analizar()
        agregarSimbolos()
        agregarErrores()
        if (ventanaCompilador.analizadorLexico!!.tablaErrores.size == 0) {
            ventanaCompilador.analizadorSintactico = AnalizadorSintactico(ventanaCompilador.analizadorLexico!!.tablaSimbolos)
            ventanaCompilador.analizadorSintactico!!.analizar()
            agregarErroresSintacticos()
            print("jklhaf")
            agregarArbolVisual()

        }

    }

    /**
     * Metodo para agregar simbolos a la tabla de la interfaz
     */
    private fun agregarSimbolos() {
        val fila_simbolo = arrayOf("Lexema", "Columna", "Fila", "Categoria")
        val modelo_simbolo = DefaultTableModel(fila_simbolo, 0)
        for (token in ventanaCompilador.analizadorLexico?.tablaSimbolos!!) {

            val columnas = arrayOf(token.lexema, token.columna, token.fila, token.categoria.toString())
            modelo_simbolo.addRow(columnas)
        }

        ventanaCompilador.panelSimbolos!!.removeAll()
        ventanaCompilador.simbolos = JTable(modelo_simbolo)
        ventanaCompilador.simbolos!!.background = ventanaCompilador.editor!!.background
        ventanaCompilador.simbolos!!.foreground = ventanaCompilador.editor!!.foreground
        ventanaCompilador.panelSimbolos?.add(JScrollPane(ventanaCompilador.simbolos), BorderLayout.CENTER)
    }

    /**
     * Metodo para agregar errores a la tabla de la interfaz
     */
    private fun agregarErrores() {
        val fila_error = arrayOf("Lexema", "Columna", "Fila", "Categoria")
        val modelo_simbolo = DefaultTableModel(fila_error, 0)
        for (token in ventanaCompilador.analizadorLexico?.tablaErrores!!) {
            val columnas = arrayOf(token.lexema, token.columna, token.fila, token.categoria.toString())
            modelo_simbolo.addRow(columnas)
        }

        ventanaCompilador.panelErrores!!.removeAll()
        ventanaCompilador.errores = JTable(modelo_simbolo)
        ventanaCompilador.errores!!.background = ventanaCompilador.editor!!.background
        ventanaCompilador.errores!!.foreground = ventanaCompilador.editor!!.foreground
        ventanaCompilador.panelErrores?.add(JScrollPane(ventanaCompilador.errores), BorderLayout.CENTER)

        for (token in ventanaCompilador.analizadorLexico!!.tablaErrores) {
            val highlightPainter = DefaultHighlighter.DefaultHighlightPainter(
                    if (ventanaCompilador.background == Color.BLACK)
                        Color(180, 0, 0)
                    else
                        Color(255, 0, 0))
            try {
                ventanaCompilador.editor?.highlighter!!.addHighlight(token.columnaReal,
                        token.columnaReal + token.lexema!!.length, highlightPainter)
            } catch (e: BadLocationException) {
                e.printStackTrace()
            }

        }

        JOptionPane.showMessageDialog(null, "Compilado con "
                + ventanaCompilador!!.analizadorLexico!!.tablaErrores.size + " errores lexicos")
    }

    /**
     * Método para señalar errores al editor
     */
    private fun agregarErroresSintacticos() {
        val fila_error = arrayOf("Mensaje", "Columna", "Fila")
        val modelo_simbolo = DefaultTableModel(fila_error, 0)
        for (errorSintactico in ventanaCompilador.analizadorSintactico!!.tablaErrores!!) {
            val columnas = arrayOf(errorSintactico.mensaje, errorSintactico.columna, errorSintactico!!.fila)
            modelo_simbolo.addRow(columnas)
        }

        ventanaCompilador.panelErroresSintacticos!!.removeAll()
        ventanaCompilador.erroresSintacticos = JTable(modelo_simbolo)
        ventanaCompilador.erroresSintacticos!!.background = ventanaCompilador.editor!!.background
        ventanaCompilador.erroresSintacticos!!.foreground = ventanaCompilador.editor!!.foreground
        ventanaCompilador.panelErroresSintacticos?.add(JScrollPane(ventanaCompilador.erroresSintacticos),
                BorderLayout.CENTER)

        for (errorSintactico in ventanaCompilador.analizadorSintactico?.tablaErrores!!) {
            val highlightPainter = DefaultHighlighter.DefaultHighlightPainter(
                    if (ventanaCompilador.background == Color.BLACK)
                        Color(180, 0, 0)
                    else
                        Color(255, 0, 0))
            try {
                ventanaCompilador.editor?.highlighter?.addHighlight(errorSintactico.columna,
                        errorSintactico.columna + 4, highlightPainter)
            } catch (e: BadLocationException) {
                e.printStackTrace()
            }

        }

        JOptionPane.showMessageDialog(null, "Compilado con "
                + ventanaCompilador!!.analizadorSintactico!!.tablaErrores!!.size + " errores sintacticos")
    }

    /**
     * Método de arbol visual
     */
    private fun agregarArbolVisual() {
        print(ventanaCompilador.analizadorSintactico!!.unidadDeCompilacion!!.arbolVisual)
        //if (ventanaCompilador.getAnalizadorSintactico().getTablaErrores().size() != 0) {
        ventanaCompilador.arbolVisual?.model = DefaultTreeModel(
                ventanaCompilador.analizadorSintactico?.unidadDeCompilacion?.arbolVisual)



        //}

    }

    /**
     * Insertar los numeros a partir de la escritura
     */
    fun leerSaltoLinea() {
        ventanaCompilador.textEditor!!.text = ventanaCompilador.editor!!.text
        organizar()
    }
}
