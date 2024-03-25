package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyStepdefs {

    private WebDriver driver;

    @Given("I'm on the registration page")
    public void iMOnTheRegistrationPage() {

        driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I fill all the fields as required")
    public void iFillAllTheFieldsAsRequired() {

        int min = 1;
        int max = 1000000;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;

        driver.findElement(By.id("dp")).sendKeys("30/05/1988");

        WebElement mailAdress = driver.findElement(By.cssSelector("#member_emailaddress"));
        String email = "maria" + rand + "@myway.com";
        mailAdress.sendKeys(email);

        WebElement confirmMailAdress = driver.findElement(By.cssSelector("#member_confirmemailaddress"));
        confirmMailAdress.sendKeys(email);

        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(7) > label > span.box")).click();

    }

    @And("I fill first and last name")
    public void iFillFirstAndLastName() {
        driver.findElement(By.id("member_firstname")).sendKeys("Maria");
        driver.findElement(By.id("member_lastname")).sendKeys("Afram");
    }

    @And("I accept the terms of use")
    public void iAcceptTheTermsOfUse() throws InterruptedException {
        driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > label > span.box")).click();

        Thread.sleep(2000);
    }

    @Then("I should receive a message that Am a member now")
    public void iShouldReceiveAMessageThatAmMemberNow() {
        assertThat(driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText(), is("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"));

        driver.close();
    }

    @When("I fill in first name but forgot last name")
    public void iFillInFirstNameButForgotLastName() throws InterruptedException {
        driver.findElement(By.id("member_firstname")).sendKeys("Maria");
        driver.findElement(By.id("member_lastname")).clear();

    }

    @Then("I should get a warning message that last name is required")
    public void iShouldGetAWarningMessageThatLastNameIsRequired() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[5]/div[2]/div/span/span")));

        String actualMessage = warningMessage.getText();
        String expectedMessage = "Last Name is required";

        assertEquals(expectedMessage, actualMessage);

        driver.close();

    }

    @And("I enter a password")
    public void iEnterAPassword() {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("taneshtaaesh");
    }

    @And("I confirm the password with same value")
    public void iConfirmThePasswordWithSameValue() {
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys("taneshtaaesh");
    }

    @And("I confirm the password with a different value")
    public void iConfirmThePasswordWithADifferentValue() {
        driver.findElement(By.cssSelector("#signupunlicenced_confirmpassword")).sendKeys("loolaie");
    }

    @And("I click the {string} button")
    public void iClickTheButton(String arg0) throws InterruptedException {
        driver.findElement(By.cssSelector("#signup_form > div.form-actions.noborder > input")).submit();

    Thread.sleep(3000);

    }

    @Then("I should get a warning message that password does not match")
    public void iShouldGetAWarningMessageThatPasswordDoesNotMatch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#signup_form > div:nth-child(9) > div > div.row > div:nth-child(2) > div > span > span")));

        String actualMessage = warningMessage.getText();
        String expectedMessage = "Password did not match";

        assertEquals(expectedMessage, actualMessage);

        driver.close();

    }

    @Then("I should get a warning message that terms and conditions are not accepted")
    public void iShouldGetAWarningMessageThatTermsAndConditionsAreNotAccepted() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > span > span")));

        String actualMessage = warningMessage.getText();
        String expectedMessage = "You must confirm that you have read and accepted our Terms and Conditions";

        assertEquals(expectedMessage, actualMessage);

        driver.close();

    }

}





