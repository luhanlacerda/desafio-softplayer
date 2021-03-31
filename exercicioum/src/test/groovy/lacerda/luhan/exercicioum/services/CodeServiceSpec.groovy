package lacerda.luhan.exercicioum.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class CodeServiceSpec extends Specification {

    @Autowired
    CodeService codesService
    def returnMessage
    def expectedMessage

    def "when receive a empty number list"() {
        given:
        expectedMessage = 'Fatura da nota fiscal de simples remessa: .'

        when:
        returnMessage = codesService.returnCodes(Arrays.asList())

        then: "return a empty ${expectedMessage}"
        !returnMessage.empty
        returnMessage == expectedMessage
    }

    def "when receive a number list with only one number"() {
        given:
        expectedMessage = 'Fatura da nota fiscal de simples remessa: 1.'
        def number = 1

        when:
        returnMessage = codesService.returnCodes(Arrays.asList(number))

        then: "return ${expectedMessage}"
        !returnMessage.empty
        returnMessage == expectedMessage
    }

    def "when receive a two or more numbers list"() {
        given:
        expectedMessage = "Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5."

        when:
        returnMessage = codesService.returnCodes(Arrays.asList(1, 2, 3, 4, 5))

        then: "return ${expectedMessage}"
        !returnMessage.empty
        returnMessage == expectedMessage
    }

    def "when receive a empty map"() {
        given:
        expectedMessage = "Fatura da nota fiscal de simples remessa: ."
        def map = []

        when:
        returnMessage = codesService.returnCodes(map)

        then: "return ${expectedMessage}"
        !returnMessage.empty
        returnMessage == expectedMessage
    }

    def "when receive map with only nota fiscal"() {
        given:
        expectedMessage = "Fatura da nota fiscal de simples remessa: 1 cujo valor é R\$ 10,00. Total = 10,00."
        def map = [
                1: 10.00
        ]

        when:
        returnMessage = codesService.returnCodes(map)

        then: "return ${expectedMessage}"
        !returnMessage.empty
        returnMessage == expectedMessage
    }

    def "when receive a of numbers and values in map"() {
        given:
        expectedMessage = "Fatura das notas fiscais de simples remessa: 1 cujo valor é R\$ 10,00, 2 cujo valor é " +
                "R\$ 35,00, 3 cujo valor é R\$ 5,00, 4 cujo valor é R\$ 1500,00 e 5 cujo valor é R\$ 0,30. " +
                "Total = 1550,30."
        def map = [
                1: 10.00,
                2: 35.00,
                3: 5.00,
                4: 1500.00,
                5: 0.30
        ]

        when:
        returnMessage = codesService.returnCodes(map)

        then:
        "return the text inside in ${expectedMessage}"
        !returnMessage.empty
        returnMessage == expectedMessage
    }

}
