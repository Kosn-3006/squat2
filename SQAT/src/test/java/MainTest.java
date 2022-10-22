import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainTest {
    public WebDriver webDriver;
    public By usernameBy = By.name("phone");
    public By passwordBy = By.name("password");
    public By signinBy = By.xpath("//*[@id=\"kzp\"]/header[1]/div[2]/div/div/div/a");
    public By shopBy = By.xpath("//*[@id=\"kzp\"]/header[1]/div[2]/div/div/ul/li[7]/a");
    public By goBy = By.xpath("//*[@id=\"kzp\"]/div[3]/div/div[1]/div/div[2]/div[2]/button[1]");
    public By paymentsBy = By.xpath("//*[@id=\"kzp\"]/header[1]/div[2]/div/div/ul/li[2]/a");
    public By selectedBy = By.xpath("//*[@id=\"kzp\"]/div[3]/div/div[2]/div[1]");
    public By typeBy = By.xpath("//*[@id=\"operators\"]/div[4]");
    public By elementBy = By.xpath("//*[@id=\"kzp\"]/div[3]/section/div[2]/div/div/div/div[1]/div");
    public By senderAddressBy = By.id("senderAddress");
    public By senderIndexBy = By.id("senderIndex");
    public By flatBy = By.id("flat");
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
    public ExtentReports extent = new ExtentReports();
    ExtentTest test = extent.createTest("Post login Test", "Testing the login test case");

    @BeforeTest
    public void mainTest() throws InterruptedException, IOException {

        String FILE_NAME = "C:\\Users\\offic\\IdeaProjects\\SQAT\\testfiles\\ExcelTest.xlsx";
        XSSFWorkbook wk = new XSSFWorkbook(FILE_NAME);
        XSSFSheet sheet = wk.getSheetAt(0);
        XSSFCell phone = sheet.getRow(1).getCell(0);
        XSSFCell passwordCell = sheet.getRow(1).getCell(1);

        String login = String.valueOf(phone);
        String password = String.valueOf(passwordCell);

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        extent.attachReporter(htmlReporter);
        System.getProperty("webdriver.chrome.driver",("user.dir"));
        webDriver.navigate().to("https://post.kz/");
        test.info("Navigated to post.kz");
        webDriver.manage().window().maximize();
        test.pass("Window maximized");
        webDriver.findElement(signinBy).click();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        test.pass("Clicked to login button");
        webDriver.findElement(usernameBy).sendKeys(login);                            //"7028655538"
        webDriver.findElement(passwordBy).sendKeys(password, Keys.ENTER);   //"Kd30062019!"
        test.pass("Successfully Logged in");
        Thread.sleep(1000);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void loginTest(){}

    @Test
    public void onlineShop() throws InterruptedException {
        webDriver.findElement(shopBy).click();
        test.pass("Navigated to the Shop menu");
        Thread.sleep(1000);
        webDriver.findElement(goBy).click();
        test.pass("Successfully selected a shop");
        test.info("Test case completed");
        extent.flush();
    }

    @Test
    public void payments() throws InterruptedException {
        webDriver.findElement(paymentsBy).click();
        Thread.sleep(1000);
        test.pass("Navigated to the payment page");
        webDriver.findElement(selectedBy).click();
        Thread.sleep(1000);
        test.pass("Selected type of payment");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(typeBy).click();
        test.pass("Selected payment");
        test.info("Test case completed");
        extent.flush();
    }

    @Test
    public void hybridMail() throws InterruptedException{
        webDriver.findElement(elementBy).click();
        test.pass("Navigated to Hybrid Mail");
        webDriver.findElement(senderAddressBy).sendKeys("1");
        webDriver.findElement(senderIndexBy).sendKeys("1");
        webDriver.findElement(flatBy).sendKeys("1",Keys.TAB,Keys.ENTER);
        test.pass("Passed 1 step of filling mail");
        Thread.sleep(2000);
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.findElement(By.id("fio")).sendKeys("1");
        webDriver.findElement(By.id("flat")).sendKeys("1");
        webDriver.findElement(By.id("address")).sendKeys("1");
        webDriver.findElement(By.id("index")).sendKeys("123456");
        webDriver.findElement(By.id("email")).sendKeys("1",Keys.TAB,Keys.ENTER);
        test.pass("Passed 2 step of filling mail");
        test.info("Test case completed");
        extent.flush();
    }

}
