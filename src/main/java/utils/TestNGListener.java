package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    Logger logger = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        logger.info("\t=== START === \n\ttest -> "
                + result.getName()
                + "  in class: " + result.getTestClass().getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        logger.info("\t=== SUCCESS === \n\ttest -> "
                + result.getName()
                + "  in class: " + result.getTestClass().getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        logger.info("\t=== FAIL === \n\ttest -> "
                + result.getName()
                + "  in class:" + result.getTestClass().getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        logger.info("\t=== SKIPP === \n\ttest -> "
                + result.getName()
                + "  in class:" + result.getTestClass().getName());
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        logger.info("start testing on date--> "
                + "\n\t\t============= start =============\t"
                + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        logger.info("stop testing on date--> "
                + "\n\t\t============= stop ==============\t"
                + context.getEndDate());
    }


}
