package stepDefinations;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;


public class ExcelAssignment2 {
    WebDriver driver;

    public void start_facebook() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\JavaWorkSpace\\ChromeDirver Download\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);

        System.out.println("\nChrome Browser Open and Facebook page opened");
        driver.manage().window().maximize();
   //     driver.get("http://www.facebook.com");

        ArrayList<String> username=readExcel(0);
        ArrayList<String> pwd=readExcel(1);

           for (int i = 0; i < username.size(); i++) {

            driver.get("http://www.facebook.com");
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("pass")).clear();

               System.out.println(username.get(i)+" Username is loging");

            driver.findElement(By.id("email")).sendKeys(username.get(i));
            driver.findElement(By.id("pass")).sendKeys(pwd.get(i));
            driver.findElement(By.id("loginbutton")).click();


            driver.findElement(By.xpath("//div [@id=\"userNavigationLabel\"]")).click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

            driver.findElement(By.xpath("//span [contains(text(),'Log Out')]")).click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

               System.out.println(" Logout ");
               driver.findElement(By.id("loginbutton")).click();


            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);


           }

           driver.close();
    }


    public ArrayList<String> readExcel(int colNum) throws IOException {

        final String excelPath = "./src/test/java/utilities/usersNdPwds.xlsx";

            FileInputStream excelFile = new FileInputStream(new File(excelPath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            ArrayList<String> list=new ArrayList<String>();

            while (rowIterator.hasNext()) {
                list.add(rowIterator.next().getCell(colNum).getStringCellValue());

            }
            return list;
           }

    public static void main(String[] args)throws IOException {
        ExcelAssignment2 e2=new ExcelAssignment2();
        e2.start_facebook();

    }
    }






