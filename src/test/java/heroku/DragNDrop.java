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

import javax.accessibility.AccessibleAction;

public class DragNDrop {

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
    public void dragNDropTest(){

        driver.findElement (By.linkText ("Drag and Drop")).click ();
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Drag and Drop",actualHeading);

        //Actions class method to drag and drop

        Actions builder = new Actions (driver);
        
        WebElement from = driver.findElement (By.id ("column-a"));
        WebElement to = driver.findElement (By.id ("column-b"));

        //Perform drag and drop
         builder.dragAndDrop (from,to).perform ();
    }
    @After
    public void cleanUp(){
        driver.close ();
    }
}
