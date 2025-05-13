package ifellow.steps;

import ifellow.pages.HomeworkPage;
import ifellow.pages.TaskPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;
import org.junit.jupiter.api.Assertions;


import java.util.List;

public class CreateBugSteps {
    private TaskPage taskPage = new TaskPage();
    private HomeworkPage homeworkPage = new HomeworkPage();
    private boolean isBugCreated;

    @Когда("пользователь создает новый баг")
    public void createNewBug() {
        homeworkPage.createBug();
    }

    @И("заполняет данные бага:")
    public void fillBugDetails(DataTable dataTable) {
        List<String> values = dataTable.asList();
        isBugCreated = taskPage.createAndResolveBug(
                values.get(0),
                values.get(1),
                values.get(2),
                values.get(3),
                values.get(4),
                values.get(5),
                values.get(6)
        );
    }

    @Тогда("баг успешно создается")
    public void checkBugCreate(){
        Assertions.assertTrue(isBugCreated, "Баг не был создан");
    }
}
