package e2e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager();
    protected static Logger Logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void setupTest() {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p) {
        Logger.info("Start test " + m.getName() + "with data" + Arrays.asList());
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    @AfterMethod
    public void stopTest(ITestResult result) {
        if (result.isSuccess()) {
            Logger.info("PASSED" + result.getMethod().getMethodName());
        } else {
            Logger.info("FALLED = (" + result.getMethod().getMethodName());
        }
        Logger.info("====================================================");

    }
}
