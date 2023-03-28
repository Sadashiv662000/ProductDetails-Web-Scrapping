package com.AdaptNxt;

import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Product_details {
  public static void main(String[] args) {
    String url = "https://www.quill.com/hanging-file-folders/cbl/4378.html";
    String csvFilePath = "product_details.csv";
    
    try {
      // Send HTTP request to the URL and get the HTML content
      Document doc = Jsoup.connect(url).get();
      
      // Get all product items from the HTML content
      Elements productItems = doc.select(".grid-tile");

      // Create a CSV file and write the product details to it
      FileWriter csvWriter = new FileWriter(csvFilePath);
      csvWriter.append("Product Name,Product Price,Item Number/SKU/Product Code,Model Number,Product Category,Product Description\n");

      for (Element productItem : productItems) {
        // Get the product details from each product item
        String productName = productItem.select(".product-title").text();
        String productPrice = productItem.select(".price").text();
        String itemNumber = productItem.select(".item-number").text();
        String modelNumber = productItem.select(".model-number").text();
        String productCategory = productItem.select(".breadcrumbs a:last-of-type").text();
        String productDescription = productItem.select(".description").text();

        // Write the product details to the CSV file
        csvWriter.append(productName + ",");
        csvWriter.append(productPrice + ",");
        csvWriter.append(itemNumber + ",");
        csvWriter.append(modelNumber + ",");
        csvWriter.append(productCategory + ",");
        csvWriter.append(productDescription + "\n");
      }

      csvWriter.close();
      System.out.println("Product details have been exported to " + csvFilePath);
    } catch (IOException e) {
      System.out.println("An error occurred while trying to scrape the product details: " + e.getMessage());
    }
  }
}

