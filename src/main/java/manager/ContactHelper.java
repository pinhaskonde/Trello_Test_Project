package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void openFormContact() {
        click(By.xpath("//a[.='ADD']"));


    }

    public void saveContact() {
        click(By.xpath("//button/b"));
    }

    public void fillFormContact(Contact contact) {
        type(By.cssSelector("input[placeholder$='Name']:first-child"), contact.getName());
        type(By.cssSelector("input[placeholder$='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder$='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder$='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder$='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder$='description']"), contact.getDescription());

    }

    public boolean isContactAdded(String phone) {
        List<WebElement> contacts = wd.findElements(By.xpath("//h3"));
        for (WebElement el : contacts) {
            if (el.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }
}
