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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FullRegression {
	WebDriver driver = null;
    public WebDriverWait getWait() {
        return wait;
    }

   WebDriverWait wait;

    @BeforeTest
    public void setup()  {
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\lenovo\\eclipse-workspace\\Pratice\\Selenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver(getChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://in.indeed.com/");
        driver.manage().window().maximize();
        System.out.println("Before class Setup"+ driver.getTitle());

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
       System.out.println("Debasis test");
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
