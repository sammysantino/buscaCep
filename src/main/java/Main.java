import br.com.labs.buscacep.util.Util;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.debug("Hello from Logback");

        logger.debug("getNumber() : {}", getNumber());
        
        Map<String, Integer> senhas = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
        	String senha = Util.gerarSenha();
        	logger.info(senha);
//        	if (senhas.containsKey(senha)) {
//        		logger.info(senha);
//        	}
        }
        logger.info("Fim");
        

    }

    static int getNumber() {
        return 5;
    }

}
