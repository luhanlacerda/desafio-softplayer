package lacerda.luhan.exerciciodois;

import lacerda.luhan.exerciciodois.service.ComposicaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExerciciodoisApplication implements CommandLineRunner {

    private static final String FILENAME = "src/main/resources/dados-entrada-servicos-composicoes.json";
    private static Logger logger = LoggerFactory.getLogger(ExerciciodoisApplication.class);

    @Autowired
    private ComposicaoService composicaoService;

    public static void main(String[] args) {
        SpringApplication.run(ExerciciodoisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            String messageReturn = composicaoService.readFileAndReturnComposicoes(FILENAME);
            logger.info("********* INICIO DAS COMPOSIÇÕES *********\n");
            logger.info(messageReturn);
            logger.info("********* FIM *********");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


}


