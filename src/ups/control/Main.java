package ups.control;

import ups.vista.Vista;
import ups.control.Controlador;
import ups.modelo.Modelo;

public class Main {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo(vista);
        new Controlador(vista, modelo);
        vista.setVisible(true);
    }
}
