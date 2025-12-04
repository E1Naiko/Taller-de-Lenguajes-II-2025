package taller2.plataformatdl2.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalificarPeliculaVista extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JComboBox<String> cmbPuntaje;
    private JTextArea txtResenia;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public CalificarPeliculaVista(Frame parent, String tituloPelicula) {
        super(parent, true); // Modal true para bloquear la de atrás
        setTitle("Calificar: " + tituloPelicula);
        setBounds(100, 100, 450, 350);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Título
        JLabel lblTitulo = new JLabel("Deja tu opinión sobre:");
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentPanel.add(lblTitulo);
        
        JLabel lblPeli = new JLabel(tituloPelicula);
        lblPeli.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblPeli.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPeli.setForeground(new Color(0, 102, 204));
        contentPanel.add(lblPeli);
        
        contentPanel.add(Box.createVerticalStrut(20));

        // Puntaje
        JLabel lblPuntaje = new JLabel("Puntaje (1 al 5):");
        lblPuntaje.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblPuntaje);
        
        String[] puntajes = {"1 - Malísima", "2 - Mala", "3 - Regular", "4 - Buena", "5 - Excelente"};
        cmbPuntaje = new JComboBox<>(puntajes);
        cmbPuntaje.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        cmbPuntaje.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbPuntaje.setBackground(Color.WHITE);
        contentPanel.add(cmbPuntaje);

        contentPanel.add(Box.createVerticalStrut(15));

        // Reseña
        JLabel lblResenia = new JLabel("Tu comentario:");
        lblResenia.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(lblResenia);
        
        txtResenia = new JTextArea(5, 20);
        txtResenia.setLineWrap(true);
        txtResenia.setWrapStyleWord(true);
        txtResenia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtResenia.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JScrollPane scroll = new JScrollPane(txtResenia);
        scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(scroll);

        // Botones
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(new Color(245, 245, 245));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnConfirmar = new JButton("Enviar Calificación");
        estilarBotonAzul(btnConfirmar);
        buttonPane.add(btnConfirmar);
        
        btnCancelar = new JButton("Cancelar");
        estilarBotonGris(btnCancelar);
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }

    public void addConfirmarListener(ActionListener l) {
        btnConfirmar.addActionListener(l);
    }
    
    // Devuelve el número entero del puntaje (1 a 5)
    public int getPuntajeSeleccionado() {
        return cmbPuntaje.getSelectedIndex() + 1;
    }
    
    public String getTextoResenia() {
        return txtResenia.getText();
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