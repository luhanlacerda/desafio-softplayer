package lacerda.luhan.exerciciodois.exceptions;

/***
 * Classe de exception para ser usada em caso de erro na conversão da mensagem
 *
 * @author luhan.melo.t.lacerda
 */
public class MessageConverterException extends Exception {

    private static final long serialVersionUID = -63477813834170972L;

    public MessageConverterException() {
        super();
    }

    public MessageConverterException(String errorMessage) {
        super(errorMessage);
    }

    public MessageConverterException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
