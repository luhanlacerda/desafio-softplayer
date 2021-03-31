package lacerda.luhan.exercicioum.services

import lacerda.luhan.exercicioum.utils.ConstantUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MessageServiceSpec extends Specification {

    @Autowired
    MessageService buildMessageService;
    def expectedMessage
    def returnMessage
    def stringBuilder

    def setup() {
        stringBuilder = new StringBuilder()
    }

    def "when receive a empty number list"() {
        given:
        expectedMessage = ''

        when:
        buildMessageService.buildReturnMessage(Arrays.asList(), stringBuilder)

        then: "return a empty string"
        stringBuilder != null
        stringBuilder.toString() == expectedMessage
    }

    def "when receive a number list with only one number"() {
        given:
        expectedMessage = '1'
        def number = 1

        when:
        buildMessageService.buildReturnMessage(Arrays.asList(number), stringBuilder)

        then: " return 1"
        stringBuilder != null
        stringBuilder.toString() == expectedMessage
    }

    def "when receive a two or more numbers list"() {
        given:
        expectedMessage = "1, 2, 3, 4 e 5"

        when:
        buildMessageService.buildReturnMessage(Arrays.asList(1, 2, 3, 4, 5), stringBuilder)

        then: "return 1, 2, 3, 4 e 5"
        stringBuilder != null
        stringBuilder.toString() == expectedMessage
    }

    def "when receive a empty map"() {
        given:
        expectedMessage = ""
        def map = []

        when:
        buildMessageService.buildReturnMessage(map, stringBuilder)

        then: "return a empty string"
        stringBuilder != null
        stringBuilder.toString() == expectedMessage
    }

    def "when receive map with only nota fiscal"() {
        given:
        expectedMessage = "1 cujo valor é R\$ 10,00. Total = 10,00."
        def map = [
                1: 10.00
        ]
        def iterator = map.entrySet().iterator();

        when:
        buildMessageService.buildReturnMessage(iterator, stringBuilder)

        then: "return 1 cujo valor é R\$ 10,00. Total = 10,00."
        stringBuilder != null
        stringBuilder.toString() == expectedMessage
    }

    def "when receive a of numbers and values in map"() {
        given:
        expectedMessage = "1 cujo valor é R\$ 10,00, 2 cujo valor é R\$ 35,00, 3 cujo valor é R\$ 5,00, 4 cujo valor é " +
                "R\$ 1500,00 e 5 cujo valor é R\$ 0,30. Total = 1550,30."
        def map = [
                1: 10.00,
                2: 35.00,
                3: 5.00,
                4: 1500.00,
                5: 0.30
        ]
        def iterator = map.entrySet().iterator();

        when:
        buildMessageService.buildReturnMessage(iterator, stringBuilder)

        then:
        "return the text inside in ${expectedMessage}"
        stringBuilder != null
        stringBuilder.toString() == expectedMessage
    }

}
