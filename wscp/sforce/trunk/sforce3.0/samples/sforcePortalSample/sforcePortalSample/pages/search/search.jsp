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

<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<TABLE CELLPADDING=0 CELLSPACING=0 BORDER=0>
    <TR>
        <TD CLASS="moduleTitle">Quick Search among Sales Force objects</TD>
    </TR>
    <TR>
        <TD CLASS="moduleLine"><IMG SRC="/s.gif" WIDTH="1" HEIGHT="2"></td>
    </tr>
    <TR>
        <TD>
        <table width=100% border=0 cellspacing=0 cellpadding=0>
            <tr>
                <td align=center>
                <netui:image src="./../../resources/images/sforce.gif"/>
                <br>
                <a href="http://www.sforce.com/" target="new"></td>
            </tr>
        </table>
        </TD>
    </TR>
    <TR>
        <TD>&nbsp;</TD>
    </TR>
    <TR>
        <TD CLASS="moduleTitle"></TD>
    </TR>
    <TR>
        <TD CLASS="moduleLine">
        <IMG SRC="" WIDTH="1" HEIGHT="2"></td>
    </tr>
    <TR>
        <TD>
        <netui:form method="GET"  action="search" >
            <input type="hidden" name="searchType" value="1">Enter keyword:<BR>
            <netui:textBox dataSource="{actionForm.searchParameter}" size="27" maxlength="80"/>
        </TD>
    </TR>
    <TR
        <TD><img src="/s.gif" border=0 width=1 height=5 align="center"></TD>
    </TR>
    <TR>
        <TD><input type="submit" name="search" value="Search" class="button" ></TD>
    </TR>
    <TR>
        <TD><img src="/s.gif" border=0 width=1 height=5 align="center"></TD>
    </TR>
    <TR>
        <TD VALIGN="MIDDLE" CLASS="bodySmall"><img src="/s.gif" border=0 width=1 height=5 align="center"><img src="/img/link_arrow.gif" border=0 width=22 height=9></TD>
        </netui:form>
    </TR>
    <TR>
        <TD>&nbsp;</TD>
        
        <p><netui-data:callPageFlow method="getArrayList" resultId="arrayList"/>
        <netui-data:repeater dataSource="{pageFlow.arrayList}">
            <netui-data:repeaterHeader>
                <table border="1">
                    <tr>
                        <td><b>Index</b></td>
                        <td><b>Name</b></td>    
                    </tr>
            </netui-data:repeaterHeader>
            <netui-data:repeaterItem>
                <tr>
                    <td>
                        <netui:label value="{container.index}" />
                    </td>
                    <td>
                        <netui:label value="{container.item}" />
                    </td>
                </tr>
            </netui-data:repeaterItem>
            <netui-data:repeaterFooter>
                </table>
            </netui-data:repeaterFooter>    
        </netui-data:repeater>

        
        
    </TR>
</TABLE>
