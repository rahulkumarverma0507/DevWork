<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/additional-methods.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/grid.locale-en.js"></script>

<title>Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pagestyle.css" />

<script type="text/javascript">

$(document).ready(function(){
	
	if("${messageBean.messageDescription}"!=null && "${messageBean.flag}" =="true"){
	   $('#successMessageDiv').append("${messageBean.messageDescription}")
	}
	
	if("${messageBean.flag}" =="false"){
		   $('#errorMessageDiv').append("${messageBean.messageDescription}")
	}
});
</script>

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
  <h1>Upload</h1> 
  <div id="successMessageDiv" style="display:inline; color:green;width:33%; font-weight: bold"></div>
  <div id="errorMessageDiv" style="display:inline; color:red ;width:33%; font-weight: bold"></div>
<form:form id="uploadForm" action="uploadfile" method="post"
                        enctype="multipart/form-data">
 <table>
    <tr>     
        <td>                 
            <input id = "file" class = "button" type="file"  name="file" onchange="ChangeText(this, 'txt');"/> 	
       </td>
       
       <td>
			<input type="submit" value="Upload File" class="snmallButton" />
	   </td>
   </tr>
</table>
</form:form>
</article>

<footer>Copyright © budweiser.com</footer>
</div>
</body>
</html>