package ca.uwaterloo.cs.webscraping;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class WebscrapingClient {

    public HtmlPage getPage(String url) {
        //food service page URL
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //disable css and java script
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);

        HtmlPage page = null;

        try {
            //get html page
            page = webClient.getPage(url);
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            //close connection and release the resource
            webClient.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }

        return page;

    }


}
