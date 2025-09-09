import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private Jugador jugador1, jugador2;
    JTabbedPane tpJugadores;

    public FrmJuego() {

        setSize(600, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(2, 115, 94));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(1, 64, 52));
        pnlJugador2.setLayout(null);

        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Player1", pnlJugador1); // Estos nombres son un chiste sacado de una
                                                    // canción de Vicente Fernandez
        tpJugadores.addTab("Player2", pnlJugador2);
        tpJugadores.setBounds(10, 40, 550, 200);

        getContentPane().add(tpJugadores);

        btnRepartir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repartir();
            }

        });

        btnVerificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                verificar();
            }

        });

        // crear las instancias
        jugador1 = new Jugador();
        jugador2 = new Jugador();

    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar() {
        Jugador jugadorActivo = (tpJugadores.getSelectedIndex() == 0) ? jugador1 : jugador2;

        // obtenemos grupos
        String grupos = jugadorActivo.getGrupos();

        // calculamos puntaje
        int puntaje = calculadorPuntaje.CalcularPuntaje(jugadorActivo);

        // mostramos todo en un solo mensaje
        JOptionPane.showMessageDialog(null,
                grupos + "\n\nPuntaje (cartas sin grupos): " + puntaje);
    }

}
