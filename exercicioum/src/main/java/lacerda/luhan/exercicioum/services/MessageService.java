package lacerda.luhan.exercicioum.services;

import lacerda.luhan.exercicioum.utils.ConstantUtils;
import lacerda.luhan.exercicioum.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/***
 * Classe usada para construção da mensagem de retorno
 *
 * @author luhan.melo.t.lacerda
 */
@Component
public class MessageService {

    @Autowired
    MessageUtils messageUtils;

    /***
     * Método para montar mensagem de retorno
     *
     * @param list
     * @param stringBuilder
     */
    public void buildReturnMessage(List<Integer> list, StringBuilder stringBuilder) {
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer code = iterator.next();
            String text;
            if (messageUtils.isEmptyOrNullStringBuild(stringBuilder))
                text = ConstantUtils.EMPTY_STRING;
            else if (iterator.hasNext())
                text = ConstantUtils.COMMA_STRING + ConstantUtils.SPACE_STRING;
            else
                text = " e ";

            stringBuilder.append(text + code);
        }
    }

    /***
     * Método para montar mensagem de retorno
     *
     * @param iterator
     * @param stringBuilder
     */
    public void buildReturnMessage(Iterator<Map.Entry<Integer, Double>> iterator, StringBuilder stringBuilder) {
        Double total = 0.0;

        while (iterator.hasNext()) {
            Map.Entry<Integer, Double> entry = iterator.next();
            String text = "";
            String valueInString = String.valueOf(entry.getValue());

            if (iterator.hasNext()) {
                if (messageUtils.isStringBuilderGreaterThanZero(stringBuilder)) {
                    text = ConstantUtils.SPACE_STRING;
                }
                text += createReturnMessage(entry, Double.valueOf(valueInString));
                total = messageUtils.sumTotalValueFromComposicao(total, valueInString);
            } else {
                total = messageUtils.sumTotalValueFromComposicao(total, valueInString);
                if (messageUtils.isStringBuilderGreaterThanZero(stringBuilder)) {
                    stringBuilder.deleteCharAt(stringBuilder.toString().length() - 1);
                    text = " e " + createReturnMessage(total, entry, valueInString);
                } else {
                    text = createReturnMessage(total, entry, valueInString);
                }
            }

            stringBuilder.append(text);
        }
    }

    /***
     * Método para criação da mensagem caso haja um próximo object no iterator
     *
     * @param total
     * @param entry
     * @param valueInString
     * @return
     */
    private String createReturnMessage(Double total, Map.Entry<Integer, Double> entry, String valueInString) {
        return entry.getKey() + ConstantUtils.STRING_CUJO_VALOR + valueInString.replace(ConstantUtils.PERIOD_STRING, ConstantUtils.COMMA_STRING) +
                ConstantUtils.PERIOD_STRING + ConstantUtils.TOTAL_STRING + String.format(ConstantUtils.DECIMAL_POINT, total) + ConstantUtils.PERIOD_STRING;
    }

    /***
     * Método para criação da mensagem caso não haja um próximo object no iterator
     *
     * @param entry
     * @param value
     * @return
     */
    private String createReturnMessage(Map.Entry<Integer, Double> entry, Double value) {
        return entry.getKey() + ConstantUtils.STRING_CUJO_VALOR + String.format(ConstantUtils.DECIMAL_POINT, value)
                .replace(ConstantUtils.PERIOD_STRING, ConstantUtils.COMMA_STRING) + ConstantUtils.COMMA_STRING;
    }

}
