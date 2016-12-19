<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pagestyle.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
		if("${messageBean.messageDescription}"!=null && "${messageBean.flag}" =="true"){
		   $('#successMessageDiv').append("${messageBean.messageDescription}")
		}
		
		if("${messageBean.flag}" =="false"){
			   $('#errorMessageDiv').append("${messageBean.messageDescription}")
		}
		
	$("form[name='sendSMSForm']").validate({
		rules : 
		{
	    
			mobileNumbers : 
	    	{
	         required : true,
	         phoneNumber: "^((\\+|00)(\\d{1,3})[\\s-]?)?(\\d{10})$"
	    	}
	    },
	    
	    messages : 
	    {
	    	mobileNumber : 
	        {
	            required : "This is required field.",
	            phoneNumber: "Please enter valid mobile number."
	        }
	    },
	    errorClass: "error-class",
	    submitHandler: function(form) {
	        form.submit();
	      }
		});
});
	function textCounter( field, countfield, maxlimit ) {
		//alert("here");
	 if ( field.value.length > maxlimit ) {
	  field.value = field.value.substring( 0, maxlimit );
	  field.blur();
	  field.focus();
	  return false;
	 } else {
	  countfield.value = maxlimit - field.value.length;
	 }
	}

</script>
</head>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.9.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.validate.js"></script>


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
  <h1>Send SMS</h1>
  <Label style="color:purple;font-weight: bold">Note: You can send SMS to multiple numbers. Please add comma separated mobile numbers.</Label><br><br><br>
  
  <div id="successMessageDiv" style="display:inline; color:green;width:33%; font-weight: bold"></div>
  <div id="errorMessageDiv" style="display:inline; color:red ;width:33%; font-weight: bold"></div>
  
  <form:form method="post" modelAttribute="userBean" action="sendSMS" id="sendSMSForm" name="sendSMSForm">
            <table>
	             <tr>
					 <td><label>Send To:</label></td>
					 <td><form:textarea path="mobileNumberString" rows="3" cols="40" id="mobileNumbers" type="text" maxlength="150"/></td>
				 </tr>
				
				 <tr>
					<td><label>Message Body:</label></td>
					<td><form:textarea path="messageBody" onblur="textCounter(this,this.form.counter,160);" onkeyup="textCounter(this,this.form.counter,160);" style="WIDTH: 608px; HEIGHT: 94px" name="messageBody" id="messageBody" rows="5" cols="40" /></td>
				 </tr>
			</table>
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			<input onblur="textCounter(this.form.recipients,this,160);" disabled  onfocus="this.blur();" tabindex="999" maxlength="3" size="3" value="160" name="counter"> <Label style="color:purple">characters remaining.</Label>
			
			<table border="0" align="center">
			<tr>
                   <td></td>
                   <td colspan="6"><input class = "button" type="submit" value="Send" id="sendsms"/></td>
                </tr>
			</table>
   </form:form>
   
</article>

<footer>Copyright © budweiser.com</footer>
</div>
</body>
</html>