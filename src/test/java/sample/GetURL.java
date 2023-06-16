package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetURL {

    public static void main (String args[]){

        System.setProperty ("webdriver.chrome.driver","driver/chrome/chromedriver");
        WebDriver driver = new ChromeDriver ();
        driver.get ("https://www.izaanschool.com/");
        System.out.println ("webpage open");
        //Thread.sleep (5);

        driver.quit ();

    }
}
