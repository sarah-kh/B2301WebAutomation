package heroku;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicContent {

    private static final Logger logger = LogManager.getLogger (CheckBoxexPageTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest(){

        //get the current system properties

        String systemProperties =System.getProperties ().toString ();
        //logger.info(systemProperties);

        String driverpath = System.getProperty ("user.dir") + "/driver/chrome/chromedriver";

        // let`s show the application where is the driver object
        System.setProperty ("webdriver.chrome.driver", driverpath);

        driver = new ChromeDriver ();// chrome browser object

        //open a new chrome window and browse to the website using URL
        driver.get (url);

        //maximizing the size of the chrome window
        driver.manage().window ().maximize ();

    }
    @Test
    public void dynamicContentTest() throws InterruptedException {

        driver.findElement (By.linkText ("Dynamic Content")).click ();
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Dynamic Content",actualHeading);


        // check initial text
        driver.findElement (By.xpath ("//*[@id=\"content\"]/div[3]/div[2]")).getText ();
        boolean actualText = driver.findElement (By.xpath ("//*[@id=\"content\"]/div[3]/div[2]")).isDisplayed ();
        Assert.assertEquals (true,actualText);

        Thread.sleep (2000);

        driver.findElement (By.linkText ("click here")).click ();
        driver.findElement (By.xpath ("//*[@id=\"content\"]/div[3]/div[2]")).getText ();
        boolean newText = driver.findElement (By.xpath ("//*[@id=\"content\"]/div[3]/div[2]")).isDisplayed ();
        Assert.assertEquals (true,newText);
        logger.info ("text has been changed");

    }
    @After
    public void cleanUp(){
        driver.close ();
    }
}
