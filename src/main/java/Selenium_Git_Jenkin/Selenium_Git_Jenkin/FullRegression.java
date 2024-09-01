package Selenium_Git_Jenkin.Selenium_Git_Jenkin;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FullRegression {
	WebDriver driver = null;
	static ExtentTest test;
	static ExtentReports extent;
	String ws = System. getProperty("user.dir");
    public WebDriverWait getWait() {
        return wait;
    }

   WebDriverWait wait;
   
   @BeforeSuite
   public void setupReport() {
   	extent = new ExtentReports();
   	ExtentSparkReporter spark = new ExtentSparkReporter(ws+"\\reports\\HtmilReport.html");
   	extent.attachReporter(spark);
   }
    @BeforeTest
    public void setup()  {
    	test = extent.createTest("Class");
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\lenovo\\eclipse-workspace\\Pratice\\Selenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver(getChromeOptions());
        test.log(Status.INFO, "browser launched");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://in.indeed.com/");
        driver.manage().window().maximize();
        System.out.println("Before class Setup"+ driver.getTitle());
        test.log(Status.PASS, "Application title fetched");

    }
    @Test
    public void logintoApp(){
        sleep(1);

        driver.findElement(By.xpath("//input[@id='text-input-what']")).click();
        sleep(1);
        driver.findElement(By.xpath("//input[@id='text-input-what']")).sendKeys("Software Testing"+Keys.ENTER);
        sleep(1);
       driver.findElement(By.xpath("//input[@aria-label='Edit location']")).click();
       sleep(1);

       driver.findElement(By.xpath("//input[@aria-label='Edit location']")).sendKeys("Bengaluru,Karnataka");
       driver.findElement(By.xpath("//button[text()='Find jobs']")).click();
       test.log(Status.PASS, "Debasis");
       //.click(); 
       //sleep(20);
    }
//    @Test(priority=1)
//    public void windowhandel() {
//    	WebElement ele=driver.findElement(By.xpath("//button[@aria-label='close']"));
//    	if(ele.isDisplayed()) {
//    		ele.click();
//    		System.out.println("popup closed");
//    	}
//    }


    @AfterTest
    public void tearDown(){
        driver.close();
        System.out.println("After class teardown");
    }
    
    @AfterSuite
    public void tearreport() {
    	extent.flush();
    }
    public void sleep(int timeinsec){
        try{
            Thread.sleep(timeinsec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ChromeOptions getChromeOptions() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1580,1280");

        final HashMap<String, Object> prefs = new HashMap();
        chromeOptions.setExperimentalOption("prefs", prefs);

        return chromeOptions;
    }
    
}
