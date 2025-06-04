package ifellow.hooks

import io.cucumber.java.After
import io.cucumber.java.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CucumHooks {
    private static final Logger logger = LoggerFactory.getLogger(CucumHooks.class)
    @Before
    public void beforeScenario() {
        logger.info("Начинаем выполнение сценария");
    }
    @After
    public void afterScenario() {
        logger.info("Сценарий завершен")
    }
    @Before
    public void beforeStep() {
        logger.debug("Перед выполнением шага")
    }
    @After
    public void afterStep() {
        logger.debug("Шаг завершен")
    }
}
