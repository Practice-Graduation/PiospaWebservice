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
<jsp:include page="includes/_head.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<c:set var="currentPageParent" value="pageService" scope="request"/>
	<c:set var="currentPage" value="pageServiceList" scope="request"/>
	<div class="wrapper">
		<jsp:include page="includes/_header.jsp"></jsp:include>
		<jsp:include page="includes/_sidebar.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				Dịch vụ <small>PioSpa</small>
			</h1>

			</section>
			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-md-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">DANH SÁCH DỊCH VỤ</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="example1" class="table table-bordered">
								<thead>
									<tr>
										<th style="width: 10px; text-align: center;">#</th>
										<th style="text-align: center; width: 90px;">Hình</th>
										<th style="width: 150px; text-align: center;">Tên dịch vụ</th>
										<th style="text-align: center; width: 60px;">Thời gian sử dụng</th>
										<th style="text-align: center; width: 35px;">Tr Thái</th>
										<th style="text-align: center; width: 10px;"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${result}">
										<tr id="${p.serviceId }">
											<td style="text-align: center; vertical-align: middle;width: 10px;">${p.serviceId}</td>
											<td style="width: 100px;" class="post-img"><img
												src="${p.image}" alt="${p.image }" width="80px"
												height="100px"></td>
											<td style="text-align: center; vertical-align: middle;width: 150px;">${p.serviceName}</td>
											<td style="text-align: center; vertical-align: middle;width: 65px;">${p.serviceTime.time }</td>
											<td style="text-align: center; vertical-align: middle;width: 35px;"><c:if test="${p.isActive eq 1}">
													<span class="badge bg-green">Hiện</span>
												</c:if> <c:if test="${p.isActive eq 0}">
													<span class="badge bg-yellow">Ẩn</span>
												</c:if></td>

											<td style="text-align: center; vertical-align: middle;">
												<div class="btn-group-vertical">
													<a class="btn btn-info"
														href="${pageContext.request.contextPath }/admin/edit-service/${p.serviceId}"><i
														class="fa fa-pencil-square-o"></i></a>
													<button type="button" product-name="${p.serviceName}"
														product-id="${p.serviceId}"
														class="btn btn-danger btn-remove-product">
														<i class="fa fa-times"></i>
													</button>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
								<!-- <tfoot>
									<tr>
										<th>STT</th>
										<th>Hình</th>
										<th>Tên sản phẩm</th>
										<th>Loại sản phẩm</th>
										<th>Hãng sản xuất</th>
										<th>đơn giá</th>
										<th>Giảm giá</th>
										<th>Trạng thái</th>
										<th>Ngày tạo</th>
										<th>Chi tiết sản phẩm</th>
										<th>Ưu đải</th>
										<th></th>
									</tr>
								</tfoot>  -->
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
			<div class="modal fade" id="modalRemoveProduct" tabindex="-1"
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
							<button id="btn-remove-product-from-list" type="button"
								product-name="" product-id="" class="btn btn-danger">Delete</button>
						</div>
					</div>
				</div>
			</div>
			<!-- end./ Modal -->
		</div>
		<!-- /.content-wrapper -->


		<jsp:include page="includes/_footer.jsp"></jsp:include>
		<script type="text/javascript">
			$(document)
					.ready(
							function() {
								// scroll
								$('#example1').DataTable({
									"scrollX" : false,
									"fnDrawCallback" : function(oSettings) {
										showModal();
									}
								});
								// close modal
								$('.btn-close-modal').click(function() {
									$('#modalRemoveProduct').modal('hide');
								});
								// show modal
								function showModal() {
									$('.btn-remove-product')
											.click(
													function() {
														var productName = $(
																this).attr(
																'product-name');
														var productId = $(this)
																.attr(
																		'product-id');
														$(
																'#btn-remove-product-from-list')
																.attr(
																		'product-id',
																		productId);
														$('.modal-body')
																.html(
																		'<p class="text-danger">Bạn chắc chắn muốn xóa <strong>'
																				+ productName
																				+ '</strong> khỏi danh sách không?</p>');
														$('#modalRemoveProduct')
																.modal('show');
													});
								}

								$('#btn-remove-product-from-list')
										.click(
												function() {
													var productId = $(this)
															.attr('product-id');
													// AJAX: /ajax/remove-product-from-cart
													$
															.ajax({
																url : "${pageContext.request.contextPath}/admin/remove-service",
																type : "get",
																contentType : "application/json;charset=UTF-8",
																dataType : "text",
																data : {
																	product_id : productId
																},
																success : function(
																		result) {
																	console
																			.log(
																					"SUCCESS: ",
																					result);
																	if (result == 'true') {
																		$(
																				'#modalRemoveProduct')
																				.modal(
																						'hide');
																		var id = '#'
																				+ productId;
																		$(id)
																				.remove();
																	} else {
																		$(
																				'.modal-body')
																				.html(
																						'<p class="text-danger">Không thể xóa sản phẩm!</p>');
																		$(
																				'#modalRemoveProduct')
																				.modal(
																						'show');
																	}
																},
																error : function(
																		e) {
																	console
																			.log(
																					"ERROR: ",
																					e);
																	$(
																			'.modal-body')
																			.html(
																					'<p class="text-danger">Đã có lỗi xảy ra!</p>');
																	$(
																			'#modalRemoveProduct')
																			.modal(
																					'show');
																}
															});
												});
							});
		</script>
</body>
</html>