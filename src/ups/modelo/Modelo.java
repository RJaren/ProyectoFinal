package ups.modelo;

import ups.vista.Celda;
import ups.vista.Vista;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Modelo {
    private Vista vista;
    private Map<String, String> cache;
    private Celda[][] celdas;

    public Modelo(Vista vista) {
        this.vista = vista;
        this.cache = new HashMap<>();
    }
    public void bfs(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Queue<int[]> queue = new LinkedList<>();
        Map<String, String> predecesores = new HashMap<>();
        Set<String> retrocedidas = new LinkedHashSet<>();

        queue.add(new int[]{startX, startY});
        predecesores.put(startX + "," + startY, null);

        long startTime = System.currentTimeMillis();
        boolean encontrado = false;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            if (x == vista.getFinX() && y == vista.getFinY()) {
                encontrado = true;
                break;
            }

            if (visitado[x][y]) continue;

            visitado[x][y] = true;
            vista.getCeldas()[y][x].setVisitedColor();
            pausar();

            boolean avanzable = false;
            List<int[]> celdasAdyacentes = new ArrayList<>();
            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas && !vista.getCeldas()[newY][newX].isBlocked() && !visitado[newX][newY]) {
                    queue.add(new int[]{newX, newY});
                    predecesores.put(newX + "," + newY, x + "," + y);
                    avanzable = true;
                    celdasAdyacentes.add(new int[]{newX, newY});
                }
            }

            if (!avanzable) {
              
                String coord = x + "," + y;
                retrocedidas.add(coord);
                while (predecesores.containsKey(coord)) {
                    coord = predecesores.get(coord);
                    if (coord != null) retrocedidas.add(coord);
                }

                for (String retrocedida : retrocedidas) {
                    String[] parts = retrocedida.split(",");
                    int retroX = Integer.parseInt(parts[0]);
                    int retroY = Integer.parseInt(parts[1]);
                    boolean hayCaminoAlternativo = false;
                    for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                        int newX = retroX + dir[0];
                        int newY = retroY + dir[1];
                        if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas &&
                            !vista.getCeldas()[newY][newX].isBlocked() && !retrocedidas.contains(newX + "," + newY)) {
                            hayCaminoAlternativo = true;
                            break;
                        }
                    }
                    if (!hayCaminoAlternativo) {
                        vista.getCeldas()[retroY][retroX].setDiscardedColor(); 
                    }
                }
            }
        }

        if (encontrado) {
            List<String> camino = new LinkedList<>();
            String current = vista.getFinX() + "," + vista.getFinY();
            while (current != null) {
                camino.add(0, "(" + current.replace(",", ", ") + ")");
                current = predecesores.get(current);
            }

            long endTime = System.currentTimeMillis();
            double duration = (endTime - startTime) / 1000.0;
            mostrarMensajeConScroll(camino, duration);
        } else {
            long endTime = System.currentTimeMillis();
            double duration = (endTime - startTime) / 1000.0;
            mostrarMensajeConScroll(Collections.singletonList("No se encontró un camino."), duration);
        }
    }


    public void dfs(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Stack<int[]> stack = new Stack<>();
        Map<String, String> predecesores = new HashMap<>();
        Set<String> retrocedidas = new LinkedHashSet<>(); 

        stack.push(new int[]{startX, startY});
        predecesores.put(startX + "," + startY, null);

        long startTime = System.currentTimeMillis();
        boolean encontrado = false;

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];

            if (x == vista.getFinX() && y == vista.getFinY()) {
                encontrado = true;
                break;
            }

            if (visitado[x][y]) continue;

            visitado[x][y] = true;
            vista.getCeldas()[y][x].setVisitedColor(); 
            pausar();

            boolean avanzable = false;
            List<int[]> celdasAdyacentes = new ArrayList<>();
            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas && !vista.getCeldas()[newY][newX].isBlocked() && !visitado[newX][newY]) {
                    stack.push(new int[]{newX, newY});
                    predecesores.put(newX + "," + newY, x + "," + y);
                    avanzable = true;
                    celdasAdyacentes.add(new int[]{newX, newY});
                }
            }

            if (!avanzable) {
                String coord = x + "," + y;
                retrocedidas.add(coord);
                while (predecesores.containsKey(coord)) {
                    coord = predecesores.get(coord);
                    if (coord != null) retrocedidas.add(coord);
                }
                for (String retrocedida : retrocedidas) {
                    String[] parts = retrocedida.split(",");
                    int retroX = Integer.parseInt(parts[0]);
                    int retroY = Integer.parseInt(parts[1]);
                    boolean hayCaminoAlternativo = false;
                    for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                        int newX = retroX + dir[0];
                        int newY = retroY + dir[1];
                        if (newX >= 0 && newX < filas && newY >= 0 && newY < columnas &&
                            !vista.getCeldas()[newY][newX].isBlocked() && !retrocedidas.contains(newX + "," + newY)) {
                            hayCaminoAlternativo = true;
                            break;
                        }
                    }
                    if (!hayCaminoAlternativo) {
                        vista.getCeldas()[retroY][retroX].setDiscardedColor(); 
                    }
                }
            }
        }

        if (encontrado) {
            List<String> camino = new LinkedList<>();
            String current = vista.getFinX() + "," + vista.getFinY();
            while (current != null) {
                camino.add(0, "(" + current.replace(",", ", ") + ")");
                current = predecesores.get(current);
            }

            long endTime = System.currentTimeMillis();
            double duration = (endTime - startTime) / 1000.0;
            mostrarMensajeConScroll(camino, duration);
        } else {
            long endTime = System.currentTimeMillis();
            double duration = (endTime - startTime) / 1000.0;
            mostrarMensajeConScroll(Collections.singletonList("No se encontró un camino."), duration);
        }
    }


    public void cache(int startX, int startY) {
        cache.clear();
        List<String> posiciones = new ArrayList<>();
        Set<String> retrocedidas = new LinkedHashSet<>(); 
        long startTime = System.currentTimeMillis();
        boolean encontrado;
        try {
            encontrado = recorridoCache(startX, startY, posiciones, retrocedidas); 
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

    private boolean recorridoCache(int x, int y, List<String> posiciones, Set<String> retrocedidas) {
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
        List<int[]> celdasAdyacentes = new ArrayList<>();
        for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (!cache.containsKey(newX + "," + newY)) {
                celdasAdyacentes.add(new int[]{newX, newY});
            }
        }

        for (int[] dir : celdasAdyacentes) {
            int newX = dir[0];
            int newY = dir[1];
            if (recorridoCache(newX, newY, posiciones, retrocedidas)) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            retrocedidas.add(key);
            vista.getCeldas()[y][x].setDiscardedColor();
        }

        return encontrado;
    }


    public void normal(int startX, int startY) {
        int filas = vista.getCeldas().length;
        int columnas = vista.getCeldas()[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        List<String> posiciones = new ArrayList<>();
        Map<String, String> predecesores = new HashMap<>();
        Set<String> retrocedidas = new LinkedHashSet<>(); 
        long startTime = System.currentTimeMillis();

        boolean encontrado = recorridoNormal(startX, startY, visitado, posiciones, predecesores, retrocedidas); 

        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;

        if (encontrado) {
            mostrarMensajeConScroll(posiciones, duration);
        } else {
            mostrarMensajeConScroll(Collections.singletonList("No se encontró un camino."), duration);
        }
    }
    private boolean recorridoNormal(int x, int y, boolean[][] visitado, List<String> posiciones, Map<String, String> predecesores, Set<String> retrocedidas) {

        if (x < 0 || x >= vista.getCeldas()[0].length || y < 0 || y >= vista.getCeldas().length) {
            return false;
        }
        if (vista.getCeldas()[y][x].isBlocked() || visitado[x][y]) {
            return false;
        }
        if (x == vista.getFinX() && y == vista.getFinY()) {
            posiciones.add("(" + x + "," + y + ")");
            return true;
        }

        visitado[x][y] = true;
        posiciones.add("(" + x + "," + y + ")");
        vista.getCeldas()[y][x].setVisitedColor(); 
        pausar();

        boolean encontrado = false;
        List<int[]> celdasAdyacentes = new ArrayList<>();
        for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < vista.getCeldas()[0].length && newY >= 0 && newY < vista.getCeldas().length && !vista.getCeldas()[newY][newX].isBlocked() && !visitado[newX][newY]) {
                celdasAdyacentes.add(new int[]{newX, newY});
            }
        }

        for (int[] dir : celdasAdyacentes) {
            int newX = dir[0];
            int newY = dir[1];
            if (recorridoNormal(newX, newY, visitado, posiciones, predecesores, retrocedidas)) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            retrocedidas.add(x + "," + y);
            vista.getCeldas()[y][x].setDiscardedColor();
            posiciones.remove(posiciones.size() - 1); 
        }

        return encontrado;
    }

    private void mostrarMensajeConScroll(List<String> mensaje, double duration) {
        StringBuilder sb = new StringBuilder();
        for (String line : mensaje) {
            sb.append(line).append("\n");
        }
        sb.append("Tiempo: ").append(duration).append(" segundos");
        JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sb.toString())), "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void pausar() {
        try {
            Thread.sleep(100); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void setCeldas(Celda[][] celdas) {
        this.celdas = celdas;
    }

}

