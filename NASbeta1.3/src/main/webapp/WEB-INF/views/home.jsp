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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery.jqGrid.src.js"></script>

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.8.0/css/ui.jqgrid.css">

<title>Home</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/pagestyle.css" />

<style type="text/css">
    
    /* It will wrap header and data in the grid */
 .ui-jqgrid tr.jqgrow td {
        word-wrap: break-word; /* IE 5.5+ and CSS3 */
        white-space: pre-wrap; /* CSS3 */
        white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
        white-space: -pre-wrap; /* Opera 4-6 */
        white-space: -o-pre-wrap; /* Opera 7 */
        overflow: hidden;
        height: 23px;
        vertical-align: middle;
        text-align:left;
    }
    .ui-jqgrid .ui-state-highlight { background: "#ADD8E6"; }
</style>

<script type="text/javascript">


 $(document).ready(function(){
    
	 //hide data update div on page load
	 $("#updateInfoDiv").hide();
	 
	 var getSelectedRowInformation = function (id, isSelected) {
		 
		 var myGrid = $('#personDataList'),
		    selRowId = myGrid.jqGrid ('getGridParam', 'selrow'),
		    firstNameValue = myGrid.jqGrid ('getCell', selRowId, "firstName");
		    mobileNumber = myGrid.jqGrid ('getCell', selRowId, "mobileNumber");
		    notificationflg = myGrid.jqGrid ('getCell', selRowId, "notificationflag");
		    
		    //alert(notificationflg);
		    
		   //hide grid div and show update info div on selectiong row 
		   $('#gridDiv').hide();
		   $("#updateInfoDiv").show();
		   
		   $("#firstNameLabel").val(firstNameValue);
		   $("#mobileNumberLabel").val(mobileNumber);
		   $("#smsNotificationdropdown").val(notificationflg);
	 }
	 
	 //Go back button will take back grid page 
	 $("#backBtn").click(function(){
		 
		   $("#updateInfoDiv").hide();
		   $('#gridDiv').show();
	 });
	 
	//Go back button will take back grid page 
	 <%-- $("#updateBtn").click(function(){
		 
		 $('#dataForm').attr('action', "<%=request.getContextPath()%>/updateSmsNotification"); 
	     $('#dataForm').submit() ;	
	 }); --%>
	 
    var obj = JSON.parse('${userBean.personDataString}');
    

    var myJSONText = JSON.stringify(obj);
	loadGrid();
	
	function loadGrid()
	{	
		/*This method loads table with data recieved from controller.*/
		var grid=jQuery("#personDataList").jqGrid({
			datatype: "jsonstring",
			datastr: myJSONText,
			pager: "#pager",
			rowNum:10000,
			height:'auto',
		    colNames:["userId","FirstName","Middle Name","Last Name","Email","Mobile Number","Address","SMS Opt"],
			   
	    	colModel:[
					{name:"userId" ,index:'userId',width:05,hidden:true},
			   		{name:"firstName" ,index:'firstName',width:120,title:false},
			   		{name:"middleName",index:'middleName', width:120,title:false},
			   		{name:"lastName",index:'lastName', width:120,title:false},
			   		{name:"email",index:'email', width:200},
			   		{name:"mobileNumber",index:'mobileNumber', width:90},
			   		{name:"address",index:'address', width:200},
			   		{name:"notificationflag",index:'notificationflag', width:90}
			   		
			   	], 
			   	multiselect: false,
			   	sortable : true,
			   	viewrecords : true,
                gridview : true,
                onSelectRow: getSelectedRowInformation,
			   	
			   	gridComplete: function () {
			   	$("tr.jqgrow:odd").css("background", "#deeff5");
			   	$("tr.jqgrow:even").css("background", "white");
			    var rows = jQuery("#personDataList").jqGrid('getGridParam','records');
		}
	});
	}
	
	/*This will convert jqgrid data to json string.
	 After pressing export button data is passed to controller for exporting.  */
	 $("#export").click(function(){
		 
		 var count=$('#personDataList').getGridParam("reccount");
		 
		 if(count==0){ //if no row then throws error for export.
			 alert("No Data To Export");
			 return
		 }
		 window.open("<%=request.getContextPath()%>/export",'', 'menubar=0,left=0,top=5,width=10,height=10,toolbar=0,status=0,resizable=0,location=0');   
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
  <h1>Home</h1>
  <form:form  method="get" modelAttribute="userBean" action="getData" id="dataForm" name="dataForm">
   	   <input class = "button" type="submit" value="Get Data" id="getDataBtn" tabindex="1">
       <input class ="button" type="button" value="Export Data" name="exportBtn" id="export" tabindex="2"><br>
       
       <!-- <fieldset style="width:1000px, height;height:500px"> -->
                
                <div id="gridDiv">
                  <table id="personDataList" >
                  </table>
                </div>
	    	    <div id="pager"></div>
	    	    <div id="updateInfoDiv">
   </form:form>	    	    
   <form:form  method="post" commandName="userBean" action="updateSmsNotification" id="updateDataForm" name="updateDataForm">
	    	      <table>
	             
	             <tr>
					 <td><form:hidden path="userId" id="userId"/></td>
				 </tr>
	             <tr>
					 <td><label>First Name:</label></td>
					 <td><form:input path="firstName" type="text" id="firstNameLabel" maxlength="50"/></td>
				 </tr>
				
				 <tr>
					<td><label>Mobile Number:</label></td>
					<td><form:input path="mobileNumber" type="text" id="mobileNumberLabel" maxlength="50"/></td>
				 </tr>
				 
				 <tr>
					
					<td><label>SMS Opt In:</label></td>
					<td ><form:select path="notificationflag" name="smsNotificationdropdown" id="smsNotificationdropdown" style="width:173px">
						  <form:option value="">Select</form:option>
						  <form:option value="Yes">Yes</form:option>
						  <form:option value="No">No</form:option>
					  </form:select>
					</td>
					
				 </tr>
				 <tr></tr>
				 <tr>
				   <td>
				     <input class ="snmallButton" type="button" value="Go Back" name="backBtn" id="backBtn">
				   </td>
				   <td>
				     <input class ="snmallButton" type="submit" value="Update" name="updateBtn" id="updateBtn">
				   </td>
				 </tr>
				 
				 </table>
	    	    </div>
	    <!-- </fieldset> -->
	    	   
  </form:form>
</article>

<footer>Copyright © budweiser.com</footer>
</div>
</body>
</html>
