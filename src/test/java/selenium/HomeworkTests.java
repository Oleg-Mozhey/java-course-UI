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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void checkboxesSelectionTest(){
        Actions actions = new Actions(driver);
        driver.get("https://demoqa.com/checkbox");

        ArrayList<String> toggles = new ArrayList<>();
        toggles.add("//span[text() ='Home']//parent::label//parent::span/button");
        toggles.add("//span[text() ='Documents']//parent::label//parent::span/button");
        toggles.add("//span[text() ='WorkSpace']//parent::label//parent::span/button");
        toggles.add("//span[text() ='Downloads']//parent::label//parent::span/button");

        for (String toggleToOpen: toggles) {
            WebElement toggle = driver.findElement(By.xpath(toggleToOpen));
            toggle.click();
        }

        ArrayList<String> checkboxes = new ArrayList<>();
        checkboxes.add("//span[text() ='Word File.doc']//parent::label");
        checkboxes.add("//span[text() ='Excel File.doc']//parent::label");
        checkboxes.add("//span[text() ='Angular']//parent::label");

        for (String checkbox: checkboxes) {
            WebElement check = driver.findElement(By.xpath(checkbox));
            actions.moveToElement(check).click().build().perform();
        }


        WebElement result = driver.findElement(By.xpath("//*[@id=\"result\"]"));
        assertThat(result.getText()).contains("You have selected :\nangular\ndownloads\nwordFile\nexcelFile");

    }

    @Test
    public void workWithTablesTest(){
        Actions actions = new Actions(driver);
        driver.get("https://demoqa.com/webtables");

        WebElement editButton = driver.findElement(By.xpath("//div[text() ='Cierra']/parent::*//div[contains(@class, \"action-buttons\")]/span[@id = \"edit-record-1\"]"));
        editButton.click();
        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameInput.clear();
        lastNameInput.sendKeys("New last name");
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.submit();
        WebElement addNewPerson = driver.findElement(By.xpath("//*[@id=\"addNewRecordButton\"]"));
        addNewPerson.click();
        WebElement firstNameInput = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstNameInput.sendKeys("Ivan");
        lastNameInput = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastNameInput.sendKeys("Ivanov");
        WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"userEmail\"]"));
        emailInput.sendKeys("test@gmail.com");
        WebElement ageInput = driver.findElement(By.xpath("//*[@id=\"age\"]"));
        ageInput.sendKeys("1");
        WebElement salaryInput = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
        salaryInput.sendKeys("100500");
        WebElement department = driver.findElement(By.xpath("//*[@id=\"department\"]"));
        department.sendKeys("Test");
        submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.submit();
    }

    @Test
    public void checkTextInModalTest() throws InterruptedException {
        driver.get("https://demoqa.com/modal-dialogs");

        WebElement smallModalButton = driver.findElement(By.xpath("//*[@id=\"showSmallModal\"]"));
        smallModalButton.click();
        WebElement ModalText = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]"));
        assertThat(ModalText.getText()).contains("This is a small modal. It has very less content");
        WebElement closeSmallModalButton = driver.findElement(By.xpath("//*[@id=\"closeSmallModal\"]"));
        closeSmallModalButton.click();
        WebElement largeModalButton = driver.findElement(By.xpath("//*[@id=\"showLargeModal\"]"));
        largeModalButton.click();
        Thread.sleep(2000);   //little hack here
        ModalText = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]"));
        assertThat(ModalText.getText()).contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    }

}
