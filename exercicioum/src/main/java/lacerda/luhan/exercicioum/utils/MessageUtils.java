package lacerda.luhan.exercicioum.utils;

import org.springframework.stereotype.Component;

/***
 * Classe contendo utilitários para geração da mensagem
 *
 * @author luhan.melo.t.lacerda
 */

@Component
public class MessageUtils {

    public static final int QUANTIDADE_MINIMA_DE_NOTAS = 2;

    /***
     *
     * @param stringBuilder
     * @return
     */
    public boolean isStringBuilderGreaterThanZero(StringBuilder stringBuilder) {
        return stringBuilder.toString().length() > 0;
    }

    /***
     *
     * @param total
     * @param valueToSum
     * @return
     */
    public Double sumTotalValueFromComposicao(Double total, String valueToSum) {
        total += Double.valueOf(valueToSum);
        return total;
    }

    public String defineMessagePrefix(int messageSize) {
        return messageSize >= QUANTIDADE_MINIMA_DE_NOTAS ? ConstantUtils.MAIS_DE_UMA_NOTA : ConstantUtils.UMA_NOTA;
    }

    public boolean isEmptyOrNullStringBuild(StringBuilder stringBuilder) {
        return stringBuilder.toString() == null || stringBuilder.toString().length() <= 0;
    }

}
