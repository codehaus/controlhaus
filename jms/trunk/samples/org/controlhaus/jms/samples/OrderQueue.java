package org.controlhaus.jms.samples;

import org.controlhaus.jms.JMSControl;
import org.apache.beehive.controls.api.bean.ControlExtension;


@ControlExtension
@JMSControl.Destination(sendJndiName="jms.SimpleJmsQ",jndiConnectionFactory="weblogic.jws.jms.QueueConnectionFactory")
public interface OrderQueue extends JMSControl
{
	public class Order implements java.io.Serializable
	{
	    /**
         * Comment for <code>serialVersionUID</code>
         */
        private static final long serialVersionUID = 8951994251593378324L;
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
    public javax.jms.Message submitOrder(@Body Order order,@Property(name="DeliverBy") String deliverBy);
}
