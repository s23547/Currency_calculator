
import org.w3c.dom.*; 
import javax.xml.parsers.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.xml.sax.SAXException;

public class calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            File xmlFile = new File("eurofxref-daily.xml"); //file load
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder(); 
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            System.out.println("");
            System.out.println("CURRENCY CALCULATOR");
            NodeList list = document.getElementsByTagName("Cube");
            System.out.println("----------------------");
            
            
            //Asking for amount of euro
            System.out.println("Enter amount of EURO: ");
            float euro_amount=scanner.nextInt();
            if(euro_amount==0 || euro_amount<=0) {
                System.out.println("ERROR: INVALID VALUE");
                System.exit(0);
                
            }
            System.out.println("");

            //What currency do you choose?
            System.out.print("Choose currency exchange: ");
            
            String given_option = scanner.next().toUpperCase();
            System.out.println("");

            float euro_rate = 0;
            
            for (int x = 0; x < list.getLength(); x++) {
                Node new_node1 = list.item(x);
                if(new_node1.getNodeType() == Node.ELEMENT_NODE) {
                    Element new_element1 = (Element) new_node1;

                    if(new_element1.getAttribute("currency").equals(given_option)) { 
                        euro_rate = Float.parseFloat(new_element1.getAttribute("rate"));
                    }
                }
            }
            
            
            if(euro_rate==0 || euro_rate<0) {
                System.out.println("NO CURRENCY LIKE ENTERED");
            } 
            else {
                float final_exchange = euro_amount*euro_rate;
                System.out.printf("Rate: " +"%2.2f\n",euro_rate);
                System.out.printf("Amount: "+"%2.2f\n",final_exchange);
            }
        } 
        
        catch(IOException | ParserConfigurationException | SAXException | InputMismatchException e) {
            System.out.println("ERROR: INVALID VALUE");
        }

        finally {
            scanner.close();
        }
    }
}