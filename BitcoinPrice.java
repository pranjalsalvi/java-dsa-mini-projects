import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitcoinPrice {

    // Arrays to convert numbers into words
    static String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen",
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    static String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"};

    // Function to convert number into words (Indian number system)
    public static String convertToWords(int n) {

        // Base case
        if (n == 0) return "Zero";

        // 1–19
        if (n < 20) return units[n];

        // 20–99
        if (n < 100)
            return tens[n / 10] + " " + units[n % 10];

        // 100–999
        if (n < 1000)
            return units[n / 100] + " Hundred " + convertToWords(n % 100);

        // 1,000–99,999
        if (n < 100000)
            return convertToWords(n / 1000) + " Thousand " + convertToWords(n % 1000);

        // 1,00,000–99,99,999
        if (n < 10000000)
            return convertToWords(n / 100000) + " Lakh " + convertToWords(n % 100000);

        // Crores
        return convertToWords(n / 10000000) + " Crore " + convertToWords(n % 10000000);
    }

    public static void main(String[] args) {

        try {
            // Step 1: API URL for Bitcoin price
            URL url = new URL("https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd,inr,eur");

            // Step 2: Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Step 3: Read response from API
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            String line;
            StringBuilder response = new StringBuilder();

            // Read full JSON response
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Step 4: Extract INR value manually from JSON string
            String json = response.toString();

            // Find "inr" value in JSON
            int start = json.indexOf("\"inr\":") + 6;
            int end = json.indexOf(",", start);

            // If INR is last field
            if (end == -1) {
                end = json.indexOf("}", start);
            }

            // Convert extracted value to integer
            int inrValue = (int) Double.parseDouble(json.substring(start, end));

            // Step 5: Print result
            System.out.println("Bitcoin Price in INR: " + inrValue);

            // Convert to words
            String words = convertToWords(inrValue);
            System.out.println("In Words: " + words);

        } catch (Exception e) {
            // Handle errors (network issues, parsing errors, etc.)
            System.out.println("Error fetching Bitcoin price");
            e.printStackTrace();
        }
    }
}