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

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@page contentType="text/html"%>
<netui:html>
<head>
<title>Sales Force Control</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css">
table.login{
    margin: 10px;
    padding: 0px;
    border-style: solid;
    border-width: 1px;
    border-color: #cccce5;
    background-color: #f7f7ff;
}
.title {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 18px;
	font-weight: bold;
}
.bodytext {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: normal;
	line-height: normal;
}
input {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	border: 1px solid #666666;
	font-weight: normal;
	line-height: 14px;
}
body {
	margin-left: 0px;
	margin-top: 0px;
}

</style>

</head>

<body>

<table>
   <tr height="28">
    <td>&nbsp;</td>
   </tr>
</table>

<p align="center" class="title">BEA Workshop SalesForce Control Portal Sample</p>

<table>
   <tr height="20">
    <td>&nbsp;</td>
   </tr>
</table>

<netui:form action="login" focus="">

<table class="login" width="400" height="120" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="2" class="bodytext">&nbsp;</td>
  </tr>
  <tr>
  
    <td align="right" class="bodytext">User Name: </td>
    <td align="center" heigth="28"><netui:textBox dataSource="{actionForm.username}" size="28" maxlength="28" defaultValue="matyix@yahoo.com" styleClass="input"></netui:textBox></td>
  </tr>
  <tr>
    <td align="right" heigth="28" class="bodytext">Password:</td>
    <td align="center"><netui:textBox dataSource="{actionForm.password}" size="28" maxlength="28" defaultValue="sforce" password="true" styleClass="input"></netui:textBox></td>
  </tr>
  <tr>
   <td colspan="2" align="center">
        <netui:imageButton src="./framework/skins/sforce/images/login_btn.gif" value="Login"/>
  </td>
  </tr>

</table>
</netui:form>

<p>&nbsp;</p>
<div align="center"><netui:image src="./framework/skins/sforce/images/sforce-header.gif"/></div>

</body>

</netui:html>
