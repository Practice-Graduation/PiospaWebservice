<%@ page errorPage="//WEB-INF/jsp/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/admin.css">
<jsp:include page="includes/_head.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">

	<c:set var="currentPageParent" value="pageOrder"  scope="request"/>
	<c:set var="currentPage" value="pageOrderList" scope="request"/>
	<div class="wrapper">
		<jsp:include page="includes/_header.jsp" />
		<jsp:include page="includes/_sidebar.jsp" />


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				Đơn Hàng <small>PioSpa</small>
			</h1>
			</section>


			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">Danh sách đơn hàng</h3>

						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th style="width: 10px;">Mã HD</th>
										<th>Tên Khách hàng</th>
										<th>Tổng giá trị</th>
										<th>Ngày tạo</th>
										<th>Địa chỉ giao hàng</th>
										<th>Tình trạng</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${result}">
										<tr id="${p.orderId}">
											<td style="width: 10px;">${p.orderId}</td>
											<td>${p.fullName}</td>
											<td style="text-align: center; vertical-align: middle;">
											<fmt:setLocale
														value="vi_VN" /> <fmt:formatNumber type="currency" pattern = "#,###đ"
														value="${p.subTotal }"  />
											</td>
											<td style="text-align: center; vertical-align: middle;"><fmt:formatDate
													pattern="dd '/' MM '/' yyyy" value="${p.createdAt}" /></td>
											<td width="300px;">${p.addressDelivery}</td>

											<td style="text-align: center; vertical-align: middle;"
												width="35px;"><c:if
													test="${p.orderStatus.orderStatusId eq 3}">
													<span class="label label-danger">Đã hủy</span>
												</c:if> <c:if test="${p.orderStatus.orderStatusId eq 1}">
													<span class="label label-warning">Chờ duyệt</span>
												</c:if> <c:if test="${p.orderStatus.orderStatusId eq 2}">
													<span class="label label-success">Đã giao hàng</span>
												</c:if></td>

											<td style="text-align: center; vertical-align: middle;">
												<div class="btn-group-vertical">
													<a class="btn btn-info"
														href="${pageContext.request.contextPath}/admin/order-detail/${p.orderId}"><i
														class="fa fa-pencil-square-o"></i></a>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th style="width: 10px;">Mã HD</th>
										<th>Tên Khách hàng</th>
										<th>Tổng giá trị</th>
										<th>Ngày tạo</th>
										<th>Địa chỉ giao hàng</th>
										<th>Tình trạng</th>
										<th></th>
									</tr>
								</tfoot>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>

			</div>
			<!-- /.row --> </section>
			<!-- /.content -->
			<!-- Modal -->
			<div class="modal fade" id="modalRemoveBrand" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Message</h5>
							<button type="button" class="close btn-close-modal"
								data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary btn-close-modal"
								data-dismiss="modal">Close</button>
							<button id="btn-remove-brand-from-list" type="button"
								product-name="" product-id="" class="btn btn-danger">Delete</button>
						</div>
					</div>
				</div>
			</div>
			<!-- end./ Modal -->
		</div>
		<!-- /.content-wrapper -->


		<jsp:include page="includes/_footer.jsp" />
		<script type="text/javascript">
			$(document).ready(function() {
				// scroll
				$('#example1').DataTable({
					"scrollX" : true,
					"fnDrawCallback" : function(oSettings) {
						showModal();
					}
				});
			});
		</script>
</body>
</html>