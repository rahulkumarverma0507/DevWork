<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pagestyle.css" />

</head>
<body>
<div id="flex-container">
<header>
  <h1>Budweiser</h1>
</header>
<nav id="nav">
<ul>
  <li><a href="home">Home</a></li></br>
  <li><a href="about">About</a></li></br>
  <li><a href="contact">Contact</a></li></br>
  <li><a href="upload">Upload</a></li></br>
  <li><a href="form">Form</a></li></br>
  <li><a href="sendsms">Send SMS</a></li>
</ul>
</nav>
<article id="article">
  <h1>About</h1>
  <p>This is about page which will tell about the page.</p>
</article>

<footer>Copyright © budweiser.com</footer>
</div>
</body>
</html>