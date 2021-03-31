package lacerda.luhan.exerciciodois.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/***
 * Classe de modelo para retonar a Composição
 * @author luhan.melo.t.lacerda
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComposicaoRetorno {

    private Long codigoComposicao;
    private String descricaoComposicao;
    private String unidadeComposicao;
    private String valorUnitario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComposicaoRetorno that = (ComposicaoRetorno) o;
        return codigoComposicao.equals(that.codigoComposicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoComposicao);
    }

    @Override
    public String toString() {
        return codigoComposicao + " " + descricaoComposicao + " " + unidadeComposicao + " " + valorUnitario + "\n";
    }
}
