package lacerda.luhan.exercicioum.utils;

/***
 * Classe contendo as constantes utilizadas no projeto
 *
 * @author luhan.melo.t.lacerda
 */
public class ConstantUtils {

    private ConstantUtils() {
        throw new IllegalStateException("Utility class");
    }

    //Textos pre-definidos
    public static final String UMA_NOTA = "Fatura da nota fiscal de simples remessa: ";
    public static final String STRING_CUJO_VALOR = " cujo valor é R$ ";
    public static final String MAIS_DE_UMA_NOTA = "Fatura das notas fiscais de simples remessa: ";
    public static final String TOTAL_STRING = " Total = ";

    // Constantes utilizadas na montagem do texto da mensagem de retorno
    public static final String PERIOD_STRING = ".";
    public static final String COMMA_STRING = ",";
    public static final String SPACE_STRING = " ";
    public static final String EMPTY_STRING = "";
    public static final String DECIMAL_POINT = "%.2f";
}
