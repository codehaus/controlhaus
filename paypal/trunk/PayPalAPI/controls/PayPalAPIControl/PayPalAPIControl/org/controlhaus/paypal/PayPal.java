package org.controlhaus.paypal; 

import com.bea.control.Control;

public interface PayPal extends Control
{ 

    void billUser();

    void refundTransaction();

    void transactionSearch();

    void getTransactionDetails();
} 
