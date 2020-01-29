<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<title><spring:message code="dashboard.title" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		
		<style><%@ include file ="../../../css/bootstrap.min.css"%></style>
		<style><%@ include file ="../../../css/font-awesome.css"%></style>
		<style><%@ include file ="../../../css/main.css"%></style>
		
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" media="screen">
	</head>

	<body>
    	<header class="navbar navbar-inverse navbar-fixed-top">
        	<div class="container">
            	<a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
            	<a href="?locale=en"><img style="margin-left: 50px;" src="https://cdn.icon-icons.com/icons2/107/PNG/512/united_kingdom_flag_flags_18060.png" id="drapeau" width="24" height="24" alt="England"> </a>
				<a href="?locale=fr"><img style="margin-left: 10px;" src="http://www.vogo-group.com/wp-content/uploads/2019/10/france_icon.png" id="drapeau" alt="France" width="24" height="24"></a> 
        	</div>
    	</header>

    	<section id="main">
        	
        	<div class="container">
            	<h1 id="homeTitle">
                	<c:out value="${nbComputer}" /> Computers 
            	</h1>
            
            	<div id="actions" class="form-horizontal">
                	<div class="pull-left">
                    	<form id="searchForm" action="#" method="GET" class="form-inline">
							<input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        	<input type="submit" id="searchsubmit" value="Filter by name" class="btn btn-primary" />
                    	</form>
                	</div>
                	<div class="pull-right">
                    	<a class="btn btn-success" id="addComputer" href="addComputer.html">Add Computer</a> 
                    	<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                	</div>
            	</div>
        	</div>
        	
        	<form id="deleteForm" action="#" method="POST">
            	<input type="hidden" name="selection" value="">
        	</form>

        	<div class="container" style="margin-top: 10px;">
            	<table class="table table-striped table-bordered">
                	<thead>
                    	<tr>
                        	<th class="editMode" style="width: 60px; height: 22px;">
                            	<input type="checkbox" id="selectall" /> 
                            	<span style="vertical-align: top;">
                                	<a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                    	<i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            	</span>
                        	</th>
                        	<th><spring:message code="dashboard.computerName" /></th>
                        	<th><spring:message code="dashboard.introduced" /></th>
                        <!-- Table header for Discontinued Date -->
                      		<th><spring:message code="dashboard.discontinued" /></th>
                        <!-- Table header for Company -->
							<th><spring:message code="dashboard.company" /></th>
                    	</tr>
                	</thead>
                <!-- Browse attribute computers -->
                	<tbody id="results">
                		<c:forEach items ="${listComputer}" var="computer">
                	    	<tr>
	                        	<td class="editMode">
	                            	<input type="checkbox" name="cb" class="cb" value="${computer.getId()}">
	                        	</td>
	                        	<td>
	                            	<a href="/computer-database/editcomputer?computer_id=${computer.getId()}" onclick="">${computer.getName()}</a>
	                        	</td>
	                        	<td>${computer.getIntroduced()}</td>
	                        	<td>${computer.getDiscontinued()}</td>
	                        	<td>${computer.getCompany().getName()}</td>
                    		</tr>
                    	</c:forEach>
                	</tbody>
            	</table>
        	</div>
    	</section>

    	<footer class="navbar-fixed-bottom">
        	<div class="container text-center">
            	<ul class="pagination">
            	
            		<c:if test="${pageID != 1}">
                	<li>
                		<a href="?search=${search}" aria-label="Previous">
                    		<span aria-hidden="true">&laquo;</span>
                  		</a> 	
		        	</li>
		        	</c:if>
              		
              		<c:if test="${pageID - 2 > 0}">
              			<li>
              				<a href="?pageNumero=${pageID - 2}&search=${search}">
              					<c:out value="${pageID - 2}"/>
              				</a>
              			</li>
              		</c:if>

					<c:if test="${currentPage - 1 > 0}">
              			<li>
              				<a href="?pageNumero=${pageID - 1}&search=${search}">
              					<c:out value="${pageID - 1}"/>
              				</a>
              			</li>
              		</c:if>

              		<c:if test="${currentPage + 1  < nbPage+1}">
              			<li>
              				<a href="?pageNumero=${pageID + 1}&search=${search}">
              					<c:out value="${pageID + 1}"/>
              				</a>
              			</li>
              		</c:if>

              		<c:if test="${currentPage + 2  < nbPage+1}">
              			<li>
              				<a href="?pageNumero=${pageID + 2}&search=${search}">
              					<c:out value="${pageID + 2}"/>
              				</a>
              			</li>
              		</c:if>
              	
              		<c:if test="${pageID < nbPage}">
              			<li>
	                		<a href="?pageNumero=${nbPage}&search=${search}" aria-label="Next">
	                    		<span aria-hidden="true">&raquo;</span>
	                		</a>            
            			</li>
            		</c:if>
        		</ul>
        	<div class="btn-group btn-group-sm pull-right" role="group" >
        		<a class="btn btn-default" href="?limit=${10}&search=${search}"><c:out value="${10}"/></a>
				<a class="btn btn-default" href="?limit=${50}&search=${search}"><c:out value="${50}"/></a>
				<a class="btn btn-default" href="?limit=${100}&search=${search}"><c:out value="${100}"/></a>
        	</div>
		</div>
    </footer>
    
	<script><%@ include file ="../../js/jquery.min.js"%></script>
	<script><%@ include file ="../../js/bootstrap.min.js"%></script>
	<script><%@ include file ="../../js/dashboard.js"%></script>

	</body>
</html>