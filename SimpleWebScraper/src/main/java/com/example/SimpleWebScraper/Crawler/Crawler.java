package com.example.SimpleWebScraper.Crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Crawler {
    public static void main(String[] args) {
        String url = "https://books.toscrape.com";
        crawl(1, url, new ArrayList<String>());
    }
    private static void crawl(int level, String url, ArrayList<String> visitedUrls){
        if(level <= 5){
            Document doc = request(url, visitedUrls);

            if(doc != null){
                for(Element link : doc.select("a[href]")){
                    String linkUrl = link.attr("abs:href");
                    if(!visitedUrls.contains(linkUrl)){
                        crawl(level + 1, linkUrl, visitedUrls);
                    }
                }
            }
        }
    }
    private static Document request(String url, ArrayList<String> visited){
        try{
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if(con.response().statusCode() == 200){
                System.out.println("Link: "+ url);
                System.out.println("Title: "+ doc.title());
                visited.add(url);

                return doc;

            }
        }catch(IOException e){
            Logger.getGlobal().log(Level.WARNING, "Something not right");

        }

        return null;
    }
}
