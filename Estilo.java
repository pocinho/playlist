/**
 * @author Paulo Pocinho
 * @since 28-03-2017
 */

public enum Estilo {
    AFRICANO, ASIATICO, BLUES, CARAIBAS, COUNTRY, ELECTRONICO, EXPERIMENTAL,
    FOLK, HIPHOP, JAZZ, LATINO, POP, RANDB, SOUL, ROCK, OUTRO;

    public static Estilo parseEstilo(final String frase) {
        Estilo estilo = null;
        for (Estilo e : Estilo.values()) {
            if (frase.equalsIgnoreCase(e.name())) {
                estilo = e;
                break;
            }
        }
        return estilo;
    }

    public static String capitalizarEstilo(final Estilo estilo) {
        return Character.toUpperCase(estilo.name().charAt(0)) + estilo.name().substring(1).toLowerCase();
    }
}
