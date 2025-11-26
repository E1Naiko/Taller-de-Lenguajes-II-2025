package taller2.plataformatdl2.controller;

import taller2.plataformatdl2.view.CargaVista;
import javax.swing.SwingWorker;

public class CargaController {
    private CargaVista vista;

    public CargaController(String usuario) {
        this.vista = new CargaVista();
    }

    public void iniciarCarga() {
        //Mostramos la ventana de carga
        vista.setVisible(true);
    }
}