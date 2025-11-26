package taller2.plataformatdl2.view;

import javax.swing.*;
import java.awt.*;

public class CargaVista extends JFrame {

    private JLabel lblMensaje;
    private JLabel lblImagen;

    public CargaVista() {
        // Configuración de la ventana
        setTitle("Plataforma TDL2 - Cargando");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Para que no se cierre a la fuerza
        setLocationRelativeTo(null); // Para que este centrado
        setUndecorated(true); // Sin bordes para que este mas piola
        
        // Panel principal con borde
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        add(mainPanel);

        // --- El CENTRO: El GIF de Cargando Perrito ---
        lblImagen = new JLabel("", SwingConstants.CENTER);
        try {
            // Buscamos el gif
            ImageIcon gifCarga = new ImageIcon("src/taller2/proyectojava/img/TL2 Perrito de carga fondo transparente.gif");
            lblImagen.setIcon(gifCarga);
        } catch (Exception e) {
            lblImagen.setText("<html><div style='text-align: center;'>[GIF Cargando]<br>(Falta imagen)</div></html>");
        }
        mainPanel.add(lblImagen, BorderLayout.CENTER);

        // --- El SUR: Mensaje de texto ---
        lblMensaje = new JLabel("Cargando, un momento por favor...", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Margen abajo
        mainPanel.add(lblMensaje, BorderLayout.SOUTH);
    }

}
