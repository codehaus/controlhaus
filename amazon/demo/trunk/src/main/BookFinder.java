
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.beehive.controls.api.bean.Control;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.xmlbeans.XMLBeansFault;
import org.controlhaus.amazon.AmazonClientControl;

import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupRequest;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemDocument.Item;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupDocument.ItemLookup;
import com.amazon.webservices.awseCommerceService.x20041019.ItemsDocument.Items;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 2, 2004
 */
public class BookFinder
{
    @Control AmazonClientControl amazon;

    private boolean receivedResponse;
    private ItemLookupResponseDocument response;
    
    private Properties properties;
    private String subId;
    
    public BookFinder() 
        throws Exception
    {
        properties = new Properties();
        properties.load(new FileInputStream("./subscriptionId.txt"));
        
        subId = properties.getProperty("subscriptionId");
        
        if (subId == null)
            throw new Exception("You must put in a subscription id into subscriptionId.txt");
    }

    public void findBook(String isbn) 
    {
        ItemLookupDocument doc = ItemLookupDocument.Factory.newInstance();
        ItemLookup lookup = doc.addNewItemLookup();
        lookup.setSubscriptionId(subId);
        
        ItemLookupRequest request = lookup.addNewRequest();
        request.addItemId(isbn);
        request.setIdType(ItemLookupRequest.IdType.ASIN);
        
        try
        {
            response = amazon.ItemLookup(doc);
            
            Items[] itemsArr = response.getItemLookupResponse().getItemsArray();
            
            if (itemsArr.length == 0)
                System.out.println("No book found for that ISBN!");
            
            Item[] items = itemsArr[0].getItemArray();
             
            if (items.length == 0)
                System.out.println("No book found for that ISBN!");
            
            for (int i = 0; i < items.length; i++)
            {
                System.out.println( "Found \"" + items[i].getItemAttributes().getTitle() +
                                    "\" by: " + 
                                    items[i].getItemAttributes().getAuthorArray()[0] );
            }
        }
        catch (IOException e)
        {
            System.out.println("Couldn't connect to the Amazon web service.");
        }
        catch (XFireFault e)
        {
            System.out.println("SOAP Fault!");
            System.out.println(((XMLBeansFault) e).getFault().xmlText());
        }

    }
}
