package TestScripts;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Allure_Listener implements ITestListener {
	private static String getTestMethodName(ITestResult iTestResult)
	{
		return iTestResult.getMethod().getConstructorOrMethod().getName();
		
	}

}
