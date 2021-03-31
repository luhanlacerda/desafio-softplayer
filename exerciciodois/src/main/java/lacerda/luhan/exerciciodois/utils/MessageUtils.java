package lacerda.luhan.exerciciodois.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import lacerda.luhan.exerciciodois.exceptions.MessageConverterException;
import lacerda.luhan.exerciciodois.model.Composicao;
import lacerda.luhan.exerciciodois.model.ComposicaoRetorno;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

/***
 * Classe utilitária para conversão de elemenos ligados à mensagem
 *
 * @author luhan.melo.t.lacerda
 */
@Component
public class MessageUtils {

    private final Type reviewType = new TypeToken<List<Composicao>>() {
    }.getType();

    public String concatListComposicaoObjectInString(Set<ComposicaoRetorno> listComposicaoRetorno) {
        StringBuilder returnMessage = new StringBuilder();
        for (ComposicaoRetorno composicao : listComposicaoRetorno) {
            returnMessage.append(composicao.toString());
        }
        return returnMessage.toString();
    }

    public List<Composicao> readFileAndConvertJsonToComposicaoList(String filename) throws MessageConverterException {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(filename));
            return gson.fromJson(reader, reviewType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageConverterException("Erro ao converter o arquivo json");
        }

    }

    public BigDecimal round(int scale, BigDecimal value) {
        return new BigDecimal(String.valueOf(value).replace(ConstantUtils.COMMA, ConstantUtils.PERIOD)).setScale(scale, RoundingMode.HALF_UP);
    }

}
