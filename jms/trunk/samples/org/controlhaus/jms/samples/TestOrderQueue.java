
package org.controlhaus.jms.samples;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.junit.ControlTestCase;


public class TestOrderQueue extends ControlTestCase
{
    @Control OrderQueue dorders;
    
    public TestOrderQueue(String name) throws Exception
    {
        super(name);
    }
    public void testQueue() throws Exception
    {
        OrderQueue orders = (OrderQueue)instantiateControl("org.controlhaus.jms.samples.OrderQueueBean");
        OrderQueue.Order order = new OrderQueue.Order(5,new String[] { "hair spray"});
        orders.submitOrder(order,"today");
    }
}
