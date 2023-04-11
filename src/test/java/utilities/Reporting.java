package utilities;
//Listener class used to generate extent reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	/*public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  // Time Stamp
		String repName="Test-Report-" + timeStamp+".html";
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+ repName); //specify location
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name",  "localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user"," reka");
		
		htmlReporter.config().setDocumentTitle("nopcommerce test project"); // title of report
		htmlReporter.config().setReportName(" Functional test report");  // name of report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of chart
		htmlReporter.config().setTheme(Theme.DARK);
	 }*/

	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		//String repName="Test-Report.html";
		//String sysDir=System.getProperty("user.dir");
	
		//htmlReporter=new ExtentHtmlReporter("E:/Vasanthi-SDET/WorkspaceVasanthi/BankingProject/test-output/"+repName);//location of extent report
		//htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		
		htmlReporter=new ExtentHtmlReporter("C:/Users/Reka/eclipse-workspace/TestNG_Framework/Reka.TestNG_Framework_DDT/test-output/"+repName);
		
		try {
			//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
			htmlReporter.loadXMLConfig("C:\\Users\\Reka\\eclipse-workspace\\Reka.TestNG_Framework_DDT\\extent_config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Reka");
		
		htmlReporter.config().setDocumentTitle("Ebanking Test Project");//title of report
		htmlReporter.config().setReportName("Functional Test Automation Report");//name of report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}
	public void onTestSuccess(ITestResult tr)
	{
	  logger=extent.createTest(tr.getName());//create new entry in the report
	  logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//Send passed info
	}
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		//String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png";  // screenshot with name of test case
		String screenshotPath="C:\\Users\\Reka\\eclipse-workspace\\Reka.TestNG_Framework_DDT\\Screenshots\\Screenshots"+tr.getName()+".png";
		File f = new File(screenshotPath);
		
		if(f.exists())
			{
			logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			
		}
	}
		public void onTestSkipped(ITestResult tr)
		{
			logger=extent.createTest(tr.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		}
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
			
		}
}