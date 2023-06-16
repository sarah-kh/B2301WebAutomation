package qcc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class QccCalenderData {

    public static void main(String[] args) {


    //private static final Logger logger = LogManager.getLogger (QccCalenderData.class);
    //logger.info("get calaneder info");
    String url = "https://www.qcc.cuny.edu/academics/academic-calendars.html";

    // Get the current System Properties
    String systemProperties = System.getProperties ().toString ();

    String driverpath = System.getProperty ("user.dir") + "/driver/chrome/chromedriver";

    // Let's show the application where is the driver object

    System.setProperty("webdriver.chrome.driver", driverpath); //(first approach)

        // String setProperty = System.setProperty ("webdriver.chrome.driver", driverpath); //(2nd approach)

    WebDriver driver = new ChromeDriver ();

    driver.get(url);

    driver.manage().window ().maximize ();

    String heading =driver.findElement (new By.ByTagName ("h2")).getText ();

    List<WebElement> webElementList = driver.findElements(new By.ByTagName("h2"));
    String firstH2Value = webElementList.get(0).getText();

    driver.close ();

  }

}

