package ifellow.pages.utils;
import lombok.Getter;
import lombok.Setter;
public class TestContext {
    private static TestContext instance;
    private TestContext() {}
    public static TestContext getInstance() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }
    @Getter @Setter
    private int initialTaskCount;
}