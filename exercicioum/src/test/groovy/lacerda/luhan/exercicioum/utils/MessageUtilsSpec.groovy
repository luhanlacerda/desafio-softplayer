package lacerda.luhan.exercicioum.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MessageUtilsSpec extends Specification {

    @Autowired
    MessageUtils messageUtils

    def returnMessage
    def stringBuilder

    def setup() {
        stringBuilder = new StringBuilder()
    }

    def "when size is equals two"() {
        given:
        def size = 2

        when:
        returnMessage = messageUtils.defineMessagePrefix(size)

        then: "return the constant MAIS_DE_UMA_NOTA value"
        returnMessage == ConstantUtils.MAIS_DE_UMA_NOTA
    }

    def "when size is greater than two"() {
        given:
        def size = 3

        when:
        returnMessage = messageUtils.defineMessagePrefix(size)

        then: "return the constant MAIS_DE_UMA_NOTA value"
        returnMessage == ConstantUtils.MAIS_DE_UMA_NOTA
    }

    def "when size is less than two"() {
        given:
        def size = 1

        when:
        returnMessage = messageUtils.defineMessagePrefix(size)

        then: "return the constant UMA_NOTA value"
        returnMessage == ConstantUtils.UMA_NOTA
    }

}
