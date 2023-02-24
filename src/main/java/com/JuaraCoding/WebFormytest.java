package com.JuaraCoding;


import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class WebFormytest {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\juaracoding\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //import liblary faker
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Faker faker = new Faker(new Locale("in-ID"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String Jobtitle = faker.job().title();
        String dob = sdf.format(faker.date().birthday());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "https://formy-project.herokuapp.com/form";
        driver.get(url);
        System.out.println("Get URL====="+url);
        driver.manage().window().maximize();
        System.out.println("===Maximize Browser===");

        // input first name
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        System.out.println("1. nama depan = " + firstName);

        // input last name
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        System.out.println("2. nama belakang = " + lastName);

        // input Job title
        driver.findElement(By.id("job-title")).sendKeys(Jobtitle);
        System.out.println("3. Job Title = " + Jobtitle);


        // select check box
        WebElement education = driver.findElement(By.xpath("//*[@id=\"radio-button-1\"]"));
        education.click();
        System.out.println("4. Education = " + education.isSelected());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // select check gender
        WebElement gender = driver.findElement(By.xpath("//*[@id=\"checkbox-1\"]"));
        gender.click();
        System.out.println("5. Sex = " + gender.isSelected());

        // select experience
        WebElement selectExperiance = driver.findElement(By.id("select-menu"));
        Select experience = new Select(selectExperiance);
        experience.selectByValue("2");
        System.out.println("6. Test Select 2-4 experience");

        // select calender
        driver.findElement(By.id("datepicker")).sendKeys(dob);
        System.out.println("7. tanggal = " +dob);

        driver.findElement(By.linkText("Submit")).click();
        System.out.println("8. Beshasil di submit");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.quit();
        System.out.println("Browser Quit");

    }
}