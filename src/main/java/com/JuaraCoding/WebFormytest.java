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

        driver.findElement(By.id("first-name")).sendKeys(firstName);
        System.out.println("1. nama depan = " + firstName);

        driver.findElement(By.id("last-name")).sendKeys(lastName);
        System.out.println("2. nama belakang = " + lastName);

        driver.findElement(By.id("job-title")).sendKeys(Jobtitle);
        System.out.println("3. Job Title = " + Jobtitle);

        driver.findElement(By.id("radio-button-2")).click();
        System.out.println("4. Pilih Highest level of education = College");

        driver.findElement(By.id("checkbox-3")).click();
        System.out.println("5. pilih jenis kelamin");

        WebElement selectExperiance = driver.findElement(By.id("select-menu"));
        Select experience = new Select(selectExperiance);
        experience.selectByValue("2");
        System.out.println("6. Test Select 2-4 experience");

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