/*   Copyright 2004 Salesforce.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package rpctypes;
/**
 * Generated class, do not edit.
 *
 * This stub class was generated by weblogic
 * webservice stub gen on Wed May 05 02:42:36 PDT 2004 */

public class Soap_Stub 
     extends weblogic.webservice.core.rpc.StubImpl 
     implements  rpctypes.Soap{

  public Soap_Stub( weblogic.webservice.Port _port ){
    super( _port, rpctypes.Soap.class );
  }

  /**
   * search 
   */

  public javax.xml.soap.SOAPElement search(rpctypes.Search parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.InvalidFieldFault, rpctypes.MalformedSearchFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "search", _args );
      return (javax.xml.soap.SOAPElement)_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.InvalidFieldFault e) {
      throw e;
    } catch (rpctypes.MalformedSearchFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * getUpdated 
   */

  public rpctypes.GetUpdatedResponse getUpdated(rpctypes.GetUpdated parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "getUpdated", _args );
      return (rpctypes.GetUpdatedResponse)_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for getUpdated 
   */

  public rpctypes.GetUpdatedResult getUpdated(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.GetUpdated _input = 
         new rpctypes.GetUpdated();

        _input.setSObjectType( sObjectType );
    _input.setStartDate( startDate );
    _input.setEndDate( endDate );
      
    rpctypes.GetUpdatedResponse _result = getUpdated( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * describeGlobal 
   */

  public rpctypes.DescribeGlobalResponse describeGlobal(rpctypes.DescribeGlobal parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "describeGlobal", _args );
      return (rpctypes.DescribeGlobalResponse)_result;
    
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for describeGlobal 
   */

  public rpctypes.DescribeGlobalResult describeGlobal(rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.DescribeGlobal _input = 
         new rpctypes.DescribeGlobal();

          
    rpctypes.DescribeGlobalResponse _result = describeGlobal( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * query 
   */

  public javax.xml.soap.SOAPElement query(rpctypes.Query parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions, rpctypes.QueryOptions QueryOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.InvalidFieldFault, rpctypes.MalformedQueryFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           _args.put( "QueryOptions", _wrap( QueryOptions ) );
           try{
      java.lang.Object _result = _invoke( "query", _args );
      return (javax.xml.soap.SOAPElement)_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.InvalidFieldFault e) {
      throw e;
    } catch (rpctypes.MalformedQueryFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * update 
   */

  public rpctypes.SaveResult[] update(javax.xml.soap.SOAPElement parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions, rpctypes.SaveOptions SaveOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           _args.put( "SaveOptions", _wrap( SaveOptions ) );
           try{
      java.lang.Object _result = _invoke( "update", _args );
      return (rpctypes.SaveResult[])_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * create 
   */

  public rpctypes.SaveResult[] create(javax.xml.soap.SOAPElement parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions, rpctypes.SaveOptions SaveOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           _args.put( "SaveOptions", _wrap( SaveOptions ) );
           try{
      java.lang.Object _result = _invoke( "create", _args );
      return (rpctypes.SaveResult[])_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * login 
   */

  public rpctypes.LoginResponse login(rpctypes.Login parameters, rpctypes.CallOptions CallOptions) 
       throws rpctypes.LoginFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "login", _args );
      return (rpctypes.LoginResponse)_result;
    
    } catch (rpctypes.LoginFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for login 
   */

  public rpctypes.LoginResult login(java.lang.String username, java.lang.String password, rpctypes.CallOptions CallOptions)
       throws rpctypes.LoginFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.Login _input = 
         new rpctypes.Login();

        _input.setUsername( username );
    _input.setPassword( password );
      
    rpctypes.LoginResponse _result = login( _input, CallOptions );

        return _result.getResult();
      }
  /**
   * queryMore 
   */

  public javax.xml.soap.SOAPElement queryMore(rpctypes.QueryMore parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions, rpctypes.QueryOptions QueryOptions) 
       throws rpctypes.InvalidQueryLocatorFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           _args.put( "QueryOptions", _wrap( QueryOptions ) );
           try{
      java.lang.Object _result = _invoke( "queryMore", _args );
      return (javax.xml.soap.SOAPElement)_result;
    
    } catch (rpctypes.InvalidQueryLocatorFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * retrieve 
   */

  public javax.xml.soap.SOAPElement retrieve(rpctypes.Retrieve parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions, rpctypes.QueryOptions QueryOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.InvalidFieldFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           _args.put( "QueryOptions", _wrap( QueryOptions ) );
           try{
      java.lang.Object _result = _invoke( "retrieve", _args );
      return (javax.xml.soap.SOAPElement)_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.InvalidFieldFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * setPassword 
   */

  public rpctypes.SetPasswordResponse setPassword(rpctypes.SetPassword parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.InvalidIdFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "setPassword", _args );
      return (rpctypes.SetPasswordResponse)_result;
    
    } catch (rpctypes.InvalidIdFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for setPassword 
   */

  public rpctypes.SetPasswordResult setPassword(java.lang.String userId, java.lang.String password, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.InvalidIdFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.SetPassword _input = 
         new rpctypes.SetPassword();

        _input.setUserId( userId );
    _input.setPassword( password );
      
    rpctypes.SetPasswordResponse _result = setPassword( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * getDeleted 
   */

  public rpctypes.GetDeletedResponse getDeleted(rpctypes.GetDeleted parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "getDeleted", _args );
      return (rpctypes.GetDeletedResponse)_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for getDeleted 
   */

  public rpctypes.GetDeletedResult getDeleted(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.GetDeleted _input = 
         new rpctypes.GetDeleted();

        _input.setSObjectType( sObjectType );
    _input.setStartDate( startDate );
    _input.setEndDate( endDate );
      
    rpctypes.GetDeletedResponse _result = getDeleted( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * resetPassword 
   */

  public rpctypes.ResetPasswordResponse resetPassword(rpctypes.ResetPassword parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.InvalidIdFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "resetPassword", _args );
      return (rpctypes.ResetPasswordResponse)_result;
    
    } catch (rpctypes.InvalidIdFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for resetPassword 
   */

  public rpctypes.ResetPasswordResult resetPassword(java.lang.String userId, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.InvalidIdFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.ResetPassword _input = 
         new rpctypes.ResetPassword();

        _input.setUserId( userId );
      
    rpctypes.ResetPasswordResponse _result = resetPassword( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * describeSObject 
   */

  public rpctypes.DescribeSObjectResponse describeSObject(rpctypes.DescribeSObject parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "describeSObject", _args );
      return (rpctypes.DescribeSObjectResponse)_result;
    
    } catch (rpctypes.InvalidSObjectFault e) {
      throw e;
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for describeSObject 
   */

  public rpctypes.DescribeSObjectResult describeSObject(java.lang.String sObjectType, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.InvalidSObjectFault, rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.DescribeSObject _input = 
         new rpctypes.DescribeSObject();

        _input.setSObjectType( sObjectType );
      
    rpctypes.DescribeSObjectResponse _result = describeSObject( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * delete 
   */

  public rpctypes.DeleteResult[] delete(java.lang.String[] parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "delete", _args );
      return (rpctypes.DeleteResult[])_result;
    
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * getUserInfo 
   */

  public rpctypes.GetUserInfoResponse getUserInfo(rpctypes.GetUserInfo parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "getUserInfo", _args );
      return (rpctypes.GetUserInfoResponse)_result;
    
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for getUserInfo 
   */

  public rpctypes.GetUserInfoResult getUserInfo(rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.GetUserInfo _input = 
         new rpctypes.GetUserInfo();

          
    rpctypes.GetUserInfoResponse _result = getUserInfo( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
  /**
   * getServerTimestamp 
   */

  public rpctypes.GetServerTimestampResponse getServerTimestamp(rpctypes.GetServerTimestamp parameters, rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions) 
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    java.util.HashMap _args = new java.util.HashMap();
    _args.put( "parameters", _wrap( parameters ) );
           _args.put( "SessionHeader", _wrap( SessionHeader ) );
           _args.put( "CallOptions", _wrap( CallOptions ) );
           try{
      java.lang.Object _result = _invoke( "getServerTimestamp", _args );
      return (rpctypes.GetServerTimestampResponse)_result;
    
    } catch (rpctypes.UnexpectedErrorFault e) {
      throw e;
    } catch (javax.xml.rpc.JAXRPCException e) {
      throw new java.rmi.RemoteException( e.getMessage(), e.getLinkedCause() );
    } catch (javax.xml.rpc.soap.SOAPFaultException e) {
      throw new java.rmi.RemoteException( "SOAP Fault:" + e + "\nDetail:\n"+e.getDetail(), e );
    } catch (java.lang.Throwable e) {
      throw new java.rmi.RemoteException( e.getMessage(), e );    }
  }
  /**
   * Convenience method for getServerTimestamp 
   */

  public rpctypes.GetServerTimestampResult getServerTimestamp(rpctypes.SessionHeader SessionHeader, rpctypes.CallOptions CallOptions)
       throws rpctypes.UnexpectedErrorFault, java.rmi.RemoteException {

    rpctypes.GetServerTimestamp _input = 
         new rpctypes.GetServerTimestamp();

          
    rpctypes.GetServerTimestampResponse _result = getServerTimestamp( _input, SessionHeader, CallOptions );

        return _result.getResult();
      }
}