package demo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.nio.file.Paths;

public class Login {
	private static Page page;
	private static Browser browser;
    private static Playwright playwright;

	@BeforeTest
	public void setup() {
	playwright = Playwright.create();
    browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
    BrowserContext context = browser.newContext();
    page = context.newPage();
	}

	
	@AfterTest
	public void teardown() {
	    if (page != null) {
	        page.close();
	    }
	    if (browser != null) {
	        browser.close();
	    }
	    if (playwright != null) {
	        playwright.close();
	    }
        System.out.println("Tear down hogya");

	}
	
	@Test
	public void successful_login(){
		  if (page == null) {
		        throw new RuntimeException("Page object is not initialized");
		    }
	
        //scenario 1: Successful login
		page.navigate("https://demo.haroldwaste.com/");
        page.fill("input[name='email']", "qa@julesai.com");
        page.fill("input[name='password']", "QaJULES2023!");

        page.click("button[type='submit']");

        page.waitForURL("https://demo.haroldwaste.com/purchases");
        System.out.println("Test Case 1: Successful Login - Passed");

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("successful_login.png")));
        Assert.assertTrue(true);
    }
	@Test
	public void invalid_email(){
	    
        //scenario 2: Invalid Email login attempt
		page.navigate("https://demo.haroldwaste.com/");
        page.fill("input[name='email']", "qa100@julesai.com");
        page.fill("input[name='password']", "QaJULES2023!");

        page.click("button[type='submit']");

        page.waitForSelector("text=Your email and/or password are incorrects");
        System.out.println("Scenario 2: Invalid Email Login - Passed");

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Invalid_email_login.png")));
        Assert.assertTrue(true);
	}
	@Test
	public void invalid_passwword(){
	    
        //scenario 2: Invalid Email login attempt
		page.navigate("https://demo.haroldwaste.com/");
        page.fill("input[name='email']", "qa@julesai.com");
        page.fill("input[name='password']", "QaJULES2024!");

        page.click("button[type='submit']");

        page.waitForSelector("text=Your email and/or password are incorrects");
        System.out.println("Scenario 3: Invalid Password Login - Passed");

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Invalid_password_login.png")));
        Assert.assertTrue(true);
	}

        public static void main(String[] args)
        {
        	org.testng.TestNG testng = new org.testng.TestNG();
            testng.setTestClasses(new Class[] { Login.class });
            testng.run();
        }
}
