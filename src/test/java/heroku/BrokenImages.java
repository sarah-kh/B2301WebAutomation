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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class BrokenImages {

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
    public void brokenImages () throws InterruptedException, IOException {

        driver.findElement (By.linkText ("Broken Images")).click ();
        Thread.sleep (1000);
        String actualHeading = driver.findElement (By.tagName ("h3")).getText ();
        Assert.assertEquals ("Broken Images",actualHeading);

        //list of images

        List<WebElement> images = driver.findElements (By.tagName ("img"));
        logger.info (images.size ());

        //find the broken images
        for (WebElement image: images){

            String imageSrc = image.getAttribute ("src");
            URL url = new URL (imageSrc);
            URLConnection urlConnection = url.openConnection ();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setConnectTimeout (3000);
            httpURLConnection.connect ();

            if(httpURLConnection.getResponseCode () == 200)
                System.out.println (imageSrc + ">>" + httpURLConnection.getResponseCode () + ">>" + httpURLConnection.getResponseMessage ());
            else
                System.err.println (imageSrc + ">>" + httpURLConnection.getResponseCode () + ">>" + httpURLConnection.getResponseMessage ());

            httpURLConnection.disconnect ();

        }

    }
    @After
    public void cleanUp(){
        driver.close ();
    }

}
