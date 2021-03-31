package lacerda.luhan.exerciciodois.utils


import lacerda.luhan.exerciciodois.exceptions.MessageConverterException
import lacerda.luhan.exerciciodois.model.Composicao
import lacerda.luhan.exerciciodois.model.ComposicaoRetorno
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MessageUtilsSpec extends Specification {

    static final String FILENAME = "src/main/resources/dados-entrada-servicos-composicoes.json"

    @Autowired
    MessageUtils messageConverter

    def expected
    def dataReturn

    def setup() {
        expected = new ArrayList<Composicao>()
    }

    def "when read a file with valid json"() {
        when:
        dataReturn = messageConverter.readFileAndConvertJsonToComposicaoList(FILENAME)

        then: "returns a list of composicoes"
        !dataReturn.isEmpty()
        dataReturn != null
        dataReturn instanceof List<Composicao>
    }

    def "when read a file that dont exists"() {
        when:
        def invalidFileName = 'src/main/resources/teste.json'
        dataReturn = messageConverter.readFileAndConvertJsonToComposicaoList(invalidFileName)

        then: "return a null"
        thrown(MessageConverterException.class)
        dataReturn == null
    }

    def "when read a file with valid json and concat ListComposicaoInStringObject"() {
        when:
        expected = "98561 IMPERMEABILIZAÇÃO DE PAREDES COM ARGAMASSA DE CIMENTO E AREIA, COM ADITIVO IMPERMEABILIZANTE, E = 2CM. AF_06/2018 M2 4,44\n" +
                "87286 ARGAMASSA TRAÇO 1:1:6 (CIMENTO, CAL E AREIA MÉDIA) PARA EMBOÇO/MASSA ÚNICA/ASSENTAMENTO DE ALVENARIA DE VEDAÇÃO, PREPARO MECÂNICO COM BETONEIRA 400 L. AF_06/2014 M3 67,50\n" +
                "94793 REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016 UN 9,40\n" +
                "88830 BETONEIRA CAPACIDADE NOMINAL DE 400 L, CAPACIDADE DE MISTURA 280 L, MOTOR ELÉTRICO TRIFÁSICO POTÊNCIA DE 2 CV, SEM CARREGADOR - CHP DIURNO. AF_10/2014 CHP 0,18\n" +
                "88831 BETONEIRA CAPACIDADE NOMINAL DE 400 L, CAPACIDADE DE MISTURA 280 L, MOTOR ELÉTRICO TRIFÁSICO POTÊNCIA DE 2 CV, SEM CARREGADOR - CHI DIURNO. AF_10/2014 CHI 0,18\n"
        def listComposicao = messageConverter.readFileAndConvertJsonToComposicaoList(FILENAME)
        def listComposicaoRetorno = new HashSet<ComposicaoRetorno>()
        for (Composicao composicao : listComposicao) {
            listComposicaoRetorno.add(new ComposicaoRetorno(composicao.getCodigoComposicao(), composicao.getDescricaoComposicao(), composicao.getUnidadeComposicao(), composicao.getValorUnitario()))
        }
        dataReturn = messageConverter.concatListComposicaoObjectInString(listComposicaoRetorno)

        then: "returns a string with composicoes"
        !dataReturn.isEmpty()
        dataReturn != null
        dataReturn == expected
    }

    def "when receive a double with x decimals case"() {
        when:
        def valueToPass = 12.223D
        expected = 12.22D
        dataReturn = messageConverter.round(2, valueToPass)

        then: "returns a BigDecimal with the quantity of decimals case that you give to method"
        dataReturn != null
        dataReturn == expected
    }

}
