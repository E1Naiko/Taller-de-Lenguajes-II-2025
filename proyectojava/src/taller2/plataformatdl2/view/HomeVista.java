package taller2.plataformatdl2.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeVista extends JFrame {
    // Componentes principales
    private JPanel panelContenido; // Acá van a vivir las "Cards" (Carga vs Pelis)
    private CardLayout cardLayout;
    // Barra Superior
    private JTextField campoBusqueda;
    private JButton botonBuscar;
    private JButton botonCerrarSesion;
    private JLabel lblUsuario;
    // Panel de Películas
    private JPanel panelPeliculas;
 
    public HomeVista() {
        setTitle("Plataforma TDL2 - Home");
        setSize(1024, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        // La parte de la barra superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(20,20,20));
        panelSuperior.setBorder(new EmptyBorder(10,20,10,20));
        panelSuperior.setPreferredSize(new Dimension(getWidth(),60));
        // La parte izquierda
        JLabel lblLogo = new JLabel("No imagen");
        lblLogo.setForeground(Color.RED);
        lblLogo.setFont(new Font("Times New Roman",Font.BOLD,14));
        try {
            // Para el logo de la app que es el mismo del login
            ImageIcon iconoLogo = new ImageIcon("proyectojava/img/Logotipo1.png");
            Image imgEscalada = iconoLogo.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(imgEscalada));
            lblLogo.setText(""); // Borramos texto
        } catch (Exception e) { }
        panelSuperior.add(lblLogo, BorderLayout.WEST);
        }
        // La parte del Centro (El Buscador)
        JPanel panelBuscador = new JPanel (new FlowLayout(FlowLayout.CENTER));
        panelBuscador.setOpaque(false);
        campoBusqueda = new JTextField(25);
        campoBusqueda.setPreferredSize(new Dimension(200,30));
        campoBusqueda.setToolTipText("Buscar Pelicula...");
        // Configuracion del Boton Buscar
        botonBuscar = new JButton("🔎");
        botonBuscar.setPreferredSize(new Dimension(50,50));
        botonBuscar.setBackground(Color.DARK_GRAY);
        botonBuscar.setForeground(Color.WHITE);
        botonBuscar.setFocusPainted(false);

        panelBuscador.add(botonBuscar);
        panelBuscador.add(campoBusqueda);
        panelSuperior.add(panelBuscador,BorderLayout.CENTER);
        // La parte Derecha boton de cerrar sesion
        JPanel panelUsuario = new JPanel(new FlowLayout());  
    }  
}
