package TestCasesAutomation;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;	

public class CreateTaxProfile extends FileNotFoundException {

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		WebDriver driver;
		String directory = System.getProperty("user.dir");

		System.setProperty("webdriver.chrome.driver", directory + "\\target\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		Properties config = new Properties();
		FileInputStream fis = new FileInputStream(directory + "\\src\\test\\resources\\resources\\Config.properties");

		config.load(fis);

		driver.get("https://indigo-testing.shireburn.com/");

		// Peforming actions on web elements

		// WebElements
		WebElement uName = driver.findElement(By.xpath("//*[@id='txtUsername']"));
		WebElement pswd = driver.findElement(By.xpath("//*[@id='txtPassword']"));
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id='submit']"));

		String username = config.getProperty("UserName");
		uName.sendKeys(username);
		String password = config.getProperty("Password");
		pswd.sendKeys(password);
		loginBtn.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement TaxProfile = driver.findElement(By.xpath("//a/span[text()='Tax Profiles']"));
		TaxProfile.click();
		String filePath = config.getProperty("IndigoTestData");
		File file = new File(directory + filePath);

		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook Workbook = new XSSFWorkbook(inputStream);
		Sheet Sheet = Workbook.getSheet("Tax Profile");
		int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
		Cell cell;
		for (int i = 1; i < rowCount + 1; i++) {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		WebElement CreateTaxProfile = driver.findElement(By.xpath("//div[@id='insertButton' and @data-context=\"Tax Profile\"]"));
		CreateTaxProfile.click();
		WebElement TaxProfileCode = driver.findElement(By.xpath("//input[@data-uid='TaxProfile-Code']"));
		WebElement TaxProfileDesc = driver.findElement(By.xpath("//div[@data-uid='TaxProfile-Description']"));
		WebElement FSSType = driver.findElement(By.xpath("//div[@data-uid='TaxProfile-FssStatusType']"));
		

		Thread.sleep(2000);

			cell = Sheet.getRow(i).getCell(0);

			cell.setCellType(Cell.CELL_TYPE_STRING);

			TaxProfileCode.click();
			Thread.sleep(2000);
			TaxProfileCode.sendKeys(cell.getStringCellValue());
			
				TaxProfileCode.sendKeys(Keys.TAB);
				cell = Sheet.getRow(i).getCell(1);		
			WebElement TaxProfileDescbox = driver.findElement(By.xpath("//input[starts-with(@id, 'textboxeditor')]"));
			
			TaxProfileDescbox.sendKeys(cell.getStringCellValue());
			Thread.sleep(2000);
			
			TaxProfileDescbox.sendKeys(Keys.TAB);
			cell = Sheet.getRow(i).getCell(2);
			WebElement TaxProfileFSSType = driver.findElement(By.xpath("//div[starts-with(@id, 'dropdownlistContentdropdown')]"));
			
			String type = (cell.getStringCellValue());
			if (type.equalsIgnoreCase("FSS Main")) {
				TaxProfileFSSType.click();
				WebElement TaxProfileFSSMain = driver.findElement(By.xpath("//span[text()='FSS Main']"));
				TaxProfileFSSMain.click();
			}
			else {
				TaxProfileFSSType.click();
				WebElement TaxProfileFSSPart = driver.findElement(By.xpath("//span[text()='FSS Part Time']"));
				TaxProfileFSSPart.click();
			}
			Thread.sleep(2000);

			cell = Sheet.getRow(i).getCell(3);
			//driver.findElement(By.xpath("//h2")).click();
			
			//TaxProfileFSSType.sendKeys(Keys.TAB);
			WebElement TaxProfilecheckbox = driver.findElement(By.xpath("//div[@data-uid='TaxProfile-undefined']"));

			String checkbox = (cell.getStringCellValue());
			if (checkbox.equalsIgnoreCase("Yes")) {
			TaxProfilecheckbox.click();
			}
			Thread.sleep(2000);
			//driver.findElement(By.xpath("//h2")).click();
			WebElement SaveTaxProfile = driver.findElement(By.xpath("//div[@id='saveButton' and @data-context=\"Tax Profile\"]"));
			SaveTaxProfile.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			WebElement CancelTaxProfile = driver.findElement(By.xpath("//div[@id='cancelButton' and @data-context=\"Tax Profile\"]"));
			CancelTaxProfile.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement ViewTaxProfile = driver.findElement(By.xpath("//a[@id='formViewButton' and @data-context=\"Tax Profile\"]"));
			
			ViewTaxProfile.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement Closebtn = driver.findElement(By.xpath("//div[@data-uid='closeViewButton']"));
			Closebtn.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement EditTaxProfile = driver.findElement(By.xpath("//div[@id='editButton' and @data-context=\"Tax Profile\"]"));
			EditTaxProfile.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement TaxProfileInputbox=driver.findElement(By.xpath("//input[@data-uid='TaxProfile-Description']"));
			TaxProfileInputbox.clear();
			TaxProfileInputbox.sendKeys("UpdateDesc");
			//driver.findElement(By.xpath("//h2")).click();
			SaveTaxProfile.click();
			Thread.sleep(2000);
			WebElement Rates=driver.findElement(By.xpath("//div[@data-uid='Tax Profile-taxratebutton']"));
			Rates.click();
			Thread.sleep(1000);
			Sheet Sheet1 = Workbook.getSheet("Tax Rate");
			int rowCount1 = Sheet1.getLastRowNum() - Sheet1.getFirstRowNum();
			Cell cell1;
			for (int j = 1; j < rowCount1 + 1; j++) {
				cell1 = Sheet1.getRow(j).getCell(0);
				Cell cell2=Sheet.getRow(i).getCell(0);
				cell1.setCellType(Cell.CELL_TYPE_STRING);
				cell2.setCellType(Cell.CELL_TYPE_STRING);
				if(cell1.getStringCellValue().equalsIgnoreCase(cell2.getStringCellValue())) {
					
					cell1 = Sheet1.getRow(j).getCell(1);
					Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			WebElement CreateTaxRate = driver
					.findElement(By.xpath("//div[@id='insertButton' and @data-context=\"Tax Rate\"]"));
			CreateTaxRate.click();
			Thread.sleep(1000);
			WebElement TaxRateCode = driver.findElement(By.xpath("//input[@data-uid='TaxRate-Code']"));
			TaxRateCode.sendKeys(cell1.getStringCellValue());
			Thread.sleep(1000);
			WebElement RangeFrom = driver.findElement(By.xpath("//div[@data-uid='TaxRate-RangeFrom']"));
			driver.findElement(By.xpath("//DIV[@data-uid='TaxRate-RangeFrom']")).click();
			cell1 = Sheet1.getRow(j).getCell(2);
			Thread.sleep(1000);
			WebElement RangeFromInput = driver.findElement(By.xpath("//input[@data-uid='TaxRate-RangeFrom']"));
			RangeFromInput.sendKeys(String.valueOf(cell1.getNumericCellValue()));
			RangeFromInput.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			//WebElement RangeTo = driver.findElement(By.xpath("//div[@data-uid='TaxRate-RangeTo']"));
			cell1 = Sheet1.getRow(j).getCell(3);
			
			WebElement RangeToInput = driver.findElement(By.xpath("//input[@data-uid='TaxRate-RangeTo']"));
			
			RangeToInput.sendKeys(String.valueOf(cell1.getNumericCellValue()));
			RangeToInput.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			cell1 = Sheet1.getRow(j).getCell(4);
			
			WebElement TaxRatepercent = driver.findElement(By.xpath("//input[@data-uid='TaxRate-Rate']"));
			TaxRatepercent.sendKeys(String.valueOf(cell1.getNumericCellValue()));
			
			cell1 = Sheet1.getRow(j).getCell(5);
			TaxRatepercent.sendKeys(Keys.TAB);
			Thread.sleep(1000);
			WebElement Subtract = driver.findElement(By.xpath("//input[@data-uid='TaxRate-Subtract']"));
			Subtract.sendKeys(String.valueOf(cell1.getNumericCellValue()));
		
			cell1 = Sheet1.getRow(j).getCell(6);
			Thread.sleep(1000);
			if(cell1.getStringCellValue().equalsIgnoreCase("Y")) {
				Subtract.sendKeys(Keys.TAB);
			WebElement ShowasPT = driver.findElement(By.xpath("//div[starts-with(@id, 'customedit')]/div[1]"));
			ShowasPT.click();
			Thread.sleep(1000);
			}
			
			WebElement SaveTaxRate = driver.findElement(By.xpath("//div[@id='saveButton' and @data-context=\"Tax Rate\"]"));
			SaveTaxRate.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			WebElement CancelTaxRate = driver.findElement(By.xpath("//div[@id='cancelButton' and @data-context=\"Tax Rate\"]"));
			CancelTaxRate.click();
			Thread.sleep(2000);
				}
			}
			WebElement Taxprofilebtn = driver.findElement(By.xpath("//div[@data-uid='Tax Rate-taxprofilebutton']"));
			Taxprofilebtn.click();
			
			Thread.sleep(2000);
			
			WebElement DeleteTaxProfile = driver.findElement(By.xpath("//div[@id='deleteActionButton' and @data-context=\"Tax Profile\"]"));
			DeleteTaxProfile.click();
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement Deletebtn = driver.findElement(By.xpath("//button[text()='delete']"));
			Deletebtn.click();
			Thread.sleep(2000);
		
			}
		
		System.out.println();
		driver.quit();

		
		
}


}