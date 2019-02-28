<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<title> List Employees</title>
	
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body> 

	<div id="wrapper">
		<div id="header">
			<h2>ERM - Employee Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
		<!-- button -->
		
		<input type="button" value="Add Employee"
		onclick="window.location.href='showFormForAdd'; return false;"
		class="add-button"
		/>
		<!--  add a search box -->
		 <form:form action="search" method="GET">
                Search employee: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-button" />
            </form:form>      
		
		<!-- Sort by -->
		<c:url var="fName" value="/employee/sortByFirstName">
			<c:param name="fName" value="First Name"/>
		</c:url>
		
		<c:url var="lName" value="/employee/sortByLastName">
			<c:param name="lName" value="Last Name"/>
		</c:url>
		
		<c:url var="email" value="/employee/sortByEmail">
			<c:param name="email" value="Email"/>
		</c:url>
			
		<!-- Sort by:	<a href="${fName}">First Name</a>	<a href="${lName}">Last Name</a>	<a href="${email}">Email</a>	-->
				
		<!-- html table -->
		<table>
			<tr>
				<th><a href="${fName}" style="color:#FFFFFF">First Name</a></th>
				<th><a href="${lName}" style="color:#FFFFFF">Last Name</a></th>
				<th><a href="${email}" style="color:#FFFFFF">Email</a></th>
				<th>Action</th>
			</tr>
			
			<!--  loop over and print our employees -->
			<c:forEach var="tempEmployee" items="${employees}">
				<!--  construct an update link with employee id -->
			<c:url var="updateLink" value="/employee/showFormForUpdate">
				<c:param name="employeeId" value="${tempEmployee.id}"/>
			</c:url>
			
				<!--  construct an delete link with employee id -->
			<c:url var="deleteLink" value="/employee/delete">
				<c:param name="employeeId" value="${tempEmployee.id}"/>
			</c:url>
			
				<tr>
					<td>${tempEmployee.firstName} </td>
					<td>${tempEmployee.lastName} </td>
					<td>${tempEmployee.email} </td>
					
					<td>
						<!--  display the update link -->
						<a href="${updateLink}">Update</a>
						|
						<!--  display the delete link -->
						<a href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">Delete</a>
						
					 </td>

				</tr>
			</c:forEach>
		</table>
		</div>
	</div>

</body>
</html>