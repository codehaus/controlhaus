<!--Generated by WebLogic Workshop-->
<%@ page language="java" contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui:html>
  <head>
    <title>Error</title>
  </head>
  <body>
    <p>
      An error has occurred:
    </p>
    <blockquote>
      <netui:label value="{request.errorMessage}" defaultValue="" />
      <br/>
      <netui:exceptions showMessage="true" />
    </blockquote>
  </body>
</netui:html>

<!-- Some browsers will not display this page unless the response status code is 200. -->
<% response.setStatus(200); %>
