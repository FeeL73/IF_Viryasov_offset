package ifellow.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Hooks  {
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    @Before
    public void setup() {
        log.info("Начинаем выполнение теста");
    }
    @After
    public void afterScenario() {
        log.info("Тест завершён");
    }
}
