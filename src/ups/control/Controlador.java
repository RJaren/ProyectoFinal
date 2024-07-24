package ups.control;

import ups.vista.Vista;
import ups.modelo.Modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;
    private Thread bfsThread, dfsThread, cacheThread, normalThread;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Crear")) {
            int filas = Integer.parseInt(vista.getFilas());
            int columnas = Integer.parseInt(vista.getColumnas());
            vista.crearCelda(filas, columnas);
        } else if (command.equals("Resetear")) {
            resetear();
        } else if (command.equals("Iniciar con BFS")) {
            iniciarBFS();
        } else if (command.equals("Iniciar con DFS")) {
            iniciarDFS();
        } else if (command.equals("Iniciar con Cache")) {
            iniciarCache();
        } else if (command.equals("Iniciar con Normal")) {
            iniciarNormal();
        } else if (command.equals("Establecer Inicio")) {
            vista.settingInicio = true;
        } else if (command.equals("Establecer Fin")) {
            vista.settingFin = true;
        }
    }

    private void iniciarBFS() {
        if (!vista.isInicioSet() || !vista.isFinSet()) {
            vista.mostrarMensaje("Establecer inicio y fin antes de comenzar el recorrido.");
            return;
        }

        bfsThread = new Thread(() -> {
            String resultado = modelo.bfs(vista.getInicioX(), vista.getInicioY());
            vista.mostrarMensaje(resultado);
        });

        bfsThread.start();
    }

    private void iniciarDFS() {
        if (!vista.isInicioSet() || !vista.isFinSet()) {
            vista.mostrarMensaje("Establecer inicio y fin antes de comenzar el recorrido.");
            return;
        }

        dfsThread = new Thread(() -> {
            String resultado = modelo.dfs(vista.getInicioX(), vista.getInicioY());
            vista.mostrarMensaje(resultado);
        });

        dfsThread.start();
    }

    private void iniciarCache() {
        if (!vista.isInicioSet() || !vista.isFinSet()) {
            vista.mostrarMensaje("Establecer inicio y fin antes de comenzar el recorrido.");
            return;
        }

        cacheThread = new Thread(() -> {
            String resultado = modelo.cache(vista.getInicioX(), vista.getInicioY());
            vista.mostrarMensaje(resultado);
        });

        cacheThread.start();
    }

    private void iniciarNormal() {
        if (!vista.isInicioSet() || !vista.isFinSet()) {
            vista.mostrarMensaje("Establecer inicio y fin antes de comenzar el recorrido.");
            return;
        }

        normalThread = new Thread(() -> {
            String resultado = modelo.normal(vista.getInicioX(), vista.getInicioY());
            vista.mostrarMensaje(resultado);
        });

        normalThread.start();
    }

    private void resetear() {
        if (bfsThread != null && bfsThread.isAlive()) {
            bfsThread.interrupt();
        }

        if (dfsThread != null && dfsThread.isAlive()) {
            dfsThread.interrupt();
        }

        if (cacheThread != null && cacheThread.isAlive()) {
            cacheThread.interrupt();
        }

        if (normalThread != null && normalThread.isAlive()) {
            normalThread.interrupt();
        }

        vista.resetCelda();
    }
}
