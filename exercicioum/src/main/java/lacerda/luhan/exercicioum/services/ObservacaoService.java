package lacerda.luhan.exercicioum.services;

import lacerda.luhan.exercicioum.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ObservacaoService {

    @Autowired
    private CodeService codeService;

    //Gera observacoes, com texto pre-definido, incluindo os numeros, das notas fiscais, recebidos no paremetro
    public String geraObservacao(List<Integer> lista) {
        if (lista.isEmpty())
            return ConstantUtils.EMPTY_STRING;
        else
            return codeService.returnCodes(lista);

    }

    //Gera observacoes, com texto pre-definido, incluindo os numeros e valores das notas fiscais, recebidos no paremetro
    public String geraObservacao(Map<Integer, Double> lista) {
        if (lista.isEmpty())
            return ConstantUtils.EMPTY_STRING;
        else
            return codeService.returnCodes(lista);

    }

}
