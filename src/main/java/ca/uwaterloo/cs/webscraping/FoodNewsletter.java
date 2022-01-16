package ca.uwaterloo.cs.webscraping;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public class FoodNewsletter
{
    public static void main( String[] args )
    {
        //get page
        WebscrapingClient webscrapingClient = new WebscrapingClient();
        HtmlPage page = webscrapingClient.getPage(FoodNewsletterConstants.FOOD_MENU_LINK);

        //extract menu from the page
        ExtractData extractData  = new ExtractData();
        List<String> menus = extractData.getMenus(page);

        //send newsletter to the subscriber
        //TODO this info should be from subscriber page
        List<String> recipients = new ArrayList<>();
        recipients.add("michaelymq@gmail.com");
        recipients.add("tomsmith@gmail.com");
        recipients.add("test1@gmail.com");
        recipients.add("test2@gmail.com");
        EmailUtil emailUtil =  new EmailUtil();
        emailUtil.sendNewsletter(recipients,menus);

        System.out.println("Newsletter has been set out successfully");
    }
}
