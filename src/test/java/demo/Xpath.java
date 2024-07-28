package demo;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static org.testng.Assert.assertEquals;
import com.microsoft.playwright.Locator;

public class Xpath {

		 public static void main(String[] args) {
		        
		        // Initialize Playwright
		        try (Playwright playwright = Playwright.create()) {
		            // Launch browser
		            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		            // Create a new page
		            Page page = browser.newPage();

		            // Navigate to the URL
		            page.navigate("https://selectorshub.com/iframe-in-shadow-dom/");

		            // Locate the shadowdom element having id as pizza
		            Locator pizzaInputLocator = page.locator("#userName #pizza");
		            
		            // Enter the desired value
		            pizzaInputLocator.fill("Raghav");
		            
		            // Fetch the value of the place holder
		            String placeholderValue = pizzaInputLocator.getAttribute("placeholder");
		            
		            System.out.println(placeholderValue);
		            
		            assertEquals(placeholderValue, "Enter pizza name");
		            
		            page.locator("#userName #pizza").fill("Test Message");
		            		            
		            browser.close();
		        }
		        catch (Exception e) {
		            // Handle exceptions here
		            System.err.println("An error occurred: " + e.getMessage());
		            e.printStackTrace();
		        }
		    }
		
	}
