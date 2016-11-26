package bigram;

/**
 * Remueve caracteres innecesarios
 */
public class TextCleaner {

    // Acentos que se deben remover
    private static final String ACCENTS = "ÁáÉéÍíÓóÚúÑñÜü";
    // Reemplazo para los acentos que se encuentren
    private static final String ACCENTS_REPLACEMENTS = "AaEeIiOoUuNnUu";
        
    /**
     * Limpia el texto proporcionado
     * @return texto sin caracteretes innecesarios
     */
    public static String cleanText(String text){
        text = removeAccents(text);
        text = downcaseText(text);
        text = removeUndesiredCharacters(text);
        text = separatePeriodsAndCommas(text);
        return text;
    }

    /**
     * Recorre la lista de acentos y en caso de que uno de ellos aparezca en 
     * el texto este será reemplazado con su versión normal
     */
    private static String removeAccents(String text) {
        for (int i=0; i<ACCENTS.length(); i++) {
            text = text.replace(ACCENTS.charAt(i), ACCENTS_REPLACEMENTS.charAt(i));
        }
        return text;
    }

    /**
     * Convierte las palabras del texto en minúsculas.
     */
    private static String downcaseText(String text) {
        return text.toLowerCase();
    }

    /**
     * Remueve todos aquellos caracteres que no sean necesarios excepto puntos,
     * comas, letras y números.
     */
    private static String removeUndesiredCharacters(String text) {
        return text.replaceAll("([^a-z0-9,\\. ])+", "");
    }

    private static String separatePeriodsAndCommas(String text){
        return text.replaceAll("(\\.|,)", " $1")
                .replaceAll("(\\.)([a-z])", "$1 $2");
    }
}