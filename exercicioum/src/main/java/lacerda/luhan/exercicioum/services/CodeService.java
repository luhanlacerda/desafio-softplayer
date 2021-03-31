package lacerda.luhan.exercicioum.services;

import lacerda.luhan.exercicioum.utils.ConstantUtils;
import lacerda.luhan.exercicioum.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/***
 * Classe utilizada para retornar a mensagem com o seu devido texto montado
 *
 * @author luhan.melo.t.lacerda
 */
@Component
public class CodeService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUtils messageUtils;

    private String text;

    //Cria observacao
    public String returnCodes(List<Integer> lista) {
        //Identificador da entidade
        text = messageUtils.defineMessagePrefix(lista.size());

        //Acha separador
        StringBuilder stringBuilder = new StringBuilder();
        messageService.buildReturnMessage(lista, stringBuilder);

        return text + stringBuilder + ConstantUtils.PERIOD_STRING;
    }

    //Cria observacao
    public String returnCodes(Map<Integer, Double> lista) {
        //Identificador da entidade
        text = messageUtils.defineMessagePrefix(lista.size());

        // Iterator com a nota e o valor
        Iterator<Map.Entry<Integer, Double>> iterator = lista.entrySet().iterator();

        StringBuilder stringBuilder = new StringBuilder();

        messageService.buildReturnMessage(iterator, stringBuilder);

        return text + stringBuilder;
    }

}
