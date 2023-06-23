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

public class CheckBoxexPageTest {

    private static final Logger logger = LogManager.getLogger (CheckBoxexPageTest.class);
    WebDriver driver;
    String url = "http://the-internet.herokuapp.com/";

    @Before
    public void beforeTest(){

        String systemProperties =System.getProperties ().toString ();
        logger.info(systemProperties);

        String driverpath = System.getProperty ("user.dir") + "/driver/chrome/chromedriver";

        System.setProperty ("webdriver.chrome.driver", driverpath);

        driver = new ChromeDriver ();
        driver.get (url);
        driver.manage().window ().maximize ();

    }

    @Test
    public void checkBoxexTest() throws InterruptedException{

        driver.findElement (By.linkText("Checkboxes")).click ();

        String actualHeading = driver.findElement (By.tagName("h3")).getText();
        Assert.assertEquals ("Checkboxes",actualHeading);
        driver.findElement (By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click ();
        Thread.sleep (5000);

        boolean actualStatus = driver.findElement (By.xpath ("//*[@id=\"checkboxes\"]/input[1]")).isSelected ();

        Assert.assertEquals (true,actualStatus);
        logger.info("check status passed");


        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
        boolean actualUncheckedStatus = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).isSelected();
        Assert.assertEquals(false, actualUncheckedStatus);
        logger.info(" Unchecked Status Passed ");

    }

    @After
    public void cleanUp(){
        driver.quit ();
    }

}
