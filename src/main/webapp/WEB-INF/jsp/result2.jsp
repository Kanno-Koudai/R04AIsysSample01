<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
Double negative = (Double) request.getAttribute("negative");
Double neutral = (Double) request.getAttribute("neutral");
Double positive = (Double) request.getAttribute("positive");

%>

<body>
<H1>Sentiment結果</H1>
<H3>Negative : <%= negative %></H3>
<H3>Neutral : <%= neutral %></H3>
<H3>Positive : <%= positive %></H3>

</body>
</html>