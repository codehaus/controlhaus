
package org.controlhaus.jms.samples;

import javax.jms.Message;
import javax.jms.Queue;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.junit.ControlTestCase;
import org.controlhaus.jms.samples.OrderQueueBean;
import org.controlhaus.jndi.JndiControlBean;


public class TestOrderQueue extends ControlTestCase
{    
    public TestOrderQueue(String name) throws Exception
    {
        super(name);
    }
    public void testQueue() throws Exception
    {
        // Programmatically instantiate order queue.
        OrderQueueBean orders = (OrderQueueBean)instantiateControl("org.controlhaus.jms.samples.OrderQueueBean");
        // Load up jndi info.
        String provider = System.getProperty("jndi.provider");
        if(provider != null)
        {
            orders.setJndiProviderURL(provider);
        }
        String factory = System.getProperty("jndi.factory");
        if(factory != null)
        {
            orders.setJndiContextFactory(factory);
        }
        // Submit an order.
        OrderQueue.Order order = new OrderQueue.Order(5,new String[] { "hair spray"});
        Message mess = orders.submitOrder(order,"today");
        javax.jms.QueueSession sess = (javax.jms.QueueSession)orders.getSession();
        sess.commit();
        // Read the queue using the same session/connection.
        orders.getConnection().start();
        javax.jms.QueueReceiver consumer = sess.createReceiver((Queue)orders.getDestination());
        Message qmess = consumer.receive(5000);
        consumer.close();
        assertNotNull(qmess);
        String by = qmess.getStringProperty("DeliverBy");
        assertNotNull(by);
        assertEquals(by,"today");
    }

}
