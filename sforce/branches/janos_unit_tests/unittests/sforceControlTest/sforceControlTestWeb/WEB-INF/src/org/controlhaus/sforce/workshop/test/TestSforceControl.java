package org.controlhaus.sforce.workshop.test; 

import com.bea.wlw.runtime.core.dispatcher.WlwProxySupport;
import com.sforce.soap.enterprise.DeleteResult;
import com.sforce.soap.enterprise.DescribeGlobalResult;
import com.sforce.soap.enterprise.DescribeSObjectResult;
import com.sforce.soap.enterprise.Field;
import com.sforce.soap.enterprise.GetUserInfoResult;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Account;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.ServletTestCase;
import org.apache.log4j.Logger;
import weblogic.jws.WlwProxy;

public class TestSforceControl extends ServletTestCase {
   
    private org.controlhaus.sforce.sforce3_0Enterprise sforceWrapperCtrl;
    
    /**
	 * 
	 */
	public TestSforceControl() {
		super();		
	}
	
	public TestSforceControl(String name)
	{
		super(name);
	}

	public static Test suite()
	{
		return new TestSuite(TestSforceControl.class);
	}

	public void setUp() throws Exception
	{
        System.out.println("-------------------------- serverside - setUp: "+this.hashCode());		
        if(sforceWrapperCtrl==null){
            Class controlClass = Class.forName("org.controlhaus.sforce.sforce3_0Enterprise");                    
            sforceWrapperCtrl = (org.controlhaus.sforce.sforce3_0Enterprise)WlwProxy.create(controlClass, request.getOriginalRequest());
            ((WlwProxySupport)sforceWrapperCtrl).setConversationalControlSupport(true);        
            System.out.println("login...");
            LoginResult result = sforceWrapperCtrl.login("matyix@yahoo.com","sforce");        
            System.out.println("ok.");                        
        }                
	}
    
    public void testCreateAndUpdate() throws Exception
    {            
        Account acct = Account.Factory.newInstance();
        acct.setName("Test Account");
        acct.setType("Customer");
        acct.setIndustry("Technology");
        acct.setBillingCity("Boise");
        acct.setBillingState("Idaho");                
        SaveResult saveResult = sforceWrapperCtrl.createOne(acct);
        assertTrue("Error creating account!",saveResult.getSuccess());        
        
        String sId = saveResult.getId();
        System.out.println("sId : "+sId);
        
        acct = (Account)sforceWrapperCtrl.retrieve("Id, Name","Account",sId);
        acct.setName("Updated Test Account");
        saveResult = sforceWrapperCtrl.updateOne(acct);
        assertTrue("Error updating account!",saveResult.getSuccess());                                
    }        
    
    public void testCreateAndDelete() throws Exception
    {            
        Account acct = Account.Factory.newInstance();
        acct.setName("Test Account");
        acct.setType("Customer");
        acct.setIndustry("Technology");
        acct.setBillingCity("Boise");
        acct.setBillingState("Idaho");                
        SaveResult saveResult = sforceWrapperCtrl.createOne(acct);
        assertTrue("Error creating account!",saveResult.getSuccess());        
        
        String sId = saveResult.getId();
        System.out.println("sId : "+sId);
        
        DeleteResult result = sforceWrapperCtrl.deleteOne(sId);
        assertTrue("Error deleting account!",result.getSuccess());                                
    }        
        
    public void testGetUserInfo(){
        GetUserInfoResult result = sforceWrapperCtrl.getUserInfo();
        System.out.println("user email: "+result.getUserEmail());                    
        assertTrue("Error : Incorrect user email!",result.getUserEmail().equalsIgnoreCase("matyix@yahoo.com"));                                
    }
    
    public void testDescribeGlobal(){
        DescribeGlobalResult result = sforceWrapperCtrl.describeGlobal();
        String[] types = result.getTypesArray();
        boolean found = false;
        for(int i=0;i<types.length;i++){
            if(types[i].equals("Account")){
                found = true;
                break;
            }
        }        
        assertTrue("Error : Account type not found!",found);                                
    }
    
    public void testDescribeSObject(){
        DescribeSObjectResult result = sforceWrapperCtrl.describeSObject("Account");        
        Field[] fields = result.getFieldsArray();
        boolean found = false;
        for(int i=0;i<fields.length;i++){
            if(fields[i].getName().equals("Name")){
                found = true;
                break;
            }
        }        
        assertTrue("Error : Account field 'Name' not found!",found);                                
    }
        
}
