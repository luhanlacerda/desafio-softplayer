package lacerda.luhan.exerciciodois.service

import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import lacerda.luhan.exerciciodois.exceptions.MessageConverterException
import lacerda.luhan.exerciciodois.repositories.ComposicaoRepository
import lacerda.luhan.exerciciodois.utils.MessageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ComposicaoServiceSpec extends Specification {

    static final String FILENAME = "src/main/resources/dados-entrada-servicos-composicoes.json"

    @Autowired
    ComposicaoService composicaoService

    @Autowired
    ComposicaoRepository composicaoRepository

    @Autowired
    MessageUtils messageConverter

    def expected
    def gson
    def reader
    def data
    def messageReturn

    def setup() {
        composicaoRepository.deleteAll()
        gson = new Gson();
        reader = new JsonReader(new FileReader(FILENAME))
    }


    def "when receive a valid json"() {
        given:
        expected = "98561 IMPERMEABILIZAÇÃO DE PAREDES COM ARGAMASSA DE CIMENTO E AREIA, COM ADITIVO IMPERMEABILIZANTE, E = 2CM. AF_06/2018 M2 28.75\n" +
                "87286 ARGAMASSA TRAÇO 1:1:6 (CIMENTO, CAL E AREIA MÉDIA) PARA EMBOÇO/MASSA ÚNICA/ASSENTAMENTO DE ALVENARIA DE VEDAÇÃO, PREPARO MECÂNICO COM BETONEIRA 400 L. AF_06/2014 M3 289.98\n" +
                "94793 REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016 UN 128.61\n" +
                "88830 BETONEIRA CAPACIDADE NOMINAL DE 400 L, CAPACIDADE DE MISTURA 280 L, MOTOR ELÉTRICO TRIFÁSICO POTÊNCIA DE 2 CV, SEM CARREGADOR - CHP DIURNO. AF_10/2014 CHP 1.25\n" +
                "88831 BETONEIRA CAPACIDADE NOMINAL DE 400 L, CAPACIDADE DE MISTURA 280 L, MOTOR ELÉTRICO TRIFÁSICO POTÊNCIA DE 2 CV, SEM CARREGADOR - CHI DIURNO. AF_10/2014 CHI 0.22\n"

        when:
        messageReturn = composicaoService.readFileAndReturnComposicoes(FILENAME)

        then: "returns the composicoes with yours values"
        messageReturn == expected
    }

    def "when receive a invalid json"() {
        given:
        def json = "[\n" +
                "    {\n" +
                "      \"codigoComposicao\": 94793,\n" +
                "      \"descricaoComposicao\": \"REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016\",\n" +
                "      \"unidadeComposicao\": \"UN\",\n" +
                "      \"tipoItem\": \"INSUMO\",\n" +
                "      \"codigoItem\": 3148,\n" +
                "      \"descricaoItemComposicao\": \"FITA VEDA ROSCA EM ROLOS DE 18 MM X 50 M (L X C)\",\n" +
                "      \"unidadeItem\": \"UN\",\n" +
                "      \"quantidadeComposicao\": \"0,0190000\",\n" +
                "      \"valorUnitario\": \"\"\n" +
                "    }" +
                "]"
        def fileWithInvalidJson = "src/main/resources/invalid-dados-entrada-servicos-composicoes.json"

        when:
        messageReturn = composicaoService.readFileAndReturnComposicoes(fileWithInvalidJson)

        then: "returns the composicoes with yours values"
        messageReturn == null
        thrown(MessageConverterException.class)
    }


}
