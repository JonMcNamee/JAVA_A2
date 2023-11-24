/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mcnameea2;

/**
 *
 * @author JonMc
 */
public class Book {
     //creating variables for object
    private String author;
    private String title;
    private String[] formats;
    private double[] prices;
    //default book constructor, null format and price fields
    public Book(String author, String title) {
        this.author = author;
        this.title = title;
        this.formats = null;
        this.prices = null;
    }
    //constructor with format and price fields
    public Book(String author, String title, String[] formats, double[] prices) {
        this.author = author;
        this.title = title;
        this.formats = formats;
        this.prices = prices;
    }
    public boolean isAvailableInFormat(String format) {
        if (formats == null || format == null) {
            return false;
        }

        for (String availableFormat : formats) {
            if (availableFormat.equals(format)) {
                return true;
            }
        }
        return false;
    }
    //function to check if book is for sale or not
    public boolean isForSale() {
        return prices != null && prices.length > 0;
    }
 @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(author).append(", ").append(title);

        if (isForSale()) {
            if (isAvailableInFormat("print")) {
                sb.append(", print price = ").append(getPriceForFormat("print"));
            }

            if (isAvailableInFormat("cd")) {
                sb.append(", cd price = ").append(getPriceForFormat("cd"));
            }

            if (isAvailableInFormat("online")) {
                sb.append(", online price = ").append(getPriceForFormat("online"));
            }
        } else {
            sb.append(" (not for sale)");
        }

        return sb.toString();
    }

    private double getPriceForFormat(String format) {
        if (formats != null && prices != null) {
            for (int i = 0; i < formats.length; i++) {
                if (formats[i].equals(format)) {
                    return prices[i];
                }
            }
        }
        return 0.0; //returns 0 if no price listed
    }
    
    public static Book parseCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length < 2) {
            return null;
        }
        String author = parts[0].trim();
        String title = parts[1].trim();
        String[] formats = null;
        double[] prices = null;

        if (parts.length > 2) {
            // Splits the third column by ':' to get formats
            String[] formatStrings = parts[2].split(":");
            formats = new String[formatStrings.length];
            System.arraycopy(formatStrings, 0, formats, 0, formatStrings.length);

            if (parts.length > 3) {
                // Splits the fourth column by ':' to get prices
                String[] priceStrings = parts[3].split(":");
                prices = new double[priceStrings.length];
                for (int i = 0; i < priceStrings.length; i++) {
                    prices[i] = Double.parseDouble(priceStrings[i].trim());
                }
            }
        }
        return new Book(author, title, formats, prices);
    }
}