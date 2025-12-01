package taller2.plataformatdl2.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class DetallesPeliculaVista extends JDialog {
    private final JPanel contenidoPanel = new JPanel();
    private JButton btnContinuar;
    private JButton btnCancelar;

    public DetallesPeliculaVista (Frame parent, Pelicula peli) {
        super(parent,true); // Para bloquear la pantalla de atras
        setTitle("Resultado de la busqueda");
        setBounds(100,100,600,400);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());

        contenidoPanel.setBackground(Color.WHITE);
        contenidoPanel.setBorder(new EmptyBorder(20,20,20,20));
        getContentPane().add(contenidoPanel, BorderLayout.CENTER);
        contenidoPanel.setLayout(new BorderLayout(20,0));

        // --- IZQUIERDA: POSTER DE PELI
        JLabel lblPoster = new JLabel("Cargando Imagen...");
        lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
        lblPoster.setPreferredSize(new Dimension(200,300));
        lblPoster.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        // Cargamos la imagen desde la URL de la API
        if (peli.getDireccionArchivo() != null && peli.getDireccionArchivo().startsWith("http")) {
            cargarImagenDesdeUrl(lblPoster, peli.getDireccionArchivo());
        } else {
            lblPoster.setText("Sin imagen");
        }
        contenidoPanel.add(lblPoster, BorderLayout.WEST);

        // --- CENTRO: Info de la peli ---
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        String titulo = peli.getMetadatos().getTitulo();
        String genero = peli.getGenero().toString();
        String sinopsis = peli.getMetadatos().getSinopsis();
        
        JLabel lblTitulo = new JLabel("<html><h2>" + titulo + "</h2></html>");
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(lblTitulo);
        
        JLabel lblGenero = new JLabel("<html><b>Género:</b> " + genero + "</html>");
        lblGenero.setForeground(Color.GRAY);
        lblGenero.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(lblGenero);
        
        infoPanel.add(Box.createVerticalStrut(20));
        
        JTextArea txtSinopsis = new JTextArea(sinopsis);
        txtSinopsis.setLineWrap(true);
        txtSinopsis.setWrapStyleWord(true);
        txtSinopsis.setEditable(false);
        txtSinopsis.setOpaque(false);
        txtSinopsis.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSinopsis.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(txtSinopsis);

        contenidoPanel.add(infoPanel, BorderLayout.CENTER);

        // --- ABAJO: BOTONES ---
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(new Color(245, 245, 245));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnContinuar = new JButton("Continuar (Randomizar)");
        estilarBotonAzul(btnContinuar);
        buttonPane.add(btnContinuar);
        
        btnCancelar = new JButton("Cancelar");
        estilarBotonGris(btnCancelar);
        // El cancelar cierra la ventana nomás
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }
    
    // Método para cargar imagen de internet sin bloquear todo
    private void cargarImagenDesdeUrl(JLabel label, String urlString) {
        new Thread(() -> {
            try {
                URL url = new URI(urlString).toURL();
                BufferedImage image = ImageIO.read(url);
                if (image != null) {
                    Image scaled = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                    SwingUtilities.invokeLater(() -> {
                        label.setIcon(new ImageIcon(scaled));
                        label.setText("");
                    });
                }
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> label.setText("Error Imagen"));
            }
        }).start();
    }

    public void addContinuarListener(ActionListener l) {
        btnContinuar.addActionListener(l);
    }

    private void estilarBotonAzul(JButton btn) {
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void estilarBotonGris(JButton btn) {
        btn.setBackground(Color.LIGHT_GRAY);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}