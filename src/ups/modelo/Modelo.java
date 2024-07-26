package ups.modelo;

import ups.vista.Celda;
import ups.vista.Vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Modelo {
    private Vista vista;

    public Modelo(Vista vista) {
        this.vista = vista;
    }
    public String bfs(int startX, int startY) {
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
                return generarMensaje(posiciones, duration);
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

        return "No se encontró un camino con BFS.";
    }

    public String dfs(int startX, int startY) {
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
                return generarMensaje(posiciones, duration);
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

        return "No se encontró un camino con DFS.";
    }

    public String cache(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Queue<int[]> queue = new LinkedList<>();
        List<String> posiciones = new ArrayList<>();
        queue.add(new int[]{startX, startY});

        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (visitado[x][y]) continue;

            posiciones.add("(" + x + "," + y + ")");
            visitado[x][y] = true;
            vista.getCeldas()[x][y].setVisitedColor();
            pausar();

            if (x == vista.getFinX() && y == vista.getFinY()) {
                long endTime = System.currentTimeMillis();
                double duration = (endTime - startTime) / 1000.0;
                return generarMensaje(posiciones, duration);
            }

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas 
                        && !vista.getCeldas()[newX][newY].isBlocked() && !visitado[newX][newY]) {
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        return "No se encontró un camino con Cache.";
    }


    public String normal(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Queue<int[]> queue = new LinkedList<>();
        List<String> posiciones = new ArrayList<>();
        queue.add(new int[]{startX, startY});

        long startTime = System.currentTimeMillis();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (visitado[x][y]) continue;

            posiciones.add("(" + x + "," + y + ")");
            visitado[x][y] = true;
            vista.getCeldas()[x][y].setVisitedColor();
            pausar();

            if (x == vista.getFinX() && y == vista.getFinY()) {
                long endTime = System.currentTimeMillis();
                double duration = (endTime - startTime) / 1000.0;
                return generarMensaje(posiciones, duration);
            }

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas 
                        && !vista.getCeldas()[newX][newY].isBlocked() && !visitado[newX][newY]) {
                    queue.add(new int[]{newX, newY});
                }
            }
        }

        return "No se encontró un camino con Normal.";
    }

    private void pausar() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String generarMensaje(List<String> posiciones, double duration) {
        StringBuilder sb = new StringBuilder();
        sb.append("Posiciones recorridas:\n");
        for (String pos : posiciones) {
            sb.append(pos).append("\n");
        }
        sb.append("Tiempo total: ").append(duration).append(" segundos.");
        return sb.toString();
    }
}

