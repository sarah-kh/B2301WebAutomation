package heroku;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ContextMenuPageTest {

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
    public void contextMenuTest() throws InterruptedException {
        // go to contextMenu page
       driver.findElement (By.linkText ("Context Menu")).click ();

       //check the heading of the page once inside the page
       String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
       Assert.assertEquals ("Context Menu", actualHeading);
       Thread.sleep (3000);
       logger.info ("Context Menu page open");


      // right click inside the box to launch the right-click menu
       Actions actions = new Actions (driver);

       //find the menu
       WebElement box = driver.findElement (By.xpath ("//*[@id=\"hot-spot\"]"));

       actions.contextClick (box).perform ();

       Thread.sleep (3000);

       driver.switchTo ().alert ().accept ();
       logger.info ("right click alert accepted");
       Thread.sleep (3000);


    }

    @After
    public void cleanup(){
        driver.quit ();
    }

}
