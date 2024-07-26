package ups.modelo;

import ups.vista.Celda;
import ups.vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Modelo {
    private Vista vista;
    private Map<String, String> cache;

    public Modelo(Vista vista) {
        this.vista = vista;
        this.cache = new HashMap<>();
    }

    public void bfs(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Queue<int[]> queue = new LinkedList<>();
        List<String> posiciones = new ArrayList<>();
        Map<String, String> predecesores = new HashMap<>();

        queue.add(new int[]{startX, startY});
        predecesores.put(startX + "," + startY, null);

        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            posiciones.add("(" + x + "," + y + ")");

            if (x == vista.getFinX() && y == vista.getFinY()) {
                long endTime = System.currentTimeMillis();
                double duration = (endTime - startTime) / 1000.0;
                mostrarMensajeConScroll(posiciones, duration);
                return;
            }

            if (visitado[x][y]) continue;

            visitado[x][y] = true;
            vista.getCeldas()[y][x].setVisitedColor();
            pausar();

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas && !vista.getCeldas()[newY][newX].isBlocked() && !visitado[newX][newY]) {
                    queue.add(new int[]{newX, newY});
                    predecesores.put(newX + "," + newY, x + "," + y);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        mostrarMensajeConScroll(posiciones, duration);
    }

    public void dfs(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Stack<int[]> stack = new Stack<>();
        List<String> posiciones = new ArrayList<>();
        stack.push(new int[]{startX, startY});

        long startTime = System.currentTimeMillis();

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            posiciones.add("(" + x + "," + y + ")");

            if (x == vista.getFinX() && y == vista.getFinY()) {
                long endTime = System.currentTimeMillis();
                double duration = (endTime - startTime) / 1000.0;
                mostrarMensajeConScroll(posiciones, duration);
                return;
            }

            if (visitado[x][y]) continue;

            visitado[x][y] = true;
            vista.getCeldas()[y][x].setVisitedColor();
            pausar();

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas && !vista.getCeldas()[newY][newX].isBlocked() && !visitado[newX][newY]) {
                    stack.push(new int[]{newX, newY});
                }
            }
        }

        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        mostrarMensajeConScroll(posiciones, duration);
    }

    public void cache(int startX, int startY) {
        cache.clear();
        List<String> posiciones = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        boolean encontrado;
        try {
            encontrado = recorridoCache(startX, startY, posiciones);
            if (!encontrado) {
                throw new Exception("No se encontró un camino con el método de caché.");
            }
        } catch (Exception e) {
            mostrarMensajeConScroll(Collections.singletonList("Error en la búsqueda con caché: " + e.getMessage()), 0);
            return;
        }
        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        mostrarMensajeConScroll(posiciones, duration);
    }

    private boolean recorridoCache(int x, int y, List<String> posiciones) {
        String key = x + "," + y;

        if (cache.containsKey(key)) {
            return cache.get(key).equals("Encontrado");
        }

        if (x < 0 || x >= vista.getCeldas()[0].length || y < 0 || y >= vista.getCeldas().length) {
            cache.put(key, "No hay camino");
            return false;
        }
        if (vista.getCeldas()[y][x].isBlocked()) {
            cache.put(key, "Bloqueado");
            return false;
        }
        if (x == vista.getFinX() && y == vista.getFinY()) {
            cache.put(key, "Encontrado");
            posiciones.add("(" + x + "," + y + ")");
            return true;
        }
        cache.put(key, "Visited");
        posiciones.add("(" + x + "," + y + ")");
        vista.getCeldas()[y][x].setVisitedColor();
        pausar();

        boolean encontrado = false;
        for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (recorridoCache(newX, newY, posiciones)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public void normal(int startX, int startY) {
        List<String> posiciones = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        boolean encontrado;

        try {
            encontrado = recorridoNormal(startX, startY, posiciones);
            if (!encontrado) {
                throw new Exception("No se encontró un camino con el método normal.");
            }
        } catch (Exception e) {
            mostrarMensajeConScroll(Collections.singletonList("Error en la búsqueda normal: " + e.getMessage()), 0);
            return;
        }

        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        mostrarMensajeConScroll(posiciones, duration);
    }

    private boolean recorridoNormal(int x, int y, List<String> posiciones) {
        if (x < 0 || x >= vista.getCeldas()[0].length || y < 0 || y >= vista.getCeldas().length) {
            return false;
        }
        if (vista.getCeldas()[y][x].isBlocked()) {
            return false;
        }
        if (x == vista.getFinX() && y == vista.getFinY()) {
            posiciones.add("(" + x + "," + y + ")");
            return true;
        }
        vista.getCeldas()[y][x].setVisitedColor();
        pausar();
        posiciones.add("(" + x + "," + y + ")");
        for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (recorridoNormal(newX, newY, posiciones)) {
                return true;
            }
        }

        return false;
    }
    
    private void pausar() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void mostrarMensajeConScroll(List<String> posiciones, double duration) {
        StringBuilder sb = new StringBuilder();
        sb.append("Posiciones recorridas:\n");
        for (String pos : posiciones) {
            sb.append(pos).append("\n");
        }
        sb.append("Tiempo total: ").append(duration).append(" segundos.");

        JTextArea textArea = new JTextArea();
        textArea.setText(sb.toString());
        textArea.setEditable(false);
        textArea.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Resultados del Algoritmo", JOptionPane.INFORMATION_MESSAGE);
    }

}
