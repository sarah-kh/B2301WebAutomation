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

public class Add_RemoveElementsTest {

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
    public void addRemoveElementTest() throws InterruptedException {
        //go to add/remove page
        driver.findElement (By.linkText ("Add/Remove Elements")).click ();
        Thread.sleep (1000);
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Add/Remove Elements",actualHeading);



        //click add button; element will be added
        driver.findElement (By.xpath ("//*[@id=\"content\"]/div/button")).click ();

        //click delete button; element will be deleted
        driver.findElement (By.xpath ("//*[@id=\"elements\"]/button")).click ();
        Thread.sleep (2000);

    }
    @After
    public void cleanUp(){
        driver.close ();
    }
}
