<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
       <link rel="stylesheet" href="/EmployeeWebAppEditable/assets/css/bootstrap.min.css"/>         
       <script src="/EmployeeWebAppEditable/assets/js/bootstrap.min.js"></script>       
	   <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    </head>

    <body>          
        <div class="container">
            <h2>Employees</h2>
            <!--Search Form -->
            <form action="/EmployeeWebAppEditable/employee" method="get" id="seachEmployeeForm" role="form">
                <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
                <div class="form-group col-xs-5">
                    <input type="text" name="employeeName" id="employeeName" class="form-control" required="true" placeholder="Type the Name or Last Name of the employee"/>                    
                </div>
                <button type="submit" class="btn btn-info">
                    <!--<span class="glyphicon glyphicon-search"></span>--> 
                    <span class="fa fa-search"></span>  Search
                </button>
                <br></br>
                <br></br>
            </form>

            <!--Employees List-->
            <c:if test="${not empty message}">                
                <div class="alert alert-success">
                    ${message}
                </div>
            </c:if> 
            <form action="/EmployeeWebAppEditable/employee" method="post" id="employeeForm" empRole="form" >              
                <input type="hidden" id="idEmployee" name="idEmployee">
                <input type="hidden" id="action" name="action">
                <c:choose>
                    <c:when test="${not empty employeeList}">
                        <table  class="table table-striped">
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <td>Name</td>
                                    <td>Last name</td>
                                    <td>Birth date</td>
                                    <td>Role</td>
                                    <td>Department</td>
                                    <td>E-mail</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <c:forEach var="employee" items="${employeeList}">
                                <c:set var="classSucess" value=""/>
                                <c:if test ="${idEmployee == employee.id}">                        	
                                    <c:set var="classSucess" value="info"/>
                                </c:if>
                                <tr class="${classSucess}">
                                    <td>
                                        <a href="/EmployeeWebAppEditable/employee?idEmployee=${employee.id}&searchAction=searchById">${employee.id}</a>
                                    </td>                                    
                                    <td>${employee.name}</td>
                                    <td>${employee.lastName}</td>
                                    <td>${employee.birthDate}</td>
                                    <td>${employee.empRole}</td>
                                    <td>${employee.department}</td>
                                    <td>${employee.email}</td>   
                                    <td><a href="#" id="remove" 
                                           onclick="document.getElementById('action').value = 'remove';document.getElementById('idEmployee').value = '${employee.id}';
                                                    
                                                    document.getElementById('employeeForm').submit();"> 
                                            <span class="fa fa-trash"/>
                                        </a>
                                                   
                                    </td>
                                </tr>
                            </c:forEach>               
                        </table>  
                    </c:when>                    
                    <c:otherwise>
                        <br>           
                        <div class="alert alert-info">
                            No people found matching your search criteria
                        </div>
                    </c:otherwise>
                </c:choose>                        
            </form>
            <form action ="/EmployeeWebAppEditable/jsp/new-employee.jsp">            
                <br></br>
                <button type="submit" class="btn btn-primary  btn-md">New employee</button> 
            </form>
        </div>
    </body>
</html>