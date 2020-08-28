package com.chicor.docker;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Jenkins {
//public class TestClass {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	PageClass pageClass;

	@Parameters({ "Port" })
	@BeforeClass
	public void initiateDriver(String Port) throws MalformedURLException {
		if (Port.equalsIgnoreCase("9001")) {
			driver = new RemoteWebDriver(new URL("http:10.222.16.64:4444/wd/hub"), DesiredCapabilities.chrome());
			//driver = new RemoteWebDriver(new URL("http:174.100.144.120:4444/wd/hub"), DesiredCapabilities.chrome());
			driver.manage().window().maximize();
		} else if (Port.equalsIgnoreCase("9002")) {
			driver = new RemoteWebDriver(new URL("http:10.222.16.64:4444/wd/hub"), DesiredCapabilities.firefox());
			//driver = new RemoteWebDriver(new URL("http:174.100.144.120:4444/wd/hub"), DesiredCapabilities.firefox());
			driver.manage().window().maximize();
		}

		pageClass = new PageClass(driver);
		vars = new HashMap<String, Object>();

	}

	public String waitForWindow(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}
		return whNow.iterator().next();
	}

	@AfterClass
	public void quitDriver() {
		driver.quit();
	}

//----------------------------------------


	@Parameters("browser")
	@Test
	public void GNB(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("todayNot")).click();
		driver.findElement(By.linkText("닫기")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("BEST")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("BRANDS")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("STORY")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("DEAL")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("EVENT")).click();
	}


	
	@Parameters("browser")
	@Test
	public void login(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElement(By.id("todayNot")).click();
		//driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.linkText("로그인")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		{
			WebElement element = driver.findElement(By.linkText("로그인"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.id("lginId")).click();
		driver.findElement(By.id("lginId")).sendKeys("820104");
		driver.findElement(By.id("lginPw")).click();
		driver.findElement(By.id("lginPw")).sendKeys("2359145js>");
		driver.findElement(By.cssSelector(".btn-login > .btn")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그아웃")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();
	}

	

//카카오 알림이와서 잠시숨김 
	
	@Parameters("browser")
	@Test public void loginkakao(String browser) 
	{
		driver.get("https://chicor.com/main"); driver.manage().window().setSize(new Dimension(1516, 737)); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.findElement(By.id("todayNot")).click();
		driver.findElement(By.linkText("닫기")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그인")).click(); 
		{ 
			WebElement element = driver.findElement(By.cssSelector(".sp-sub-ico-kakao")); 
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform(); 
		}
		vars.put("window_handles", driver.getWindowHandles());
		driver.findElement(By.cssSelector(".sp-sub-ico-kakao")).click();
		vars.put("win5344", waitForWindow(2000)); 
		vars.put("root", driver.getWindowHandle());
		driver.switchTo().window(vars.get("win5344").toString());
		driver.findElement(By.id("id_email_2")).sendKeys("ID");
		driver.findElement(By.id("id_password_3")).sendKeys("PW");
		driver.findElement(By.cssSelector(".wrap_btn:nth-child(13) > .btn_confirm")).click(); 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.switchTo().window(vars.get("root").toString());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("마이페이지")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그아웃")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();
		}

//네이버는 캡챠로인해 중단
/*	
	@Parameters("browser")
	@Test public void loginnaver(String browser) 
	{
		driver.get("https://chicor.com/main"); driver.manage().window().setSize(new Dimension(1516, 737)); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		driver.findElement(By.id("todayNot")).click();
		driver.findElement(By.linkText("닫기")).click();
	    driver.findElement(By.linkText("로그인")).click();
	    driver.findElement(By.cssSelector(".sp-sub-ico-naver")).click();
	    {
	      WebElement element = driver.findElement(By.id("id"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).clickAndHold().perform();
	    }
	    {
	      WebElement element = driver.findElement(By.id("id"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.id("id"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).release().perform();
	    }
	    driver.findElement(By.id("id")).click();
	    driver.findElement(By.id("id")).sendKeys("ID");
	    driver.findElement(By.id("pw")).click();
	    driver.findElement(By.id("pw")).sendKeys("PW");
	    driver.findElement(By.id("log.login")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그아웃")).click();
		//assertThat(driver.switchTo().alert().getText(), is("로그아웃 하시겠습니까? "));
		//assertThat(driver.switchTo().alert().getText(), equals(false));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();
	  }	


	@Parameters("browser")
	@Test
	public void mypage(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("todayNot")).click();
		driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.linkText("로그인")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		{
			WebElement element = driver.findElement(By.linkText("로그인"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.id("lginId")).click();
		driver.findElement(By.id("lginId")).sendKeys("820104");
		driver.findElement(By.id("lginPw")).click();
		driver.findElement(By.id("lginPw")).sendKeys("2359145js>");
		driver.findElement(By.cssSelector(".btn-login > .btn")).click();		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);			
	    driver.findElement(By.xpath("//div[@id=\'container\']/header/div/div/ul[2]/li[2]/a")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("온라인 회원등급")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("오프라인 회원등급")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.findElement(By.linkText("배송지 관리")).click();
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    js.executeScript("window.scrollTo(0,241)");
	    driver.findElement(By.cssSelector(".btn-color1:nth-child(2)")).click();
	    driver.findElement(By.linkText("닫기")).click();
	    		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그아웃")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.switchTo().alert().accept();
	}
*/
	
}