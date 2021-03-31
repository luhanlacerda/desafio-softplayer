package lacerda.luhan.exerciciodois.service;

import lacerda.luhan.exerciciodois.exceptions.MessageConverterException;
import lacerda.luhan.exerciciodois.model.Composicao;
import lacerda.luhan.exerciciodois.model.ComposicaoRetorno;
import lacerda.luhan.exerciciodois.repositories.ComposicaoRepository;
import lacerda.luhan.exerciciodois.utils.ConstantUtils;
import lacerda.luhan.exerciciodois.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Classe de serviço contendo o core da aplicação
 * @author luhan.melo.t.lacerda
 */

@Component
public class ComposicaoService {

    @Autowired
    private MessageUtils messageUtils;

    @Autowired
    private ComposicaoRepository composicaoRepository;

    /***
     * Método core da aplicação, onde é realizado a leitura e chamada dos outros componentes para a montagem da mensagem
     * de retorno
     *
     * @param filename
     * @return
     * @throws MessageConverterException
     */
    public String readFileAndReturnComposicoes(String filename) throws MessageConverterException {
        try {
            List<Composicao> listComposicao = messageUtils.readFileAndConvertJsonToComposicaoList(filename);
            Set<Long> listCodigoComposicao = new HashSet<>();
            saveAndGetCodigoComposicao(listComposicao, listCodigoComposicao);
            Set<ComposicaoRetorno> setComposicaoRetorno = new HashSet<>();

            for (Long codigoComposicao : listCodigoComposicao) {
                List<Composicao> listComposicaoFromRepository = composicaoRepository.findAllByCodigoComposicao(codigoComposicao);
                Double total = getTotalByComposicao(listComposicaoFromRepository);
                setComposicaoRetorno.addAll(updateComposicao(listComposicaoFromRepository, total));
            }

            return messageUtils.concatListComposicaoObjectInString(setComposicaoRetorno);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageConverterException("Erro ao converter a mensagem. Arquivo com o formato de json inválido");
        }
    }

    /***
     * Método para salvar as composições com seus respectivos itens em banco de dados e retonar apenas os códigos das
     * composições sem ficar repetido como ocorre no input do json.
     *
     * @param listComposicao
     * @param listCodigoComposicao
     */
    private void saveAndGetCodigoComposicao(List<Composicao> listComposicao, Set<Long> listCodigoComposicao) {

        for (Composicao composicao : listComposicao) {
            listCodigoComposicao.add(composicao.getCodigoComposicao());
            composicaoRepository.save(composicao);
        }

    }

    /***
     * Método para pegar valores de cada item da composição e somar para dar o valor total
     *
     * @param listComposicao
     * @return
     */
    private Double getTotalByComposicao(List<Composicao> listComposicao) {
        BigDecimal total = new BigDecimal("0.00");

        for (Composicao composicao : listComposicao) {
            BigDecimal quantidade = new BigDecimal(composicao.getQuantidadeComposicao().replace(ConstantUtils.COMMA, ConstantUtils.PERIOD));
            BigDecimal valorUnitario = new BigDecimal(composicao.getValorUnitario().replace(ConstantUtils.COMMA, ConstantUtils.PERIOD));
            BigDecimal totalTemp = quantidade.multiply(valorUnitario);
            total = messageUtils.round(ConstantUtils.BIGDECIMAL_ROUND_LENGHT, total.add(totalTemp));
        }

        return Double.valueOf(total.toString());
    }

    /***
     * Método para atualização do valor referente a composição de acordo com a quantidade e valor unitário do item
     * e após isso utilizar o valor total (quantidade * valor unitário) para somar o valor total da composição
     *
     * @param listComposicao
     * @param total
     * @return
     */
    private Set<ComposicaoRetorno> updateComposicao(List<Composicao> listComposicao, Double total) {
        Set<ComposicaoRetorno> setComposicaoRetorno = new HashSet<>();

        listComposicao.stream().forEach(composicao -> {
            composicao.setValorTotalComposicao(total);
            composicaoRepository.save(composicao);

            setComposicaoRetorno.add(new ComposicaoRetorno(composicao.getCodigoComposicao(),
                    composicao.getDescricaoComposicao(), composicao.getUnidadeComposicao(),
                    String.valueOf(composicao.getValorTotalComposicao())));
        });

        return setComposicaoRetorno;
    }

}
