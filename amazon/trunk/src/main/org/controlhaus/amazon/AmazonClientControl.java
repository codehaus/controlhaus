package org.controlhaus.amazon;

import java.io.IOException;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.beehive.controls.api.events.EventSet;

import org.controlhaus.xfire.client.XFireClientControl;
import org.controlhaus.xfire.client.XFireClientControl.Asynchronous;
import org.controlhaus.xfire.client.XFireClientControl.ServiceUrl;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.client.ClientHandler;
import org.codehaus.xfire.xmlbeans.client.XMLBeansClientHandler;

import com.amazon.webservices.awseCommerceService.x20041019.TransactionLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ListSearchDocument;
import com.amazon.webservices.awseCommerceService.x20041019.HelpDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartGetDocument;
import com.amazon.webservices.awseCommerceService.x20041019.BrowseNodeLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ListSearchResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CustomerContentSearchResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SellerListingSearchResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemSearchResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemSearchDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SimilarityLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartAddDocument;
import com.amazon.webservices.awseCommerceService.x20041019.BrowseNodeLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.MultiOperationResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.TransactionLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SellerListingLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartClearResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SimilarityLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.HelpResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SellerListingLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartModifyDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SellerListingSearchDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SellerLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartCreateDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartClearDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartAddResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.SellerLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CustomerContentLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ListLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ListLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CustomerContentSearchDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartCreateResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartModifyResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CustomerContentLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.CartGetResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.MultiOperationDocument;

/**
 * Auto-generated client stub for AWSECommerceService.
 */
@ServiceUrl("http://soap.amazon.com/onca/soap?Service=AWSECommerceService")
@ControlExtension
public interface AmazonClientControl
	     extends XFireClientControl
{
    HelpResponseDocument Help( HelpDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginHelp( HelpDocument body );
    ItemSearchResponseDocument ItemSearch( ItemSearchDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginItemSearch( ItemSearchDocument body );
    ItemLookupResponseDocument ItemLookup( ItemLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginItemLookup( ItemLookupDocument body );
    BrowseNodeLookupResponseDocument BrowseNodeLookup( BrowseNodeLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginBrowseNodeLookup( BrowseNodeLookupDocument body );
    ListSearchResponseDocument ListSearch( ListSearchDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginListSearch( ListSearchDocument body );
    ListLookupResponseDocument ListLookup( ListLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginListLookup( ListLookupDocument body );
    CustomerContentSearchResponseDocument CustomerContentSearch( CustomerContentSearchDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCustomerContentSearch( CustomerContentSearchDocument body );
    CustomerContentLookupResponseDocument CustomerContentLookup( CustomerContentLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCustomerContentLookup( CustomerContentLookupDocument body );
    SimilarityLookupResponseDocument SimilarityLookup( SimilarityLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginSimilarityLookup( SimilarityLookupDocument body );
    SellerLookupResponseDocument SellerLookup( SellerLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginSellerLookup( SellerLookupDocument body );
    CartGetResponseDocument CartGet( CartGetDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCartGet( CartGetDocument body );
    CartCreateResponseDocument CartCreate( CartCreateDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCartCreate( CartCreateDocument body );
    CartAddResponseDocument CartAdd( CartAddDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCartAdd( CartAddDocument body );
    CartModifyResponseDocument CartModify( CartModifyDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCartModify( CartModifyDocument body );
    CartClearResponseDocument CartClear( CartClearDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginCartClear( CartClearDocument body );
    TransactionLookupResponseDocument TransactionLookup( TransactionLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginTransactionLookup( TransactionLookupDocument body );
    SellerListingSearchResponseDocument SellerListingSearch( SellerListingSearchDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginSellerListingSearch( SellerListingSearchDocument body );
    SellerListingLookupResponseDocument SellerListingLookup( SellerListingLookupDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginSellerListingLookup( SellerListingLookupDocument body );
    MultiOperationResponseDocument MultiOperation( MultiOperationDocument body )
        throws IOException, XFireFault;

    @Asynchronous
    void beginMultiOperation( MultiOperationDocument body );
}
