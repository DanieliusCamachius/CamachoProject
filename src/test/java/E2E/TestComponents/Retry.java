package E2E.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 1;

    // for flaky tests
    @Override
    public boolean retry(ITestResult result) {

        if(count<maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}
