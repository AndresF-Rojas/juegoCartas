public class calculadorPuntaje {

    public static int CalcularPuntaje(Jugador jugador) {
        int puntaje = 0;

        for (Carta c : jugador.getCartas()) {
            // si la carta NO pertenece a un grupo
            if (!c.esParteDeGrupo()) {
                puntaje += obtenerValor(c);
            }
        }

        return puntaje;
    }

    private static int obtenerValor(Carta carta) {
        switch (carta.getNombre()) {
            case AS:
            case JACK:
            case QUEEN:
            case KING:
                return 10;

            case DOS:
                return 2;
            case TRES:
                return 3;
            case CUATRO:
                return 4;
            case CINCO:
                return 5;
            case SEIS:
                return 6;
            case SIETE:
                return 7;
            case OCHO:
                return 8;
            case NUEVE:
                return 9;
            case DIEZ:
                return 10;

            default:
                return 0; // por seguridad
        }
    }

}
