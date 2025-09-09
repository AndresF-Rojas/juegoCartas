import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import imagenes.NombreCarta;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int SEPARACION = 40;
    private final int MARGEN = 10;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN;
        JLabel[] lblCartas = new JLabel[TOTAL_CARTAS];
        int z = 0;

        for (Carta carta : cartas) {
            lblCartas[z] = carta.mostrar(pnl, posicion, MARGEN);
            posicion += SEPARACION;
            z++;
        }

        z = lblCartas.length - 1;
        for (JLabel lbl : lblCartas) {
            pnl.setComponentZOrder(lbl, z);
            z--;

        }

        pnl.repaint();
    }

    public String getGrupos() {
        String resultado = "No se encontraron grupos";

        // Reiniciar la marca de todas las cartas antes de calcular
        for (Carta carta : cartas) {
            carta.setParteDeGrupo(false);
        }

        // Calcular los contadores por nombre de carta
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        // verficar si hubo grupos
        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador >= 2) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            resultado = "Se hallaron los siguientes grupos:\n";
            for (int p = 0; p < contadores.length; p++) {
                int contador = contadores[p];
                if (contador >= 2) {
                    for (Carta carta : cartas) {
                        if (carta.getNombre() == NombreCarta.values()[p]) {
                            carta.setParteDeGrupo(true);
                        }
                    }
                    

                //  Evitar desbordes en el enum Grupo
                int idx = Math.min(contador, Grupo.values().length - 1);
                resultado += Grupo.values()[idx] + " de " + NombreCarta.values()[p] + "\n";
                }
                
            }
        }

        // Escaleras por pinta
        for (Pinta pinta : Pinta.values()) {
            // Guardar los valores de esta pinta
            int[] valores = new int[TOTAL_CARTAS];
            int cuenta = 0;
            for (Carta carta : cartas) {
                if (carta.getPinta() == pinta) {
                    valores[cuenta] = carta.getNombre().ordinal() + 1; // AS=1 ... KING=13
                    cuenta++;
                }
            }

            if (cuenta < 2)
                continue; // imposible escalera

            // Ordenar con burbuja
            for (int i = 0; i < cuenta - 1; i++) {
                for (int j = 0; j < cuenta - 1 - i; j++) {
                    if (valores[j] > valores[j + 1]) {
                        int temp = valores[j];
                        valores[j] = valores[j + 1];
                        valores[j + 1] = temp;
                    }
                }
            }

            // Buscar secuencias consecutivas
            int inicio = 0;
            for (int i = 1; i <= cuenta; i++) {
                if (i == cuenta || valores[i] != valores[i - 1] + 1) {
                    int longitud = i - inicio;
                    if (longitud >= 2) {
                        // 🔹 Marcar cartas que forman parte de la escalera
                        for (int k = inicio; k <= i - 1; k++) {
                            for (Carta carta : cartas) {
                                if (carta.getPinta() == pinta &&
                                    carta.getNombre().ordinal() + 1 == valores[k]) {
                                    carta.setParteDeGrupo(true);
                                    
                                }
                            
                            }
                        }
                        
                        resultado += "Escalera: " + traducirSecuencia(valores, inicio, i - 1)+ " de " + pinta + "\n";
                    }
                    inicio= i;
                }
            }
        }

        if (resultado.equals("")) {
            resultado = "No se encontraron grupos";
        }

        return resultado;
    }   

    // Método auxiliar usando arrays simples
    private String traducirSecuencia(int[] valores, int inicio, int fin) {
        String texto = "";
        for (int i = inicio; i <= fin; i++) {
            if (i > inicio)
                texto += ", ";
            texto += NombreCarta.values()[valores[i] - 1];
        }
        return texto;
    }

    public int obtenerPuntaje() {
        return calculadorPuntaje.CalcularPuntaje(this);
    }

    public Carta[] getCartas() {
        return cartas;
    }
}
