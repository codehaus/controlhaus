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

<%@ page session="false"%>
<%
    // The cause explains the cause of the error
    Throwable errorCause = (Throwable) request.getAttribute("portlet.error.cause");

    // Error message
    String errorMessage = (String) request.getAttribute("portlet.error.message");

    // Source URI that caused this error
    String sourceUri = (String) request.getAttribute("portlet.error.source.uri");

    if (sourceUri != null)
    {
%>
Error opening <%=sourceUri%>.
<%
    }
%>
<br>
The source of this error is
<%
    if (errorCause != null) {
        errorCause.printStackTrace(response.getWriter());
    }

    Throwable rootCause = null;
    if (errorCause instanceof ServletException)
    {
        rootCause = ((ServletException) errorCause).getRootCause();
        if (rootCause == null)
        {
            rootCause = errorCause.getCause();
        }
    }

    if (rootCause != null)
    {
%>
<br>Caused by<br>
<%
        rootCause.printStackTrace(response.getWriter());
    }
%>
