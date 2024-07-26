package ups.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ups.control.Controlador;

public class Vista extends JFrame {

    private TextField txtColum, txtFil;
    private Button btnCrear, btnStartBFS, btnStartDFS, btnStarCache, btnNormal, btnReset, btnSetInicio, btnSetFin, btnLimpiarRecorrido;
    private JPanel gridPanel;
    private Celda[][] celdas;
    private int filas, columnas;
    private int inicioX = -1, inicioY = -1, finX = -1, finY = -1;
    public boolean settingInicio = false, settingFin = false;

    public Vista() {
        setTitle("Maze Solver Full");
        this.setLayout(new BorderLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnCrear = new Button("Crear");
        btnStartBFS = new Button("Iniciar con BFS");
        btnStartDFS = new Button("Iniciar con DFS");
        btnStarCache = new Button("Iniciar con Cache");
        btnNormal = new Button("Iniciar con Normal");
        btnReset = new Button("Resetear");
        btnLimpiarRecorrido = new Button("Limpiar Recorrido");
        txtColum = new TextField();
        txtFil = new TextField();
        btnSetInicio = new Button("Establecer Inicio");
        btnSetFin = new Button("Establecer Fin");

        Panel panel = new Panel(new GridLayout(4, 2));
        panel.add(new Label("Columnas: "));
        panel.add(txtColum);
        panel.add(new Label("Filas: "));
        panel.add(txtFil);
        panel.add(btnCrear);
        panel.add(btnReset);
        panel.add(btnSetInicio);
        panel.add(btnSetFin);

        add(panel, BorderLayout.NORTH);

        Panel panel2 = new Panel(new GridLayout(4, 2)); // Cambiado de 3 a 4 para acomodar el nuevo botón
        panel2.add(btnStartBFS);
        panel2.add(btnStartDFS);
        panel2.add(btnStarCache);
        panel2.add(btnNormal);
        panel2.add(btnLimpiarRecorrido); // Añadir botón "Limpiar Recorrido"

        add(panel2, BorderLayout.WEST);

        gridPanel = new JPanel();
        add(gridPanel, BorderLayout.CENTER);
    }

    public void setControlador(Controlador controlador) {
        btnCrear.addActionListener(controlador);
        btnReset.addActionListener(controlador);
        btnNormal.addActionListener(controlador);
        btnStartBFS.addActionListener(controlador);
        btnStartDFS.addActionListener(controlador);
        btnStarCache.addActionListener(controlador);
        btnSetInicio.addActionListener(controlador);
        btnSetFin.addActionListener(controlador);
        btnLimpiarRecorrido.addActionListener(controlador); // Añadir acción al botón
    }

    public String getColumnas() {
        return txtColum.getText();
    }

    public String getFilas() {
        return txtFil.getText();
    }

    public void setInicio(int x, int y) {
        if (settingInicio) {
            if (inicioX != -1 && inicioY != -1) {
                celdas[inicioY][inicioX].setStart(false); // Usar el método setStart
            }
            inicioX = x;
            inicioY = y;
            celdas[y][x].setStart(true); // Usar el método setStart
            settingInicio = false;
        }
    }

    public void setFin(int x, int y) {
        if (settingFin) {
            if (finX != -1 && finY != -1) {
                celdas[finY][finX].setEnd(false); // Usar el método setEnd
            }
            finX = x;
            finY = y;
            celdas[y][x].setEnd(true); // Usar el método setEnd
            settingFin = false;
        }
    }

    public void crearCelda(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(filas, columnas));
        celdas = new Celda[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Celda celda = new Celda(j, i, this); 
                celdas[i][j] = celda;
                gridPanel.add(celda);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public void resetCelda() {
        gridPanel.removeAll();
        gridPanel.revalidate();
        gridPanel.repaint();
        inicioX = inicioY = finX = finY = -1;
    }

    public void limpiarRecorrido() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!celdas[i][j].isBlocked() && !celdas[i][j].isStart() && !celdas[i][j].isEnd()) {
                    celdas[i][j].resetColor();
                }
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public boolean isInicioSet() {
        return inicioX != -1 && inicioY != -1;
    }

    public boolean isFinSet() {
        return finX != -1 && finY != -1;
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public int getFinX() {
        return finX;
    }

    public int getFinY() {
        return finY;
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public boolean isSettingInicio() {
        return settingInicio;
    }

    public void setSettingInicio(boolean settingInicio) {
        this.settingInicio = settingInicio;
    }

    public boolean isSettingFin() {
        return settingFin;
    }

    public void setSettingFin(boolean settingFin) {
        this.settingFin = settingFin;
    }
}