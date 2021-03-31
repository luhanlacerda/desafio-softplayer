package lacerda.luhan.exerciciodois.repositories;

import lacerda.luhan.exerciciodois.model.Composicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComposicaoRepository extends JpaRepository<Composicao, Long> {

    @Query("SELECT c FROM Composicao c where c.codigoComposicao = ?1")
    List<Composicao> findAllByCodigoComposicao(Long codigoComposicao);

}
