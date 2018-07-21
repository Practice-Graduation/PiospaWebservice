<%@ page errorPage="//WEB-INF/jsp/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Thành viên | Admin PTiTShop</title>
<jsp:include page="includes/_head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<c:set var="currentPageParent" value="pageAccount" scope="request"/>
<c:set var="currentPage" value="pageAccountList" scope="request"/>
<div class="wrapper">

<jsp:include page="includes/_header.jsp"/>
<jsp:include page="includes/_sidebar.jsp"/>
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Thành viên
        <small>PTiTShop</small>
      </h1>
      
    </section>

	
	<!-- Main content -->
    <section class="content">
      <div class="row">
		
		<c:forEach var="a" items="${result}">
		<div class="col-md-3">
	      <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="${a.staffAvatar}" alt="User profile picture">
              <h3 class="profile-username text-center">${a.fullname}</h3>
              <p class="text-muted text-center">
              <c:if test="${a.isAdmin eq 1}"><span class="label label-success">Quản trị viên</span></c:if>
	                <c:if test="${a.isAdmin eq 0}"><span class="label label-warning">Nhân viên</span></c:if></p>
              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Tên tài khoản</b> <a class="pull-right">${a.account}</a>
                </li>
                <li class="list-group-item">
                  <b>Ngày lập</b> <a class="pull-right"><fmt:formatDate pattern="dd '/' MM '/' yyyy" value="${a.createdAt}"/></a>
                </li>
                <li class="list-group-item">
                  <b>Tình trạng</b> 
                  <a class="pull-right">
                  	<c:if test="${a.isActive eq 1}"><span class="label label-success">Hoạt động</span></c:if>
	                <c:if test="${a.isActive eq 0}"><span class="label label-warning">Đã khóa</span></c:if>
                  </a>
                </li>
              </ul>
              <a href="${pageContext.request.contextPath}/admin/profile/${a.staffId}" class="btn btn-primary btn-block"><b>Thông tin cá nhân</b></a>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
		</div>
		</c:forEach>
		
		
	  </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
	</div>
  <!-- /.content-wrapper -->
	<jsp:include page="includes/_footer.jsp"/>
</body>
</html>