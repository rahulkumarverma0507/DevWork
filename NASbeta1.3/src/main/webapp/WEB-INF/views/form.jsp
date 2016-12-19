<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<!-- <script type="text/javascript" src="https://ajax.microsoft.com/ajax/jQuery/jquery-1.4.2.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script> -->

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/additional-methods.js"></script>

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
	
		$("form[name='userForm']").validate({
		rules : 
		{
	    
		mobileNumber : 
	    {
	         required :true,
	         digits: true
	    },
	    firstName :
	    {
	    	lettersonly: true
	    },
	    middleName :
	    {
	    	lettersonly: true
	    },
	    lastName :
	    {
	    	lettersonly: true
	    },
	    email :
	    {
	    	email: true
	    }
	    },
	    
	    messages : 
	    {
	    	mobileNumber : 
	        {
	            required : "This is required field.",
	            digits: "Please enter only digits."
	        },
	        firstName :
	        {
	        	lettersonly: "Please enter only alphabets."
	        },
	        middleName :
	        {
	        	lettersonly: "Please enter only alphabets."
	        },
	        lastName :
	        {
	        	lettersonly: "Please enter only alphabets."
	        },
	        email :
	        {
	        	email: "Please enter valid email id."	
	        }
	    },
	    errorClass: "error-class",
	    submitHandler: function(form) {
	        form.submit();
	      }
	});
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
  <h1>Form</h1>
  
  <div id="successMessageDiv" style="display:inline; color:green;width:33%; font-weight: bold"></div>
  <div id="errorMessageDiv" style="display:inline; color:red ;width:33%; font-weight: bold"></div>
  
  <p>Please fill below details to create new entry.</p>
  
         <form:form method="post" modelAttribute="userForm" action="addNewEntry" id="userForm" name="userForm">
            <table>
	             <tr>
					 <td><label>First Name:</label></td>
					 <td><form:input path="firstName" id="firstName" type="text" maxlength="50"/></td>
				 </tr>
				
				 <tr>
					<td><label>Middle Name:</label></td>
					<td><form:input path="middleName"  type="text" id="middleName" maxlength="50"/></td>
				 </tr>
				 
				 <tr>
					<td><label>Last Name:</label></td>
					<td><form:input path="lastName" type="text" id="lastName" maxlength="50"/></td>
				 </tr>
				 
				 <tr>
					<td><label>Email:</label></td>
					<td><form:input path="email" type="text" id="email" maxlength="50"/></td>
				 </tr>
				 
				 <tr>
					<td><label>Number:*</label></td>
					<td><form:input path="mobileNumber" type="text" name="mobileNumber" id="mobileNumber" maxlength="15"/></td>
				 </tr>
				 
				 <tr>
					<td><label>Address:</label></td>
					<td><form:textarea path="address" rows="5" cols="40" id="address" maxlength="250"/></td>
				 </tr>
				 
				 <tr>
					<td><label>Want SMS notification?</label></td>
					<td>
					  <form:radiobutton path="notificationflag"  name= "notificationflg" value="Yes"/> Yes<br>
                      <form:radiobutton path="notificationflag" name= "notificationflg" value="No"/> No<br>
					</td>
				 </tr>
				 
				 <tr>
                   <td></td>
                   <td colspan="6"><input class = "button" type="submit" value="submit" id="submit"/></td>
                </tr>
            
			</table>
		</form:form>
			
</article>

<footer>Copyright © budweiser.com</footer>
</div>
</body>
</html>