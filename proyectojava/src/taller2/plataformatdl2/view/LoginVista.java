package taller2.plataformatdl2.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginVista extends JFrame {
    // Variables de la vista
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonIngresar;
    private JButton botonRegistrar;

    public LoginVista() {
        // Configuramos la ventana
        setTitle("Plataforma TDL2 - Bienvenido");
        setSize(900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        // Layout principal: 2 Columnas (Imagen | Formulario)
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        add(mainPanel);

        // La PARTE IZQUIERDA: EL GIF  
        // ---------------------------------------------------------
        JPanel panelIzquierdo = new JPanel();
        // Usamos GridBagLayout para que CENTRE el GIF automáticamente 
        panelIzquierdo.setLayout(new GridBagLayout()); 
        // Color de fondo: Este color rellena todo el espacio que sobra alrededor del GIF para que no quede blanco
        panelIzquierdo.setBackground(new Color(250, 215, 160)); 
        JLabel lblImagen = new JLabel();
        try {
            ImageIcon gifAnimado = new ImageIcon("proyectojava/img/Pantalla de Carga Perrito.gif");
            lblImagen.setIcon(gifAnimado);
        } catch (Exception e) {
            // Tiramos un mensajito si no encuentra el gif
            lblImagen.setText("<html><div style='text-align: center;'>[Falta el GIF]<br>Verificá la carpeta img</div></html>");
            lblImagen.setForeground(new Color(100, 50, 0));
        }  
        panelIzquierdo.add(lblImagen); // Se agrega centrado gracias al GridBagLayout
        mainPanel.add(panelIzquierdo);

        // LA PARTE DERECHA: Formulario con Logo 
        // ---------------------------------------------------------
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(Color.WHITE);
        panelDerecho.setLayout(new GridBagLayout()); // Centra el bloque del formulario
        // Panel interno del formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(10, 40, 10, 40)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- 0. EL LOGOTIPO ---
        JLabel lblLogo = new JLabel("", SwingConstants.CENTER);
        try {
            // Aca ponemos el link de la imagen
            ImageIcon iconoLogo = new ImageIcon("proyectojava/img/Logotipo1.png");
            // Ajustamos el logo para que se vea mejor
            Image imagenLogoEscalada = iconoLogo.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(imagenLogoEscalada));
        } catch (Exception e) {
            // Tiramos mensajito si no encuentra la imagen
            lblLogo.setText("[LOGO]");
        }
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; // Para que Ocupe todo el ancho
        gbc.insets = new Insets(0, 0, 15, 0); // Ponemos un espacio debajo de la imagen
        formPanel.add(lblLogo, gbc);

        // --- 1. Título ---
        JLabel lblTitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 28)); // Ajustamos las letras  
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 25, 0); 
        formPanel.add(lblTitulo, gbc);

        // --- Configuración de campos ---
        gbc.gridwidth = 1; 
        gbc.insets = new Insets(5, 5, 5, 5); 

        // --- 2. Campo del Email ---
        // Letra
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0.3; 
        gbc.anchor = GridBagConstraints.LINE_END; 
        JLabel lblUser = new JLabel("E-mail:");
        lblUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
        formPanel.add(lblUser, gbc);

        // Campo de relleno para el email
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 0.7; 
        gbc.anchor = GridBagConstraints.LINE_START; 
        campoUsuario = new JTextField(15);
        campoUsuario.setPreferredSize(new Dimension(220, 30)); 
        formPanel.add(campoUsuario, gbc);

        // --- 3. Campo de la Contraseña ---
        // Letra
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel lblPass = new JLabel("Contraseña:");
        lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        formPanel.add(lblPass, gbc);

        // Campo de relleno para la contraseña
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.LINE_START;
        campoContrasena = new JPasswordField(15);
        campoContrasena.setPreferredSize(new Dimension(220, 30));
        formPanel.add(campoContrasena, gbc);

        // --- 4. Botón Ingresar ---
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; 
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(25, 10, 10, 10); 
        
        // Letra y la forma del boton
        botonIngresar = new JButton("INGRESAR");
        botonIngresar.setPreferredSize(new Dimension(220, 40));
        botonIngresar.setBackground(new Color(30, 144, 255)); 
        botonIngresar.setForeground(Color.WHITE);
        botonIngresar.setFont(new Font("Times New Roman", Font.BOLD, 18));
        botonIngresar.setFocusPainted(false);
        botonIngresar.setBorderPainted(false);
        botonIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        formPanel.add(botonIngresar, gbc);

        // --- 5. Link Registro ---
        JPanel panelRegistro = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panelRegistro.setBackground(Color.WHITE);   
        JLabel lblNoCuenta = new JLabel("¿No tenés cuenta?");
        lblNoCuenta.setFont(new Font("Times New Roman", Font.PLAIN, 14));  
        botonRegistrar = new JButton("<html><u>Registrate acá</u></html>");
        botonRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        botonRegistrar.setForeground(Color.BLUE);
        botonRegistrar.setBorderPainted(false);
        botonRegistrar.setContentAreaFilled(false);
        botonRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelRegistro.add(lblNoCuenta);
        panelRegistro.add(botonRegistrar);
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.insets = new Insets(5, 10, 10, 10);
        formPanel.add(panelRegistro, gbc);
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0; 
        gbcMain.gridy = 0; 
        gbcMain.insets = new Insets(0, 0, 200, 0); 
        panelDerecho.add(formPanel, gbcMain);
        mainPanel.add(panelDerecho);
    }

    // --- Getters y Listeners ---
    public String getUsuario() { 
        return campoUsuario.getText(); 
    }

    public String getContrasena() { 
        return new String(campoContrasena.getPassword());
    }

    public void addLoginListener(ActionListener listener) { 
        botonIngresar.addActionListener(listener); 
    }

    public void addRegistroListener(ActionListener listener) { 
        botonRegistrar.addActionListener(listener); 
    }

    public void mostrarError(String mensaje) { 
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE); 
    }
}