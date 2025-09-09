import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private Jugador jugador1, jugador2;

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

        JTabbedPane tplJugadores = new JTabbedPane();
        tplJugadores.addTab("Martín Estrada Contreras", pnlJugador1); // Estos nombres son un chiste sacado de una
                                                                      // canción de Vicente Fernandez
        tplJugadores.addTab("Raúl Vidal", pnlJugador2);
        tplJugadores.setBounds(10, 40, 550, 200);

        getContentPane().add(tplJugadores);

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
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

}
