package lacerda.luhan.exerciciodois.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/***
 * Classe modelo do Json de entrada
 * @author luhan.melo.t.lacerda
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_composicao")
public class Composicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long codigoComposicao;
    private String descricaoComposicao;
    private String unidadeComposicao;

    private String tipoItem;
    private Long codigoItem;
    private String descricaoItemComposicao;
    private String unidadeItem;
    private String quantidadeComposicao;
    private String valorUnitario;

    private Double valorTotalComposicao;

}
