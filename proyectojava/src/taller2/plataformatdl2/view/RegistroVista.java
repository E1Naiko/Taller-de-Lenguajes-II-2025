package taller2.plataformatdl2.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistroVista extends JFrame {
    
    // Componentes
    private JTextField campoNombre;
    private JTextField campoApellido;
    private JTextField campoDNI;
    private JTextField campoEmail; 
    private JPasswordField campoContrasena;
    private JPasswordField campoRepetirContrasena;
    private JButton botonRegistrarse;
    private JButton botonVolver; 

    public RegistroVista() {
        setTitle("Plataforma TDL2 - Registrar Usuario");
        setSize(500, 700); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para que no cierre el programa usamos este
        setLocationRelativeTo(null);
        
        // Panel Principal con fondo blanco
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre cositas

        // --- 0. LOGO ARRIBA ---
        JLabel lblLogo = new JLabel("", SwingConstants.CENTER);
        try {
            ImageIcon iconoLogo = new ImageIcon("proyectojava/img/Logotipo1.png");
            Image imagenEscalada = iconoLogo.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            lblLogo.setText("[LOGO]");
        }
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0); // Aire abajo del logo
        mainPanel.add(lblLogo, gbc);

        // --- 1. TÍTULO ---
        JLabel lblTitulo = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 26));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        mainPanel.add(lblTitulo, gbc);

        // --- CAMPOS (Reset de constraints) ---
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Helper para agregar filas rápido (Etiqueta + Campo)
        int fila = 2;
        
        // Nombre
        agregarCampo(mainPanel, "Nombre:", campoNombre = new JTextField(), gbc, fila++);
        // Apellido
        agregarCampo(mainPanel, "Apellido:", campoApellido = new JTextField(), gbc, fila++);
        // DNI
        agregarCampo(mainPanel, "DNI:", campoDNI = new JTextField(), gbc, fila++);
        // Email
        agregarCampo(mainPanel, "Email:", campoEmail = new JTextField(), gbc, fila++);
        // Contraseña
        agregarCampo(mainPanel, "Contraseña:", campoContrasena = new JPasswordField(), gbc, fila++);
        // Repetir Contraseña
        agregarCampo(mainPanel, "Repetir Pass:", campoRepetirContrasena = new JPasswordField(), gbc, fila++);

        // --- BOTONES ---
        gbc.gridx = 0; 
        gbc.gridy = fila; 
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(30, 10, 10, 10);
        
        botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setPreferredSize(new Dimension(200, 40));
        botonRegistrarse.setBackground(new Color(30, 144, 255)); 
        botonRegistrarse.setForeground(Color.WHITE);
        botonRegistrarse.setFont(new Font("Times New Roman", Font.BOLD, 14));
        botonRegistrarse.setFocusPainted(false);
        botonRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(botonRegistrarse, gbc);

        gbc.gridy = fila + 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        botonVolver = new JButton("Volver");
        botonVolver.setPreferredSize(new Dimension(200, 40));
        botonVolver.setBackground(new Color(30, 144, 255)); 
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setFont(new Font("Times New Roman", Font.BOLD, 14));
        botonVolver.setFocusPainted(false);
        botonVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(botonVolver, gbc);
        GridBagConstraints gbcPush = new GridBagConstraints();
        gbcPush.gridx = 0;
        gbcPush.gridy = fila + 2;
        gbcPush.weighty = 1.0; 
        gbcPush.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(new JLabel(), gbcPush);
    }

    // Método helper para no repetir código de GridBag
    private void agregarCampo(JPanel panel, String etiqueta, JComponent campo, GridBagConstraints gbc, int fila) {
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.gridy = fila;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.LINE_START;
        campo.setPreferredSize(new Dimension(200, 30));
        panel.add(campo, gbc);
    }

    // Getters
    public String getNombre() { return campoNombre.getText(); }
    public String getApellido() { return campoApellido.getText(); }
    public String getDNI() { return campoDNI.getText(); }
    public String getEmail() { return campoEmail.getText(); }
    public String getContrasena() { return new String(campoContrasena.getPassword()); }
    public String getRepetirContrasena() { return new String(campoRepetirContrasena.getPassword()); }

    // Listeners
    public void addRegistrarListener(ActionListener listener) { botonRegistrarse.addActionListener(listener); }
    public void addVolverListener(ActionListener listener) { botonVolver.addActionListener(listener); }
    
    public void mostrarMensaje(String msg) { JOptionPane.showMessageDialog(this, msg); }
    public void mostrarError(String msg) { JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE); }
}