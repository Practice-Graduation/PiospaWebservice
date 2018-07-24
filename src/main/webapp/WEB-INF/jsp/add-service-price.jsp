<%@ page errorPage="//WEB-INF/jsp/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin</title>
<jsp:include page="includes/_head.jsp"></jsp:include>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/css/admin.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<c:set var="currentPageParent" value="pageServicePrice" scope="request" />
	<c:set var="currentPage" value="pageAddNewServicePrice" scope="request" />
	<div class="wrapper">

		<jsp:include page="includes/_header.jsp"></jsp:include>
		<jsp:include page="includes/_sidebar.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				Dịch Vụ <small>PioSpa</small>
			</h1>

			</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<!-- general form elements -->
					<div class="box box-primary ">
						<div class="box-header with-border">
							<h3 class="box-title title-name">${title }</h3>
						</div>
						<!-- /.box-header -->

						<div class="col-md-12">
					       <c:if test="${not empty message}">
								<div class="alert alert-warning alert-dismissible">
									<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
									<h4><i class="icon fa fa-warning"></i> Thất bại!</h4> ${message }
								</div>
							</c:if>
					        <c:if test="${not empty result and result eq 'create'}">
						        <div class="alert alert-success alert-dismissible">
						        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						        	<h4><i class="icon fa fa-check"></i> Thành công!</h4> Thêm dịch vụ <strong>${productname}</strong> thành công.
						        </div>
					         </c:if>
					         <c:if test="${not empty result and result eq 'update'}">
						        <div class="alert alert-success alert-dismissible">
						        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
						        	<h4><i class="icon fa fa-check"></i> Thành công!</h4> Cập nhật dịch vụ <strong>${productname}</strong> thành công.
						        </div>
					         </c:if>
					         
					        </div> 
						<!-- form start -->
						<form role="form" action="" method="post"
							enctype="multipart/form-data" autocomplete='off'>
							<div class="box-body">
								<input type="hidden" name="servicepriceid"
									value="${servicepriceid }">


								<div class="form-group">
									<label>Nhóm dịch vụ</label> <select class="form-control"
										name="servicegroup">
										<c:forEach items="${groups }" var="group">
											<option value="${group.serviceGroupId }"
												<c:if test="${ group.serviceGroupId eq servicegroup}">selected</c:if>>${group.serviceGroupName }</option>
										</c:forEach>
									</select>
								</div>

								<div class="form-group">
									<label>Loại dịch vụ</label> 
									<select class="form-control"
										name="servicetype" id="servicetype">
										<c:forEach items="${types }" var="type">
											<option value="${type.serviceTypeId }"
												<c:if test="${ type.serviceTypeId eq servicetype}">selected</c:if>>${type.serviceTypeName }</option>
										</c:forEach>
									</select>
								</div>

								<div class="form-group">
									<label>Dịch vụ</label>
									 <select class="form-control"
										name="service" id ="service">
										<option value="0"
												<c:if test="${ 0 eq service}">selected</c:if>>Chọn
												dịch vụ</option>
										<c:forEach items="${services }" var="s">
											
											<option value="${s.serviceId }"
												<c:if test="${ s.serviceId eq service}">selected</c:if>>${s.serviceName }</option>
										</c:forEach>
									</select>
								</div>

								<div class="form-group">
									<label>Gói dịch vụ</label> 
									<select class="form-control"
										name="servicepakage" id="servicepakage">
										<option value="0"
											<c:if test="${ 0 eq servicepakage}">selected</c:if>>Chọn
											gói dịch vụ</option>
										<c:forEach items="${servicepakages }" var="sp">
											<option value="${sp.servicePackageId }"
												<c:if test="${ sp.servicePackageId eq servicepakage}">selected</c:if>>${sp.servicePackageName }</option>
										</c:forEach>
									</select>
								</div>

								<!-- End Service Group -->
								<div class="form-group">
									<label for="productprice">Giá</label> <input type="number"
										min="0" class="form-control" id="serviceprice"
										name="serviceprice" placeholder="Nhập vào đơn giá sản phẩm"
										required value="${ serviceprice}">
								</div>
								<!-- radio -->
								<div class="form-group">
									<label>Tình trạng</label>
									<div class="radio">
										<label> <input type="radio" name="post_status"
											value="1" ${post_status eq 1 ? 'checked':'checked'}>
											<span class="label label-success">Hoạt động</span>
										</label>
									</div>
									<div class="radio">
										<label> <input type="radio" name="post_status"
											value="0" ${post_status eq 0 ? 'checked':''}> <span
											class="label label-warning">Khóa</span>
										</label>
									</div>
								</div>

							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="submit" class="btn btn-primary" value="submit"
									name="submit">Submit</button>
							</div>
						</form>
					</div>
					<!-- /.box -->

				</div>
			</div>
			<!-- /.row --> </section>
			<!-- /.content -->

		</div>
	</div>
	<!-- /.content-wrapper -->


	<jsp:include page="includes/_footer.jsp"></jsp:include>

	<script
		src="${pageContext.request.contextPath}/themes/plugins/ckeditor/ckeditor.js"></script>
	<script>
		CKEDITOR.replace('txtAreaCKEditor', {
			/* extraPlugins : 'syntaxhighlight',  */
			toolbar : [
					[ 'Source', '-', 'Save', 'NewPage', 'DocProps', 'Preview',
							'Print', '-', 'Templates' ],
					[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord',
							'-', 'Undo', 'Redo' ],
					[ 'Find', 'Replace', '-', 'SelectAll', '-', 'SpellChecker',
							'Scayt' ],
					[ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea',
							'Select', 'Button', 'ImageButton', 'HiddenField' ],
					[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
							'Superscript', '-', 'RemoveFormat' ],
					[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
							'-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft',
							'JustifyCenter', 'JustifyRight', 'JustifyBlock',
							'-', 'BidiLtr', 'BidiRtl' ],
					[ 'Link', 'Unlink', 'Anchor' ],
					[ 'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley',
							'SpecialChar', 'PageBreak', 'Iframe' ],
					[ 'Styles', 'Format', 'Font', 'FontSize' ],
					[ 'TextColor', 'BGColor' ],
					[ 'Maximize', 'ShowBlocks', '-', 'About' ],
					[ 'Syntaxhighlight' ] ]
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
				$("#servicetype").on('change', function(){
					
					var id = $("#servicetype option:selected").val();
					
					if(id == 1){
						$("#service").prop('disabled', true);
						$("#servicepackage").prop('disabled', false);
					}else if(id == 2){
						$("#service").prop('disabled', fase);
						$("#servicepackage").prop('disabled', true);
					}
				}
		});
	</script>
</body>

</html>