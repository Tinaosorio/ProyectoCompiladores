package gui
import java.awt.BorderLayout
import java.awt.Font

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

import javax.swing.GroupLayout
import javax.swing.GroupLayout.Alignment
import javax.swing.JEditorPane
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTabbedPane
import javax.swing.JTable
import javax.swing.JTextArea
import javax.swing.JTree
import javax.swing.LayoutStyle.ComponentPlacement
import javax.swing.border.TitledBorder
import controlador.ControladorVentana
import lexico.AnalizadorLexico

/**
 * Esta clase contiene la interfaz grafica del compilador
 * @author VALENTINA OSORIO
 */
class VentanaCompilador : JFrame(), ActionListener, KeyListener {

    var panelEditor: JPanel? = null
    var panelErrores: JPanel? = null
    var panelSimbolos: JPanel? = null
    private var panel: JPanel? = null
    /**
     * @return the panelArbol
     */
    /**
     * @param panelArbol
     * the panelArbol to set
     */
    var panelArbol: JPanel? = null
    var errores: JTable? = null
    var simbolos: JTable? = null
    private var scroll: JScrollPane? = null

    /**
     * @return the scrollSimbolos
     */
    /**
     * @param scrollSimbolos
     * the scrollSimbolos to set
     */
    var scrollSimbolos: JScrollPane? = null
    /**
     * @return the scrollErrores
     */
    /**
     * @param scrollErrores
     * the scrollErrores to set
     */
    var scrollErrores: JScrollPane? = null

    var mnEjecutar: JMenu? = null
    var mnArchivo: JMenu? = null
    var menuBarWindow: JMenuBar? = null
        private set
    var mntmAbrir: JMenuItem? = null

    private var mntmCompilar: JMenuItem? = null
    var mntmNuevo: JMenuItem? = null
    var mntmGuardar: JMenuItem? = null
    var textEditor: JTextArea? = null
    var editor: JEditorPane? = null
    var linea: JEditorPane? = null
    var controladorVentana: ControladorVentana? = null
    private var gl_panel: GroupLayout? = null
    var analizadorLexico: AnalizadorLexico? = null

    /**
     * @return the compilado
     */
    /**
     * @param compilado
     * the compilado to set
     */
    var isCompilado: Boolean = false

    /**
     * Metodo constructor, aca se define el molde grafico del compilador
     */
    init {
        isResizable = false
        title = "Compilador"

        setBounds(100, 100, 1280, 720)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isCompilado = false
        init()
        controladorVentana = ControladorVentana(this)
        isVisible = true
        setLocationRelativeTo(null)
    }

    /**
     * Inicializa los componentes graficos de la interfaz y los organiza dentro de
     * llea
     */
    private fun init() {

        // Molde principal
        contentPane.layout = null
        val tabbedPane = JTabbedPane(JTabbedPane.TOP)
        tabbedPane.setBounds(0, 0, 1280, 720)
        tabbedPane.border = TitledBorder(null, "Compilador", TitledBorder.LEADING, TitledBorder.TOP, null, null)

        panelSimbolos = JPanel(BorderLayout())
        panelSimbolos!!.setBounds(0, 0, 1280, 720)
        panelSimbolos!!.border = TitledBorder(null, "Simbolos", TitledBorder.LEADING, TitledBorder.TOP, null, null)

        panelErrores = JPanel(BorderLayout())
        panelErrores!!.setBounds(0, 0, 1280, 720)
        panelErrores!!.border = TitledBorder(null, "Errores", TitledBorder.LEADING, TitledBorder.TOP, null, null)

        panelEditor = JPanel(null)
        panelEditor!!.setBounds(0, 0, 1280, 720)
        panelEditor!!.border = TitledBorder(null, "Editor", TitledBorder.LEADING, TitledBorder.TOP, null, null)

        panelArbol = JPanel(null)
        panelArbol!!.setBounds(0, 0, 1280, 720)
        panelArbol!!.border = TitledBorder(null, "Arbol", TitledBorder.LEADING, TitledBorder.TOP, null, null)
        contentPane.add(tabbedPane)

        tabbedPane.addTab("Compilador", null, panelEditor, null)
        tabbedPane.addTab("Simbolos", null, panelSimbolos, null)
        tabbedPane.addTab("Errores", null, panelErrores, null)


        // Panel editor
        linea = JEditorPane()
        linea!!.text = "1\n"
        linea!!.isEditable = false

        editor = JEditorPane()

        menuBarWindow = JMenuBar()
        menuBarWindow!!.setBounds(0, 20, 170, 20)

        mnArchivo = JMenu("Archivo")
        mnArchivo!!.font = Font("Century Gothic", Font.BOLD, 13)
        menuBarWindow!!.add(mnArchivo)

        mnEjecutar = JMenu("Analizar")
        mnEjecutar!!.font = Font("Century Gothic", Font.BOLD, 13)
        menuBarWindow!!.add(mnEjecutar)



        mntmAbrir = JMenuItem("Abrir")
        mntmAbrir!!.addActionListener(this)
        mnArchivo!!.add(mntmAbrir)

        mntmNuevo = JMenuItem("Nuevo")
        mntmNuevo!!.addActionListener(this)
        mnArchivo!!.add(mntmNuevo)

        mntmGuardar = JMenuItem("Guardar")
        mntmGuardar!!.addActionListener(this)
        mnArchivo!!.add(mntmGuardar)

        mntmCompilar = JMenuItem("Analizar")
        mntmCompilar!!.addActionListener(this)
        mnEjecutar!!.add(mntmCompilar)

        panelEditor!!.add(menuBarWindow)

        scroll = JScrollPane()
        scroll!!.setBounds(2, 37, 1253, 601)
        panelEditor!!.add(scroll)

        panel = JPanel()
        scroll!!.setViewportView(panel)

        gl_panel = GroupLayout(panel!!)
        gl_panel!!.setHorizontalGroup(gl_panel!!.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel!!.createSequentialGroup().addContainerGap()
                        .addComponent(linea!!, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(editor!!, GroupLayout.DEFAULT_SIZE, 393, java.lang.Short.MAX_VALUE.toInt()).addContainerGap()))
        gl_panel!!.setVerticalGroup(gl_panel!!.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
                gl_panel!!.createSequentialGroup().addContainerGap()
                        .addGroup(gl_panel!!.createParallelGroup(Alignment.TRAILING)
                                .addComponent(editor!!, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, java.lang.Short.MAX_VALUE.toInt())
                                .addComponent(linea!!, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 230, java.lang.Short.MAX_VALUE.toInt()))
                        .addContainerGap()))
        panel!!.layout = gl_panel

        editor!!.addKeyListener(this)

        textEditor = JTextArea()
        textEditor!!.bounds = editor!!.bounds
        textEditor!!.lineWrap = true

        // Panel errores
        errores = JTable()
        errores!!.setBounds(2, 37, 1253, 601)

        // Panel simbolos
        simbolos = JTable()
        errores!!.setBounds(2, 37, 1253, 601)


        scrollSimbolos = JScrollPane(simbolos)
        scrollSimbolos!!.setBounds(2, 37, 1253, 601)
        panelSimbolos!!.add(scrollSimbolos!!, BorderLayout.CENTER)

        scrollErrores = JScrollPane(errores)
        scrollErrores!!.setBounds(2, 37, 1253, 601)
        panelErrores!!.add(scrollErrores!!, BorderLayout.CENTER)

    }

    override fun keyTyped(e: KeyEvent) {
        if (e.keyChar == '\n' || e.keyChar == '\b' || e.keyChar.toInt() == 127) {
            controladorVentana!!.leerSaltoLinea()
        }
        else {
            editor!!.highlighter.removeAllHighlights()
        }
    }

    override fun keyPressed(e: KeyEvent) {
        // TODO Auto-generated method stub
    }

    override fun keyReleased(e: KeyEvent) {
        // TODO Auto-generated method stub
    }

    override fun actionPerformed(e: ActionEvent) {
        if (e.source === mntmAbrir) {
            controladorVentana!!.cargar()
        }
        else if (e.source === mntmGuardar) {
            controladorVentana!!.guardar()
        }
        else if (e.source === mntmCompilar) {
            controladorVentana!!.compilar()
        }

        else if (e.source === mntmNuevo) {
            controladorVentana!!.crear()
        }
    }

    fun setMenuBar(menuBar: JMenuBar) {
        this.menuBarWindow = menuBar
    }

    companion object {

        private val serialVersionUID = 3622765441129668108L
    }
}
