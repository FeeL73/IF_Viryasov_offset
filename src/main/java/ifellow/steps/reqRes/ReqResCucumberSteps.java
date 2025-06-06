package ifellow.steps.reqRes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifellow.utils.Props;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import ifellow.utils.FileUtil;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;

public class ReqResCucumberSteps {
    private Response response;
    private final ReqResSteps reqResSteps = new ReqResSteps();
    private static final Props props = ConfigFactory.create(Props.class);

    @Когда("создаем пользователя, поменяем name и job")
    public void createUser() throws JsonProcessingException {
        String reqresContentType = props.reqresContentType();
        Map<String, String> userData = new ObjectMapper().readValue(
                FileUtil.readFileFromResources("user.json"),
                new TypeReference<HashMap<String, String>>() {}
        );
        userData.put("name", "Tomato");
        userData.put("job", "Eat maket");
        response = reqResSteps.createUser(
                userData,
                props.reqresApiKey(),
                reqresContentType
        );
    }

    @Тогда("проверить что получили ответ 201")
    public void checkAnswer() {
        reqResSteps.verifyStatusCode(response, HttpStatus.SC_CREATED);
    }

    @И("Свериться что ответ имеет валидные данные")
    public void checkValidDate() {
        reqResSteps.verifyUserName(response, props.reqresName());
        reqResSteps.verifyUserJob(response, props.reqresJob());
    }
}

