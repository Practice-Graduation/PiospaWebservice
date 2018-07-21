<%@ page errorPage="error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="includes/_head.jsp" />
<title>PioSpa | Đăng Nhập</title>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/themes/css/style.css"> 
</head>
<body>

	<!-- CONTENT -->
	<div class="container content-shop">
			<div class="col-md-6 login-card">
				<div class="login-account">
					<h2>Đăng Nhập</h2>
					<!-- /login?error=true -->
					<c:if test="${param.error == 'true'}">
						<div class="alert alert-danger">Tài khoản hoặc mật khẩu
							không đúng.</div>
					</c:if>
					<!--End /login?error=true -->

					<div class="login-form">
						<form name="f"
							action="${pageContext.request.contextPath}/j_spring_security_check?${_csrf.parameterName}=${_csrf.token}"
							method="post" autocomplete='off'>
							<div class="form-group">
								<label for="userName">Email</label> <input type="text" autocomplete="off"
									class="form-control" id="username" name="username"
									aria-describedby="emailHelp" placeholder="Nhập vào email">
								
							</div>
							<div class="form-group">
								<label for="password">Mật Khẩu</label> <input type="password" autocomplete="off"
									class="form-control" id="password" name="password"
									placeholder="Nhập vào mật khẩu">
							</div>
							<p>
								<label> <a
									href="${pageContext.request.contextPath }/register">Đăng kí
										tài khoản?</a>
								</label>
							</p>
							<div class="button-submit">
								<input type="submit" class="btn btn-primary" name="btnSumit"
									value="Đăng Nhập">
								<%-- <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" /> --%>
							</div>

						</form>
					</div>
				</div>
			</div>
	</div>


	<!-- END CONTENT -->

	<div class="navbar navbar-fixed-bottom">

		<footer class="main-footer" style="margin-left: 0px;">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0.1
		</div>
		© 2018 <strong>PioSpa</strong> | Designed and Developed by Nguyễn Bảo
		Bằng</span>. </footer>

	</div>

	<!-- ./wrapper -->
	<!-- jQuery 2.2.0 -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/bootstrap/js/bootstrap.min.js"></script>
	<!-- DataTables -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/themes/admin/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/themes/admin/dist/js/demo.js"></script>
	<!-- page script -->

	<!--upload -->
	<script
		src="${pageContext.request.contextPath}/themes/plugins/fileuploader/jquery.fileuploader.js"></script>
	<script
		src="${pageContext.request.contextPath}/themes/plugins/fileuploader/thumbnails_custom.js"></script>
	<script
		src="${pageContext.request.contextPath}/themes/plugins/fileuploader/multifleupload-custom.js"></script>
</body>
</html>