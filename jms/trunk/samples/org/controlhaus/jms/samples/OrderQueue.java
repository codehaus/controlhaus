package org.controlhaus.jms.samples;

import org.controlhaus.jms.JMSControl;
import org.apache.beehive.controls.api.bean.ControlExtension;


@ControlExtension
@JMSControl.Destination(sendJndiName="queue.orders",jndiConnectionFactory="weblogic.jws.jms.QueueConnectionFactory")
public interface OrderQueue extends JMSControl
{
	public class Order implements java.io.Serializable
	{
	    public Order()
	    {
	        
	    }
	    public Order(int buyer,String[] list)
	    {
	        buyerId = buyer;
	        itemList = list;
	        
	    }
	    private int buyerId;
	    private String[] itemList;
	}
    public void submitOrder(@Body Order order,@Property(name="DeliverBy") String deliverBy);
}
