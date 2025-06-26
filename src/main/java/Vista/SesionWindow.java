package Vista;

import Controlador.SesionActiva;
import Modelo.Tarea;

import javax.swing.*;
import java.awt.*;

public class SesionWindow extends JFrame {

    private final SesionActiva sesionActiva;

    public SesionWindow(SesionActiva sesionActiva) {
        this.sesionActiva = sesionActiva;

        setTitle("Sesión de " + sesionActiva.getUsuario().getNombre());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 720);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;

        // Botones visuales
        int anchoBoton = 500;
        int altoBoton = 80;
        ImageIcon iconoNormal = escalarIcono(
                new ImageIcon("src/main/resources/icons/button_rectangle_depth_border.png"), anchoBoton, altoBoton);
        ImageIcon iconoHover = escalarIcono(
                new ImageIcon("src/main/resources/icons/button_rectangle_depth_gloss.png"), anchoBoton, altoBoton);
        JButton btnVerTareas = crearBotonConHover("1. Ver tareas", iconoNormal, iconoHover);
        JButton btnEscribirTarea = crearBotonConHover("2. Escribir nueva tarea", iconoNormal, iconoHover);
        JButton btnRegistrar = crearBotonConHover("3. Registrar nuevo usuario", iconoNormal, iconoHover);
        JButton btnSalir = crearBotonConHover("4. Cerrar sesión", iconoNormal, iconoHover);

        btnVerTareas.addActionListener(_ -> verTareas());
        btnEscribirTarea.addActionListener(_ -> escribirTarea());
        btnSalir.addActionListener(_ -> {
            JOptionPane.showMessageDialog(this, "Sesión cerrada.");
            dispose();
            new LoginWindow();
        });

        add(btnVerTareas,gbc);
        add(btnEscribirTarea,gbc);

        if (sesionActiva.esAdmin()) {
            btnRegistrar.addActionListener(_ -> registrarUsuario());
            add(btnRegistrar,gbc);
        }

        add(btnSalir,gbc);

        setVisible(true);
    }

    private void verTareas() {
        java.util.List<Tarea> lista = sesionActiva.getTareas();

        String[] columnas = {"Nombre", "Prioridad", "Completada"};
        Object[][] datos = new Object[lista.size()][3];

        for (int i = 0; i < lista.size(); i++) {
            Tarea t = lista.get(i);
            datos[i][0] = t.getDescripcion();
            datos[i][1] = t.getPriority();
            datos[i][2] = t.isComplete() ? "✔" : "✘";
        }

        JTable tabla = new JTable(datos, columnas);
        tabla.setEnabled(false);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(tabla);

        JOptionPane.showMessageDialog(this, scroll, "Tareas del Usuario", JOptionPane.INFORMATION_MESSAGE);
    }


    private void escribirTarea() {
        JTextField campoDescripcion = new JTextField();
        String[] prioridades = {"ALTO", "MEDIO", "BAJO"};
        JComboBox<String> comboPrioridad = new JComboBox<>(prioridades);
        JCheckBox checkCompletada = new JCheckBox("¿Completada?");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Descripción de la tarea:"));
        panel.add(campoDescripcion);
        panel.add(new JLabel("Prioridad:"));
        panel.add(comboPrioridad);
        panel.add(checkCompletada);

        int result = JOptionPane.showConfirmDialog(this, panel, "Nueva Tarea", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String desc = campoDescripcion.getText().trim();
            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descripción vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Tarea.Prioridad prio = Tarea.Prioridad.valueOf(comboPrioridad.getSelectedItem().toString());
            boolean completada = checkCompletada.isSelected();
            sesionActiva.agregarTarea(desc, prio, completada);
            JOptionPane.showMessageDialog(this, "Tarea agregada con éxito.");
        }
    }

    private void registrarUsuario() {
        JTextField campoUsuario = new JTextField();
        JTextField campoClave = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nuevo nombre de usuario:"));
        panel.add(campoUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoClave);

        int result = JOptionPane.showConfirmDialog(this, panel, "Registrar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String usuario = campoUsuario.getText().trim();
            String clave = campoClave.getText().trim();
            boolean exito = sesionActiva.registrarNuevoUsuario(usuario, clave);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Usuario ya registrado o no valido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JButton crearBotonConHover(String texto, ImageIcon iconoNormal, ImageIcon iconoHover) {
        JButton boton = new JButton(texto);

        if (iconoNormal != null) {
            boton.setIcon(iconoNormal);
        }

        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.CENTER);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setForeground(Color.BLACK);


        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (iconoHover != null) boton.setIcon(iconoHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (iconoNormal != null) boton.setIcon(iconoNormal);
            }
        });

        return boton;
    }

    private ImageIcon escalarIcono(ImageIcon original, int ancho, int alto) {
        Image img = original.getImage();
        Image nuevaImg = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(nuevaImg);
    }

}
