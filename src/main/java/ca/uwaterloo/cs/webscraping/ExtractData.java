package ca.uwaterloo.cs.webscraping;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public class ExtractData {


    public List<String> getMenus(HtmlPage page){

        //get all the menus from the page
        List<HtmlElement> items = page.getByXPath("//li[@class='dm-menu-item']");;

        List<String> menus =  new ArrayList<>();
        for(HtmlElement htmlItem : items){
            menus.add(htmlItem.getTextContent().toString().trim());
        }
        return menus;
    }

}
