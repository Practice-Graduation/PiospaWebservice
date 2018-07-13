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
	<c:set var="current_page_parent" value="page_product"></c:set>
	<c:set var="current_page" value="page_add_new_product"></c:set>
	<div class="wrapper">

		<jsp:include page="includes/_header.jsp"></jsp:include>
		<jsp:include page="includes/_sidebar.jsp"></jsp:include>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			<h1>
				Sản Phẩm <small>PioSpa</small>
			</h1>

			</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<!-- general form elements -->
					<div class="box box-primary ">
						<div class="box-header with-border">
							<h3 class="box-title title-name">THÊM SẢN PHẨM</h3>
						</div>
						<!-- /.box-header -->
						<!-- form start -->
						<form role="form" action="" method="post"
							enctype="multipart/form-data">
							<div class="box-body">
								<input type="hidden" name="productid" value="${productid }">
								<div class="form-group">
									<label for="productname">Tên Sản phẩm</label> <input
										type="text" class="form-control" id="productname"
										name="productname" placeholder="Nhập vào tên sản phẩm"
										value="${ productname}" required>
								</div>
								<div id="form-upload" class="form-group">
									<label for="brandimage">Hình</label>
									 <input type="text" class="form-control" id="brandImage" name="productimage" value="${productimage }" placeholder="Hình..." required readonly="readonly">
										 <input class="form-control" type="file" name="files[]">

									<button type="button" id="btn-upload" class="btn btn-primary">
										<i class="fa fa-cloud-upload"></i> Upload
									</button>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Nhóm sản phẩm</label> <select
										class="form-control" name="productgroup">
										<c:forEach items="${groups }" var="group">
											<option value="${group.productGroupId }"
												<c:if test="${ group.productGroupId eq productgroup}">selected</c:if>>${group.productGroupName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Nhãn hiệu sản phẩm</label> <select
										class="form-control" name="productbrand">
										<c:forEach items="${labels }" var="brand">
											<option value="${brand.productLabelId }"
												<c:if test="${ brand.productLabelId eq productbrand}">selected</c:if>>${brand.productLabelName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Nguồn gốc xuất xứ</label> <select
										class="form-control" name="productorigin">
										<c:forEach items="${origins }" var="origin">
											<option value="${origin.productOriginId }"
												<c:if test="${ origin.productOriginId eq productorigin}">selected</c:if>>${origin.productOriginName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">Đơn vị</label> <select
										class="form-control" name="productunit">
										<c:forEach items="${units }" var="unit">
											<option value="${unit.productUnitId }"
												<c:if test="${ unit.productUnitId eq productunit}">selected</c:if>>${unit.productUnitName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label for="productprice">Giá nhập</label> <input type="number"
										min="0" class="form-control" id="productcostprice"
										name="productprice" placeholder="Nhập vào đơn giá sản phẩm"
										required value="${ productcostprice}">
								</div>
								<div class="form-group">
									<label for="productname">Giá bán</label> <input type="number"
										min="0" class="form-control" id="productprice"
										name="productsaleprice"
										placeholder="Nhập vào đơn giá khuyến mãi" required
										value="${ productprice}">
								</div>
								<div class="form-group">
									<label for="productquantity">Số lượng</label> <input
										type="number" min="0" class="form-control"
										id="productquantity" name="productquantity"
										placeholder="Nhập vào số lượng sản phẩm" required
										value="${ productquantity}">
								</div>
								<div class="form-group">
									<label for="productquantity">Mô tả</label>
									<textarea id="txtAreaCKEditor" name="productdescription"
										class="form-control" rows="5" cols="">${productdescription }</textarea>
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
					<c:if test="${not empty message || message ne ''}">
						<script type="text/javascript">
							alert("${message}");
						</script>
					</c:if>

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
		$(document)
				.ready(
						function() {
							var files = [];
							$(document).on("change", "#form-upload",
									function(event) {
										files = event.target.files;

									});

							$(document).on("click", "#btn-upload", function() {
								processUpload();
							});
							function processUpload() {
								var oMyForm = new FormData();
								oMyForm.append("file", files[0]);
								$
										.ajax({
											dataType : 'json',
											url : "${pageContext.request.contextPath}/ajax/upload/one-file",
											data : oMyForm,
											type : "POST",
											enctype : 'multipart/form-data',
											processData : false,
											contentType : false,
											dataType : "text",
											success : function(result) {
												console
														.log("SUCCESS: ",
																result);
												$('#brandImage').val(result);
												$('#brandImage').attr('value', result);
												/* alert(result); */
											},
											error : function(e) {
												console.log("ERROR: ", e);
											}
										});
							}
						});
	</script>
</body>

</html>