package selenium;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class HomeworkTests {

    //Instructions
    //Create a new UI project with Maven, JUnit, and Selenium Framework
    //

    //https://demoqa.com/automation-practice-form
    //Fill in all necessary fields, and submit the form.
    //Fill in all necessary fields + a few additional fields, and submit the form

    //https://demoqa.com/checkbox
    //Select all from the downloads folder
    //Select Angular

    //https://demoqa.com/webtables
    //Change Last name for the user with the name Cierra
    //Add a new row
    //Delete the user with the email alden@example.com

    //https://demoqa.com/modal-dialogs
    //Check text in modals.

    //https://demoqa.com/accordian
    //Open the accordion Where does it come from? and check that text contains Hampden-Sydney College

    //http://uitestingplayground.com/visibility
    //Press the Hide button and check whether other buttons are visible.
    //
    //Create a new Git repository and push tests.

    WebDriver driver;

    @BeforeAll
    static void setupClass() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void fillRequiredInputsTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement firstNameInput = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameInput.sendKeys("Ilya");
        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameInput.sendKeys("Varlamov");
        WebElement otherGenderCheckbox = driver.findElement(By.xpath("//*[@id=\"gender-radio-3\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(otherGenderCheckbox).click().build().perform();
        WebElement mobilePhoneInput = driver.findElement(By.xpath("//*[@id=\"userNumber\"]"));
        mobilePhoneInput.sendKeys("1234567890");
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.submit();



        WebElement confirmationForm = driver.findElement(By.xpath("//*[@id=\"example-modal-sizes-title-lg\"]"));
        assertThat(confirmationForm.getText()).contains("Thanks for submitting the form");

    }

    @Test
    void fillTheFormTest() {
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement firstNameInput = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameInput.sendKeys("Ilya");
        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameInput.sendKeys("Varlamov");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"userEmail\"]"));
        emailInput.sendKeys("test@gmail.com");
        WebElement otherGenderCheckbox = driver.findElement(By.xpath("//*[@id=\"gender-radio-3\"]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(otherGenderCheckbox).click().build().perform();
        WebElement mobilePhoneInput = driver.findElement(By.xpath("//*[@id=\"userNumber\"]"));
        mobilePhoneInput.sendKeys("1234567890");
        WebElement datePicker = driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
        datePicker.click();
        WebElement date = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div[1]/div[2]"));
        actions.moveToElement(date).click().build().perform();
        WebElement subjectsDropdown = driver.findElement(By.xpath("//*[@id=\"subjectsContainer\"]/div/div[1]"));
        subjectsDropdown.click();
        actions.sendKeys("History");
        subjectsDropdown.submit();
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.submit();



        WebElement confirmationForm = driver.findElement(By.xpath("//*[@id=\"example-modal-sizes-title-lg\"]"));
        assertThat(confirmationForm.getText()).contains("Thanks for submitting the form");

    }

}
