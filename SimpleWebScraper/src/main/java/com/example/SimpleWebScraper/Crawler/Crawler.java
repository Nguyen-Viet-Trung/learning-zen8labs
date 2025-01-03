package com.example.SimpleWebScraper.Crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
    public static void main(String[] args) {
        String url = "https://books.toscrape.com";
        List<Product> products = new ArrayList<>();
        crawl(1, url, new HashSet<>(), products);
        
        // Print collected products
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void crawl(int level, String url, HashSet<String> visitedUrls, List<Product> products) {
        if (level <= 5 && visitedUrls.size() <= 10) {
            Document doc = request(url, visitedUrls);

            if (doc != null) {
                // Extract product details
                extractProducts(doc, products);

                // Find and crawl other links
                for (Element link : doc.select("a[href]")) {
                    String linkUrl = link.attr("abs:href");
                    if (!visitedUrls.contains(linkUrl)) {
                        crawl(level + 1, linkUrl, visitedUrls, products);
                    }
                }
            }
        }
    }

    private static Document request(String url, HashSet<String> visited) {
        try {
            Connection con = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                System.out.println("Link: " + url);
                visited.add(url);
                return doc;
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "Error connecting to URL: " + url, e);
        }
        return null;
    }

    private static void extractProducts(Document doc, List<Product> products) {
        // Modify selectors based on the actual website structure
        Elements productElements = doc.select(".product_pod"); // Example selector for products

        for (Element productElement : productElements) {
            String title = productElement.select("h3 a").attr("title");
            String price = productElement.select(".price_color").text();
            String imageUrl = productElement.select("img").attr("src");

            // Resolve relative image URLs
            if (!imageUrl.startsWith("http")) {
                imageUrl = doc.baseUri() + imageUrl;
            }

            // Add product to the list
            products.add(new Product(title, price, imageUrl));
        }
    }
}

// Product class for storing product details
class Product {
    private String title;
    private String price;
    private String imageUrl;

    public Product(String title, String price, String imageUrl) {
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}

