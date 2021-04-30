import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestClass {

    private WebDriver driver;

    @BeforeMethod
    public void openBrowser() {

        driver = Main.openBrowser();
    }

    @Test(enabled = true)
    public void verifyReturns() throws InterruptedException {
        WebElement searchBar = driver.findElement(By.name("searchVal"));
        searchBar.sendKeys("Jackets");
        searchBar.submit();

        WebElement firstItem = driver.findElement(By.xpath("(//div[@class='name'])[1]"));
        firstItem.click();

        for (String h1 : driver.getWindowHandles()) {
            driver.switchTo().window(h1);
        }

        Thread.sleep(2000);
        WebElement returns = driver.findElement(By.xpath("//a[@id='product-details-tab-tab-2']"));
        returns.click();
        System.out.println(returns.getText());
        Assert.assertTrue(returns.getText().contains("RETURNS"));
        System.out.println(driver.findElement(By.xpath("//div[@class='return-desc']")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='return-desc']")).getText().contains("15 days"));
    }

    @AfterMethod
    public void closeBrowser() {

        Main.closeBrowser(driver);
    }

}
