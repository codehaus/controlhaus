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
 * This exception class was generated by weblogic
 * webservice exception gen on Wed May 05 02:42:36 PDT 2004 */

public class LoginFault     extends java.lang.Exception
     implements java.io.Serializable 
{

  private rpctypes.LoginFault info;

  public LoginFault(rpctypes.LoginFault t) {
    info = t;
  }

  public rpctypes.LoginFault getFault() {
    return info;
  }

  public void setFault(rpctypes.LoginFault t) {
    info = t;
  }
}