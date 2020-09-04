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

//public class Jenkins {
public class TestClass {

	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	PageClass pageClass;

	@Parameters({ "Port" })
	@BeforeClass
	public void initiateDriver(String Port) throws MalformedURLException {
		if (Port.equalsIgnoreCase("9001")) {
			// driver = new RemoteWebDriver(new URL("http:10.222.16.64:4444/wd/hub"),
			// DesiredCapabilities.chrome());
			driver = new RemoteWebDriver(new URL("http:174.100.144.120:4444/wd/hub"), DesiredCapabilities.chrome());
			driver.manage().window().maximize();
		} else if (Port.equalsIgnoreCase("9002")) {
			// driver = new RemoteWebDriver(new URL("http:10.222.16.64:4444/wd/hub"),
			// DesiredCapabilities.firefox());
			driver = new RemoteWebDriver(new URL("http:174.100.144.120:4444/wd/hub"), DesiredCapabilities.firefox());
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

//----------------------------------------테스트 시작

	// 테스트1

	
	@Parameters("browser")
	@Test
	public void GNB(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("todayNot")).click();
		driver.findElement(By.linkText("닫기")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("BEST")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("BRANDS")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("STORY")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("DEAL")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("EVENT")).click();
	}

	// 테스트2

	@Parameters("browser")

	@Test
	public void login(String browser) {
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
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그아웃")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.switchTo().alert().accept();
	}

	// 테스트3

	@Parameters("browser")

	@Test
	public void loginkakao(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
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
		driver.findElement(By.id("id_email_2")).sendKeys("yjs0104@gmail.com");
		driver.findElement(By.id("id_password_3")).sendKeys("mjjs1381403");
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

	// 네이버는 캡챠로인해 중단
	// 테스트4

	/*
	 * @Parameters("browser")
	 * 
	 * @Test public void loginnaver(String browser) {
	 * driver.get("https://chicor.com/main"); driver.manage().window().setSize(new
	 * Dimension(1516, 737)); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); driver.findElement(By.id("todayNot")).click();
	 * driver.findElement(By.linkText("닫기")).click();
	 * driver.findElement(By.linkText("로그인")).click();
	 * driver.findElement(By.cssSelector(".sp-sub-ico-naver")).click(); { WebElement
	 * element = driver.findElement(By.id("id")); Actions builder = new
	 * Actions(driver); builder.moveToElement(element).clickAndHold().perform(); } {
	 * WebElement element = driver.findElement(By.id("id")); Actions builder = new
	 * Actions(driver); builder.moveToElement(element).perform(); } { WebElement
	 * element = driver.findElement(By.id("id")); Actions builder = new
	 * Actions(driver); builder.moveToElement(element).release().perform(); }
	 * driver.findElement(By.id("id")).click();
	 * driver.findElement(By.id("id")).sendKeys("ID");
	 * driver.findElement(By.id("pw")).click();
	 * driver.findElement(By.id("pw")).sendKeys("PW");
	 * driver.findElement(By.id("log.login")).click();
	 * driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	 * driver.findElement(By.linkText("로그아웃")).click();
	 * driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	 * driver.switchTo().alert().accept(); }
	 */

	// 테스트5

	@Parameters("browser")

	@Test
	public void mypage(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.linkText("로그인")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		{
			WebElement element = driver.findElement(By.linkText("로그인"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.id("lginId")).click();
		driver.findElement(By.id("lginId")).sendKeys("820104");
		driver.findElement(By.id("lginPw")).click();
		driver.findElement(By.id("lginPw")).sendKeys("2359145js>");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".btn-login > .btn")).click();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@id=\'container\']/header/div/div/ul[2]/li[2]/a")).click();
		driver.findElement(By.linkText("마이페이지")).click();
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

	// 테스트6

	@Parameters("browser")

	@Test
	public void categoryskin(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.cssSelector(".menu-item:nth-child(1) .gra-text")).click();
		driver.findElement(By.linkText("페이셜케어")).click();
		driver.findElement(By.linkText("스킨/토너")).click();
		driver.findElement(By.linkText("로션")).click();
		driver.findElement(By.linkText("에센스/세럼")).click();
		driver.findElement(By.linkText("크림/젤/밤")).click();
		driver.findElement(By.linkText("아이케어")).click();
		driver.findElement(By.linkText("오일")).click();
		driver.findElement(By.linkText("미스트")).click();
		driver.findElement(By.linkText("세트")).click();
		driver.findElement(By.linkText("클렌징")).click();
		driver.findElement(By.linkText("클렌징워터/오일")).click();
		driver.findElement(By.linkText("클렌징젤/폼/솝")).click();
		driver.findElement(By.linkText("클렌징밀크/크림/밤")).click();
		driver.findElement(By.linkText("아이/립 리무버")).click();
		driver.findElement(By.linkText("클렌징티슈/패드")).click();
		driver.findElement(By.linkText("스크럽/필링")).click();
		driver.findElement(By.linkText("클렌징/마사지기기")).click();
		driver.findElement(By.linkText("마스크/팩")).click();
		driver.findElement(By.linkText("시트마스크")).click();
		driver.findElement(By.linkText("워시오프팩")).click();
		driver.findElement(By.linkText("슬리핑팩")).click();
		driver.findElement(By.linkText("부분 패치")).click();
		driver.findElement(By.linkText("선케어")).click();
		driver.findElement(By.linkText("선크림")).click();
		driver.findElement(By.linkText("선쿠션")).click();
		driver.findElement(By.linkText("선스틱/스프레이")).click();
	}

	// 테스트7

	@Parameters("browser")

	@Test
	public void categorymakeup(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.cssSelector(".menu-item:nth-child(2) .gra-text")).click();
		driver.findElement(By.linkText("베이스메이크업")).click();
		driver.findElement(By.linkText("프라이머/베이스")).click();
		driver.findElement(By.linkText("BB/CC")).click();
		driver.findElement(By.linkText("파운데이션")).click();
		driver.findElement(By.linkText("쿠션")).click();
		driver.findElement(By.linkText("컨실러")).click();
		driver.findElement(By.linkText("파우더")).click();
		driver.findElement(By.linkText("메이크업픽서")).click();
		driver.findElement(By.linkText("립메이크업")).click();
		driver.findElement(By.linkText("립스틱")).click();
		driver.findElement(By.linkText("립틴트/락커")).click();
		driver.findElement(By.linkText("립글로스")).click();
		driver.findElement(By.linkText("립펜슬/립라이너")).click();
		driver.findElement(By.linkText("립에센스/립밤")).click();
		driver.findElement(By.linkText("아이메이크업")).click();
		driver.findElement(By.linkText("아이라이너")).click();
		driver.findElement(By.linkText("아이브로우")).click();
		driver.findElement(By.linkText("아이섀도우/팔레트")).click();
		driver.findElement(By.linkText("마스카라")).click();
		driver.findElement(By.linkText("아이프라이머")).click();
		driver.findElement(By.linkText("아이래쉬/속눈썹영양제")).click();
		driver.findElement(By.linkText("블러셔/하이라이터/쉐딩")).click();
		driver.findElement(By.linkText("블러셔/치크")).click();
		driver.findElement(By.linkText("하이라이터")).click();
		driver.findElement(By.linkText("쉐딩/컨투어링")).click();
		driver.findElement(By.linkText("네일케어")).click();
		driver.findElement(By.linkText("네일 폴리쉬")).click();
		driver.findElement(By.linkText("젤 네일 폴리쉬")).click();
		driver.findElement(By.linkText("베이스/탑코트")).click();
		driver.findElement(By.linkText("네일팁/스티커")).click();
		driver.findElement(By.linkText("리무버/영양제")).click();
	}

	// 테스트8

	@Parameters("browser")

	@Test
	public void categorymanscare(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.cssSelector(".menu-item:nth-child(3) .gra-text")).click();
		driver.findElement(By.linkText("쉐이빙/클렌징")).click();
		driver.findElement(By.linkText("폼/젤/크림")).click();
		driver.findElement(By.linkText("애프터쉐이브")).click();
		driver.findElement(By.linkText("올인원")).click();
		driver.findElement(By.linkText("페이셜케어")).click();
		driver.findElement(By.linkText("스킨/토너")).click();
		driver.findElement(By.linkText("로션/크림/올인원")).click();
		driver.findElement(By.linkText("에센스/세럼")).click();
		driver.findElement(By.linkText("마스크/팩")).click();
		driver.findElement(By.linkText("선케어")).click();
		driver.findElement(By.linkText("세트")).click();
		driver.findElement(By.xpath("(//a[contains(text(),\'메이크업\')])[9]")).click();
		driver.findElement(By.linkText("베이스/BB/CC")).click();
		driver.findElement(By.linkText("립밤/립틴트")).click();
	}

	// 테스트8

	@Parameters("browser")

	@Test
	public void categorybodycare(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.cssSelector(".menu-item:nth-child(4) .gra-text")).click();
		driver.findElement(By.linkText("바디워시")).click();
		driver.findElement(By.linkText("바디클렌저/비누")).click();
		driver.findElement(By.linkText("바디스크럽")).click();
		driver.findElement(By.linkText("입욕제")).click();
		driver.findElement(By.linkText("여성청결제")).click();
		driver.findElement(By.linkText("바디보습")).click();
		driver.findElement(By.linkText("바디로션")).click();
		driver.findElement(By.linkText("바디크림/젤/밤")).click();
		driver.findElement(By.linkText("바디오일/에센스")).click();
		driver.findElement(By.linkText("바디미스트")).click();
		driver.findElement(By.linkText("핸드/풋")).click();
		driver.findElement(By.linkText("핸드워시")).click();
		driver.findElement(By.linkText("핸드크림/밤")).click();
		driver.findElement(By.linkText("세니타이저")).click();
		driver.findElement(By.linkText("풋크림")).click();
		driver.findElement(By.linkText("발관련용품")).click();
		driver.findElement(By.linkText("기능성 바디")).click();
		driver.findElement(By.linkText("슬리밍/탄력")).click();
		driver.findElement(By.linkText("임산부/튼살")).click();
		driver.findElement(By.linkText("데오드란트/제모")).click();
		driver.findElement(By.linkText("스프레이")).click();
		driver.findElement(By.linkText("스틱/롤온")).click();
		driver.findElement(By.linkText("제모용품")).click();
		driver.findElement(By.linkText("베이비/키즈")).click();
		driver.findElement(By.linkText("워시")).click();
		driver.findElement(By.linkText("로션/크림")).click();
		driver.findElement(By.linkText("오일/파우더")).click();
		driver.findElement(By.linkText("선케어")).click();
		driver.findElement(By.linkText("네일/마스크팩")).click();
		driver.findElement(By.linkText("세트")).click();
		driver.findElement(By.linkText("덴탈케어")).click();
		driver.findElement(By.linkText("치약")).click();
		driver.findElement(By.linkText("칫솔")).click();
		driver.findElement(By.linkText("가글용품/구취제거제")).click();
		driver.findElement(By.linkText("치아미백제/치실")).click();
		driver.findElement(By.linkText("세트")).click();
	}

	// 테스트9

	@Parameters("browser")
	@Test
	public void categoryperfume(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.cssSelector(".menu-item:nth-child(5) .gra-text")).click();
		driver.findElement(By.xpath("(//a[contains(text(),\'향수\')])[6]")).click();
		driver.findElement(By.linkText("여성향수")).click();
		driver.findElement(By.linkText("남성향수")).click();
		driver.findElement(By.linkText("스틱/롤온")).click();
		driver.findElement(By.linkText("향초/디퓨저/방향제")).click();
	}

	// 테스트10

	@Parameters("browser")
	@Test
	public void categoryhaircare(String browser) {
		driver.get("https://chicor.com/main");
		driver.manage().window().setSize(new Dimension(1516, 737));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.id("todayNot")).click();
		// driver.findElement(By.linkText("닫기")).click();
		driver.findElement(By.cssSelector(".menu-item:nth-child(6) .gra-text")).click();
		driver.findElement(By.linkText("샴푸/린스/트리트먼트")).click();
		driver.findElement(By.linkText("트리트먼트/헤어팩")).click();
		driver.findElement(By.linkText("세트")).click();
		driver.findElement(By.linkText("염모제")).click();
		driver.findElement(By.linkText("염색")).click();
		driver.findElement(By.linkText("헤어스타일")).click();
		driver.findElement(By.linkText("드라이샴푸")).click();
		driver.findElement(By.linkText("헤어에센스/오일")).click();
		driver.findElement(By.linkText("헤어젤/스프레이/무스")).click();
		driver.findElement(By.linkText("헤어왁스/포마드")).click();
		driver.findElement(By.linkText("헤어미스트")).click();
		driver.findElement(By.linkText("헤어기기")).click();
		driver.findElement(By.linkText("드라이기")).click();
		driver.findElement(By.linkText("헤어 아이론")).click();
	}

	// 테스트11
	/*
	 * @Parameters("browser")
	 * 
	 * @Test public void categorybeauty(String browser) {
	 * driver.get("https://chicor.com/main"); driver.manage().window().setSize(new
	 * Dimension(1516, 737)); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); driver.findElement(By.id("todayNot")).click();
	 * driver.findElement(By.linkText("닫기")).click();
	 * driver.findElement(By.cssSelector(".menu-item:nth-child(6) .gra-text")).click
	 * (); driver.findElement(By.linkText("샴푸/린스/트리트먼트")).click();
	 */

}