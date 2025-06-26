package Vista;

import Controlador.SesionActiva;
import Modelo.DatosLogin;
import Controlador.Login;
import Modelo.Usuario;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginWindow extends JFrame {
    private final JTextField userField = new JTextField(15);
    private final JPasswordField passField = new JPasswordField(15);
    private final DatosLogin datos = new DatosLogin();
    private final Login login = new Login();
    private ImageIcon iconoNormal;
    private ImageIcon iconoHover;

    public LoginWindow() {
        setTitle("Login del Sistema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 720);
        setLocationRelativeTo(null); // Centrar
        setLayout(new GridBagLayout());

        iconoNormal = new ImageIcon("src/main/resources/icons/button_rectangle_depth_border.png");
        iconoHover = new ImageIcon("src/main/resources/icons/button_rectangle_depth_gloss.png");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(18, 18, 18, 18);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Campo Usuario + etiqueta a la derecha
        add(crearCampoConEtiqueta(userField, "Usuario"), gbc);

        // Campo Contraseña
        gbc.gridy++;
        add(crearCampoConEtiqueta(passField, "Contraseña"), gbc);

        // Botones personalizados
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(crearBotones(), gbc);

        setVisible(true);
    }

    private JPanel crearCampoConEtiqueta(JTextField field, String label) {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setOpaque(false);

        JLabel etiqueta = new JLabel(label);
        etiqueta.setHorizontalAlignment(SwingConstants.RIGHT);

        field.setPreferredSize(new Dimension(200, 35));
        panel.add(etiqueta);  // ahora primero el texto
        panel.add(field);     // luego el campo de texto

        return panel;
    }


    private JPanel crearBotones() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JButton btnLogin = crearBotonConHover("Iniciar sesión", iconoNormal, iconoHover);
        JButton btnSalir = crearBotonConHover("Cerrar programa", iconoNormal, iconoHover);

        btnLogin.addActionListener(e -> manejarLogin());
        btnSalir.addActionListener(e -> System.exit(0));

        userField.addActionListener(e -> passField.requestFocusInWindow());
        passField.addActionListener(e -> manejarLogin());

        panel.add(btnLogin);
        panel.add(Box.createHorizontalStrut(20)); // espacio entre botones
        panel.add(btnSalir);

        return panel;
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



    private void manejarLogin() {
        String nombre = userField.getText();
        String clave = new String(passField.getPassword());

        if (login.autenticar(nombre, clave, datos)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            dispose();
            SesionActiva sesion = new SesionActiva(new Usuario(nombre, clave));
            new SesionWindow(sesion);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
