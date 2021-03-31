package lacerda.luhan.exercicioum;

import lacerda.luhan.exercicioum.services.ObservacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ExercicioumApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ExercicioumApplication.class);

    @Autowired
    ObservacaoService observacaoService;

    public static void main(String[] args) {
        SpringApplication.run(ExercicioumApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String retorno;

        logger.info("******* Legado *******");
        retorno = observacaoService.geraObservacao(Arrays.asList(1, 2, 3, 4, 5));
        logger.info(retorno);

        Map<Integer, Double> map = new HashMap<>();
        map.put(1, 10.00);
        map.put(2, 35.00);
        map.put(3, 5.00);
        map.put(4, 1500.00);
        map.put(5, 0.30);

        logger.info("******* Nova feature *******");
        retorno = observacaoService.geraObservacao(map);
        logger.info(retorno);
        logger.info("*********************");

    }
}
