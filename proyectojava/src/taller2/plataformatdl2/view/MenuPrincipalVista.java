package taller2.plataformatdl2.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import taller2.plataformatdl2.Model.ManejoDeContenido.Pelicula;

public class MenuPrincipalVista extends JFrame {
    private JPanel contentPane;
    private JPanel panelListaPeliculas; 
    private JButton btnCerrarSesion;
    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JLabel lblCargando;
    private JLabel lblUsuarioNombre; 
    
    // Botones para ordenar (headers de la tabla)
    private JButton btnOrderTitulo;
    private JButton btnOrderGenero;

    // COLORES TEMA (Todo clarito ahora)
    private final Color COLOR_FONDO = Color.WHITE;
    private final Color COLOR_TEXTO = new Color(50, 50, 50);
    private final Color COLOR_AZUL_PRINCIPAL = new Color(0, 102, 204); 
    @SuppressWarnings("unused")
    private final Color COLOR_HOVER_FILA = new Color(245, 245, 255); // Colorcito suave al pasar el mouse (opcional)
    private static final String PATH_LOGO = "proyectojava/img/Logotipo1.png";
    private static final String PATH_LOADING = "proyectojava/img/TL2 Perrito de carga.gif";

    public MenuPrincipalVista() {
        setTitle("TDL2 - Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768); // Pantalla ancha
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(COLOR_FONDO); 
        contentPane.setBorder(null);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // --- HEADER (ARRIBA) ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_FONDO);
        // Borde gris abajo para separar
        panelHeader.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        panelHeader.setPreferredSize(new Dimension(getWidth(), 70));
        contentPane.add(panelHeader, BorderLayout.NORTH);
        panelHeader.setLayout(new BorderLayout(20, 0));

        // 1. IZQUIERDA: LOGO + BIENVENIDA
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelLogo.setBackground(COLOR_FONDO);      
        JLabel lblLogo = new JLabel();
        cargarImagenEnLabel(lblLogo, PATH_LOGO, 170, 50);
        panelLogo.add(lblLogo);       
        JLabel lblBienvenida = new JLabel("Bienvenido a la plataforma");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblBienvenida.setForeground(COLOR_TEXTO);
        panelLogo.add(lblBienvenida);    
        panelHeader.add(panelLogo, BorderLayout.WEST);

        // 2. CENTRO: BUSCADOR
        JPanel panelBuscador = new JPanel();
        panelBuscador.setBackground(COLOR_FONDO);
        panelBuscador.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));     
        txtBusqueda = new JTextField();
        txtBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBusqueda.setToolTipText("Filtrar...");
        txtBusqueda.setPreferredSize(new Dimension(250, 35)); // Hice más corto el buscador
        txtBusqueda.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panelBuscador.add(txtBusqueda);
        btnBuscar = new JButton("Buscar");
        estilarBotonAzul(btnBuscar);
        panelBuscador.add(btnBuscar);     
        panelHeader.add(panelBuscador, BorderLayout.CENTER);

        // 3. DERECHA: USUARIO + CERRAR SESIÓN
        JPanel panelUser = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        panelUser.setBackground(COLOR_FONDO);      
        lblUsuarioNombre = new JLabel("Hola, Usuario");
        lblUsuarioNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUsuarioNombre.setForeground(COLOR_AZUL_PRINCIPAL);
        panelUser.add(lblUsuarioNombre);       
        btnCerrarSesion = new JButton("Cerrar Sesión");
        estilarBotonAzul(btnCerrarSesion);
        panelUser.add(btnCerrarSesion);    
        panelHeader.add(panelUser, BorderLayout.EAST);

        // --- CENTRO: TABLA DE PELÍCULAS ---
        // Usaremos un panel principal que contenga los Headers y el Scroll
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(COLOR_FONDO);
        
        // A. HEADERS (Encabezados de la tabla)
        JPanel panelHeaders = new JPanel();
        panelHeaders.setBackground(new Color(240, 240, 240));
        panelHeaders.setBorder(new MatteBorder(0, 0, 2, 0, COLOR_AZUL_PRINCIPAL));
        panelHeaders.setPreferredSize(new Dimension(getWidth(), 40));

        // Usamos GridBagLayout para controlar anchos de columnas
        panelHeaders.setLayout(new GridBagLayout());
        
        // Agregamos los titulos de las columnas
        agregarHeaderColumna(panelHeaders, "Poster", 0, 0.1);
        
        // Título con botón de orden
        btnOrderTitulo = new JButton("Título ⬇");
        estilarBotonHeader(btnOrderTitulo);
        agregarComponenteHeader(panelHeaders, btnOrderTitulo, 1, 0.2);
        
        // Género con botón de orden
        btnOrderGenero = new JButton("Género ⬇");
        estilarBotonHeader(btnOrderGenero);
        agregarComponenteHeader(panelHeaders, btnOrderGenero, 2, 0.15);      
        agregarHeaderColumna(panelHeaders, "Resumen", 3, 0.4);
        agregarHeaderColumna(panelHeaders, "Acciones", 4, 0.15);
        panelCentro.add(panelHeaders, BorderLayout.NORTH);

        // B. LISTA (SCROLL)
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getViewport().setBackground(COLOR_FONDO);    
        panelListaPeliculas = new JPanel();
        panelListaPeliculas.setBackground(COLOR_FONDO);
        // BoxLayout vertical para que sea una lista una abajo de la otra
        panelListaPeliculas.setLayout(new BoxLayout(panelListaPeliculas, BoxLayout.Y_AXIS));      
        scrollPane.setViewportView(panelListaPeliculas);
        panelCentro.add(scrollPane, BorderLayout.CENTER);      
        contentPane.add(panelCentro, BorderLayout.CENTER);

        // --- BOTTOM (ABAJO): LOADING ---
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBottom.setBackground(COLOR_FONDO);      
        lblCargando = new JLabel("Cargando catálogo...");
        lblCargando.setForeground(Color.GRAY);
        cargarImagenEnLabel(lblCargando, PATH_LOADING, 40, 40);
        lblCargando.setVisible(false);      
        panelBottom.add(lblCargando);
        contentPane.add(panelBottom, BorderLayout.SOUTH);
    }

    // --- MÉTODOS PÚBLICOS ---
    public void setNombreUsuario(String nombre) {
        lblUsuarioNombre.setText("Hola, " + nombre);
    }

    public void setCargando(boolean cargando) {
        lblCargando.setVisible(cargando);
    }

    public void cargarPeliculas(List<Pelicula> peliculas, ActionListener listener) {
        panelListaPeliculas.removeAll();
        if (peliculas == null || peliculas.isEmpty()) {
            JLabel lblVacio = new JLabel("No se encontraron resultados.");
            lblVacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblVacio.setBorder(new EmptyBorder(20,0,0,0));
            panelListaPeliculas.add(lblVacio);
        } else {
            for (Pelicula p : peliculas) {
                if (p != null) {
                    panelListaPeliculas.add(crearFilaPelicula(p, listener));
                    // Separador
                    JSeparator sep = new JSeparator();
                    sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
                    sep.setForeground(Color.LIGHT_GRAY);
                    panelListaPeliculas.add(sep);
                }
            }
        }    
        panelListaPeliculas.revalidate();
        panelListaPeliculas.repaint();
    }

    // Crea una FILA de la tabla para una película
    private JPanel crearFilaPelicula(Pelicula p, ActionListener listener) {
        JPanel fila = new JPanel();
        fila.setLayout(new GridBagLayout());
        fila.setBackground(Color.WHITE);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); // Altura fija por fila
        fila.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 5, 0, 5); // Margen entre columnas

        // 1. POSTER (Imagen chica)
        JLabel lblImg = new JLabel();
        lblImg.setPreferredSize(new Dimension(60, 90));
        lblImg.setHorizontalAlignment(SwingConstants.CENTER);
        lblImg.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblImg.setText("...");
        // aca trae el URL de la imagen de los poster desde la APi
        String urlImagen = p.getDireccionArchivo();
        if (urlImagen != null && urlImagen.startsWith("http")) {
            cargarPosterDesdeUrl(lblImg, urlImagen);
        } else {
            lblImg.setText ("Sin imagen");
        }      
        gbc.gridx = 0; 
        gbc.weightx = 0.1;
        fila.add(lblImg, gbc);

        // Datos seguros
        String titulo = "Desconocido";
        String genero = "Varios";
        String resumen = "Sin descripción disponible.";     
        if (p.getMetadatos() != null) {
            titulo = (p.getMetadatos().getTitulo() != null) ? p.getMetadatos().getTitulo() : titulo;
            if(p.getGenero() != null) genero = p.getGenero().toString();
            if(p.getMetadatos().getSinopsis() != null) resumen = p.getMetadatos().getSinopsis();
        }
        // 2. TÍTULO
        JLabel lblTitulo = new JLabel("<html><b>" + titulo + "</b></html>");
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.weightx = 0.2;
        fila.add(lblTitulo, gbc);
        // 3. GÉNERO
        JLabel lblGenero = new JLabel(genero);
        gbc.gridx = 2;
        gbc.weightx = 0.15;
        fila.add(lblGenero, gbc);
        // 4. RESUMEN (Texto multilínea)
        if (resumen.length() > 100) resumen = resumen.substring(0, 100) + "...";
        JTextArea txtResumen = new JTextArea(resumen);
        txtResumen.setWrapStyleWord(true);
        txtResumen.setLineWrap(true);
        txtResumen.setOpaque(false);
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtResumen.setForeground(Color.DARK_GRAY);      
        gbc.gridx = 3;
        gbc.weightx = 0.4;
        fila.add(txtResumen, gbc);

        // 5. ACCIONES (Botón Calificar)
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAcciones.setOpaque(false);    
        JButton btnCalificar = new JButton("Calificar");
        estilarBotonAzul(btnCalificar);
        btnCalificar.putClientProperty("PELICULA_DATA", p);
        btnCalificar.setActionCommand("CALIFICAR"); // Cambié el comando como pediste
        btnCalificar.addActionListener(listener);       
        panelAcciones.add(btnCalificar); 
        gbc.gridx = 4;
        gbc.weightx = 0.15;
        fila.add(panelAcciones, gbc);
        return fila;
    }

    // --- MÉTODO NUEVO PARA CARGAR POSTERS SIN TRABAR TODO ---
    private void cargarPosterDesdeUrl(JLabel label, String urlString) {
        // Lanzamos un mini hilo solo para esta imagen
        new Thread(() -> {
            try {
                URL url = new URI(urlString).toURL();
                BufferedImage image = ImageIO.read(url);
                
                if (image != null) {
                    // Redimensionamos al tamaño del label (65x95)
                    Image scaled = image.getScaledInstance(65, 95, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(scaled);
                    
                    // Actualizamos la UI en el hilo correcto
                    SwingUtilities.invokeLater(() -> {
                        label.setText("");
                        label.setIcon(icon);
                    });
                }
            } catch (Exception e) {
                // Si falla (ej: sin internet o url rota), dejamos texto
                SwingUtilities.invokeLater(() -> label.setText("Error"));
            }
        }).start();
    }

    // --- UTILS LAYOUT ---
    
    private void agregarHeaderColumna(JPanel panel, String texto, int gridx, double weightx) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        agregarComponenteHeader(panel, lbl, gridx, weightx);
    }
    
    private void agregarComponenteHeader(JPanel panel, Component comp, int gridx, double weightx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = 0;
        gbc.weightx = weightx;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(comp, gbc);
    }

    private void estilarBotonAzul(JButton btn) {
        btn.setBackground(COLOR_AZUL_PRINCIPAL);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void estilarBotonHeader(JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Icono o texto para indicar que se puede clickear
    }

    private void cargarImagenEnLabel(JLabel label, String path, int w, int h) {
        try {
            File imgFile = new File(path);
            if (imgFile.exists()) {
                ImageIcon icon = new ImageIcon(path);
                Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                if (path.endsWith(".gif")) {
                     img = icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                }
                label.setIcon(new ImageIcon(img));
                label.setText("");
            }
        } catch (Exception e) {}
    }

    public void addListeners(ActionListener listener) {
        btnCerrarSesion.setActionCommand("LOGOUT");
        btnCerrarSesion.addActionListener(listener);
        btnBuscar.setActionCommand("BUSCAR");
        btnBuscar.addActionListener(listener);
        
        // Listeners para ordenar
        btnOrderTitulo.setActionCommand("ORDENAR_TITULO");
        btnOrderTitulo.addActionListener(listener);
        
        btnOrderGenero.setActionCommand("ORDENAR_GENERO");
        btnOrderGenero.addActionListener(listener);
    }
    
    public String getTextoBusqueda() {
        return txtBusqueda.getText();
    }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}