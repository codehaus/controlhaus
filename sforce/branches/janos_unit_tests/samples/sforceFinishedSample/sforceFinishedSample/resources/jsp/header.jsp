<%-- 
   Copyright 2004 Salesforce.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

--%>


  <!-- header.jsp -->

  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <!-- Topmost table containing the primary header graphics and color -->
   <tr>
   <!--Primary header color defined here in the form of 'bgcolor="#ffffff"' -->
    <td width="100%" height="96" valign="top" bgcolor="#ffffff">
    <!-- Left justified graphic image with arrows -->
    <img src="<%=request.getContextPath ()%>/resources/images/weblogic-img-lt.jpg" border="0" align="left">
    <!-- Right justified graphic text 'Weblogic Workshop version 2.0' -->
    <img src="<%=request.getContextPath ()%>/resources/images/weblogic-img-rt.gif" border="0" align="right">
    </td>
   </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <!-- Smaller, lower table containing the decorative 'bar' which could be used for navigation -->
   <tr>
    <!-- Styled, graphical look of 'bar' defined here using a repeatable image specified as 'background="bar-background.gif"' -->
    <td width="100%" height="21" background="<%=request.getContextPath ()%>/resources/images/bar-background.gif">&nbsp;</td>
  </tr>
  </table>
  <!-- end header.jsp -->
