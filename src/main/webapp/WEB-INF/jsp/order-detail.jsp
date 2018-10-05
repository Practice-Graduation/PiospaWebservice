<%@ page errorPage="//WEB-INF/jsp/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Chi tiết đơn hàng | Admin PTiTShop</title>
<jsp:include page="includes/_head.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<c:set var="currentPageParent" value="pageOrder" scope="request"/>
	<c:set var="currentPage" value="pageOrderList" scope="request"/>
	<div class="wrapper">

		<jsp:include page="includes/_header.jsp"></jsp:include>
		<jsp:include page="includes/_sidebar.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				Chi tiết đơn hàng <small>Đơn hàng</small>
			</h1>

			</section>


			<!-- Main content -->
			<section class="content">
			<div class ="row">
				<c:if test="${not empty message}">
						<div class="alert alert-danger">${message }</div>
					</c:if>
			</div>
			<div class="row">
				<div class="col-xs-3">
					<!-- Profile Image -->
					<div class="box box-primary">
						<div class="box-body box-profile">
							<img class="profile-user-img img-responsive img-circle"
								src="${order.customer.customerAvatar}" alt="${order.fullName}">

							<h3 class="profile-username text-center">
								<a
									href="${pageContext.request.contextPath}/admin/profile/${order.customer.customerId}">${order.fullName}</a>
							</h3>

							<p class="text-muted text-center">Khách hàng</p>

							<ul class="list-group list-group-unbordered">
								<li class="list-group-item"><b>Tổng giá trị</b> <a
									class="pull-right"><fmt:formatNumber
											value="${order.total}" type="currency" pattern = "#,###đ" /></a></li>
								<li class="list-group-item"><b>Số tiền giảm</b> <a
									class="pull-right"><fmt:formatNumber
											value="${order.discount}" type="currency" pattern = "#,###đ" /></a></li>
								<li class="list-group-item"><b>Phí giao hàng</b> <a
									class="pull-right"><fmt:formatNumber
											value="${order.deliveryCost}" type="currency" pattern = "#,###đ" /></a></li>
								<li class="list-group-item"><b>Thành tiền</b> <a
									class="pull-right"><fmt:formatNumber
											value="${order.subTotal}" type="currency" pattern = "#,###đ" /></a></li>
								<li class="list-group-item"><b>Thời gian</b> <a
									class="pull-right"><fmt:formatDate
											pattern="dd '/' MM '/' yyyy" value="${order.createdAt}" /></a></li>
								<li class="list-group-item"><b>Tình trạng</b> <a
									class="pull-right"> <c:if
											test="${order.orderStatus.orderStatusId eq 3}">
											<span class="label label-danger">Hủy</span>
										</c:if> <c:if test="${order.orderStatus.orderStatusId eq 1}">
											<span class="label label-warning">Chưa thanh toán</span>
										</c:if> <c:if test="${order.orderStatus.orderStatusId eq 2}">
											<span class="label label-success">Đã thanh toán</span>
										</c:if>
								</a></li>

							</ul>

							<%-- <a
								href="${pageContext.request.contextPath}/admin/profile/${order.customer.customerId}"
								class="btn btn-primary btn-block"><b>Thông tin cá nhân</b></a> --%>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->

					<div class="box box-primary">
						<div class="box-body box-profile">
							<h3 class="profile-username text-center">Tình trạng</h3>
							<form
								action="${pageContext.request.contextPath}/admin/order-detail/${order.orderId}"
								method="post">
								<!-- radio -->
								<div class="form-group">
								<div class = "group-lable">
									<label> <input type="radio" name="order_status"
										value="3" class="minimal"
										${order.orderStatus.orderStatusId eq 3 ? 'checked' : '' }>
										<span class="label label-danger">Hủy</span>
									</label>
								</div>
								<div class = "group-lable">
									<label> <input type="radio" name="order_status"
										value="1" class="minimal"
										${order.orderStatus.orderStatusId eq 1 ? 'checked' : '' }>
										<span class="label label-warning">Chưa Thanh toán</span>
									</label> 
								</div>
								<div class = "group-lable">
									<label> <input type="radio" name="order_status"
										value="2" class="minimal"
										${order.orderStatus.orderStatusId eq 2 ? 'checked' : '' }>
										<span class="label label-success">Thanh toán</span>
									</label>
								</div>
									
									 
									
								</div>
								<button type="submit" class="btn btn-primary btn-block">
									<i class="fa fa-floppy-o"></i> Lưu
								</button>
							</form>
						</div>
					</div>
				</div>
				<c:if test="${! empty(products)}">

					<div class="col-xs-9">
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">Danh sách sản phẩm</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>Hình ảnh</th>
											<th>Tên sản phẩm</th>
											<th>Số lượng</th>
											<th>Giá</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="o" items="${products}">
											<tr>
												<td>${o.product.productId}</td>
												<td width="120px;"><img width="100px"
													src="${o.product.image}" alt="" /></td>
												<td>${o.product.productName}</td>
												<td style="text-align: center;vertical-align : middle;">${o.number}</td>
												<td style="text-align: center;vertical-align : middle;"><fmt:formatNumber value="${o.price}"
														type="currency" pattern = "#,###đ" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->

				</c:if>

			</div>
			<!-- /.row --> </section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->

		<jsp:include page="includes/_footer.jsp"></jsp:include>
		
		<script type="text/javascript">
		
		$('#radioWatingServe').click(function(){
			var id = $(this).attr('booking-detail-id');
			var radioValue = $(this).attr('value');
			send(id, radioValue);
		});
		
		$('#radioServing').click(function(){
			var id = $(this).attr('booking-detail-id');
			var radioValue = $(this).attr('value');
			send(id, radioValue);
		});
		
		$('#radioServed').click(function(){
			var id = $(this).attr('booking-detail-id');
			var radioValue = $(this).attr('value');
			send(id, radioValue);
		});
		
		$('#radioCancle').click(function(){
			var id = $(this).attr('booking-detail-id');
			var radioValue = $(this).attr('value');
			send(id, radioValue);
		});
		
		function send(id, radioValue){
			 $.ajax({
					url : "${pageContext.request.contextPath}/admin/update-booking-detail/" + id,
					type : "get",
					contentType : "application/json;charset=UTF-8",
					dataType : "text",
					data : {
						served_status : radioValue
					},
					success : function(result) {
						if (result == 'true') {
							alert("Cập nhật thành công");
						} else {
							alert("Cập nhật thất bại");
						}
					},
					error : function(e) {
						alert("Có lỗi xảy ra: " + e);
					}
				});
		}
		
		</script>
</body>
</html>