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

public class DisappearingElements {

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
    public void homeButtonTest() throws InterruptedException {

        //go to Disappearing Elements page
        driver.findElement (By.linkText ("Disappearing Elements")).click ();
        Thread.sleep (1000);
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Disappearing Elements",actualHeading);

        // verify "Home" button

        driver.findElement (By.linkText ("Home")).click ();
        String welcomePageHeading = driver.findElement (By.tagName ("h1")).getText ();
        Assert.assertEquals ("Welcome to the-internet",welcomePageHeading);

    }
    @Test
    public void aboutButtonTest() throws InterruptedException {
        //go to Disappearing Elements page
        driver.findElement (By.linkText ("Disappearing Elements")).click ();
        Thread.sleep (1000);
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Disappearing Elements",actualHeading);

        driver.findElement (By.linkText ("About")).click ();
        String aboutPageHeading = driver.findElement (By.tagName ("h1")).getText ();
        Assert.assertEquals ("Not Found",aboutPageHeading);
    }

    @Test
    public void contactUSButtonTest() throws InterruptedException {
        //go to Disappearing Elements page
        driver.findElement (By.linkText ("Disappearing Elements")).click ();
        Thread.sleep (1000);
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Disappearing Elements",actualHeading);

        driver.findElement (By.linkText ("Contact Us")).click ();
        String contactUsPageHeading = driver.findElement (By.tagName ("h1")).getText ();
        Assert.assertEquals ("Not Found",contactUsPageHeading);
    }

    @After
    public void cleanUp(){
        driver.close ();
    }

}
