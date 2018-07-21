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
<style type="text/css">
.searchable-container{margin:20px 0 0 0}
.searchable-container label.btn-default.active{background-color:#007ba7;color:#FFF}
.searchable-container label.btn-default{width:90%;border:1px solid #efefef;margin:5px; box-shadow:5px 8px 8px 0 #ccc;}
.searchable-container label .bizcontent{width:100%;}
.searchable-container .btn-group{width:90%}
.searchable-container .btn span.glyphicon{
    opacity: 0;
}
.searchable-container .btn.active span.glyphicon {
    opacity: 1;
}

</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<c:set var="currentPageParent" value="pageService" scope="request" />
	<c:set var="currentPage" value="pageAddNewServicePackage"
		scope="request" />
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
							<h3 class="box-title title-name">THÊM GÓI DỊCH VỤ</h3>
						</div>
						<!-- /.box-header -->

						<div class="col-md-12">
							<c:if test="${not empty message}">
								<div class="alert alert-warning alert-dismissible">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<h4>
										<i class="icon fa fa-warning"></i> Thất bại!
									</h4>
									${message }
								</div>
							</c:if>
							<c:if test="${not empty result and result eq 'create'}">
								<div class="alert alert-success alert-dismissible">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<h4>
										<i class="icon fa fa-check"></i> Thành công!
									</h4>
									Thêm dịch vụ <strong>${productname}</strong> thành công.
								</div>
							</c:if>
							<c:if test="${not empty result and result eq 'update'}">
								<div class="alert alert-success alert-dismissible">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<h4>
										<i class="icon fa fa-check"></i> Thành công!
									</h4>
									Cập nhật dịch vụ <strong>${productname}</strong> thành công.
								</div>
							</c:if>
							<%-- <c:if test="${not empty result and result eq false}">
						        <div class="alert alert-warning alert-dismissible">
					                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					                <h4><i class="icon fa fa-warning"></i> Thất bại!</h4> Thêm thành viên <strong>${account.fullname}</strong> thất bại.
					              </div>
					         </c:if> --%>
						</div>

						<!-- form start -->
						<form role="form" action="" method="post"
							enctype="multipart/form-data" autocomplete='off'>
							<div class="box-body">
								<input type="hidden" name="productid" value="${productid }">
								<div class="form-group">
									<label for="productname">Tên gói dịch vụ</label> <input
										type="text" class="form-control" id="productname"
										name="productname" placeholder="Nhập vào tên sản phẩm"
										value="${productname}" required>
								</div>
								<div id="form-upload" class="form-group">
									<label for="brandimage">Hình</label> <input type="text"
										class="form-control" id="brandImage" name="productimage"
										value="${productimage }" placeholder="Hình..." required
										readonly="readonly"> <input class="form-control"
										type="file" name="files[]">

									<button type="button" id="btn-upload" class="btn btn-primary">
										<i class="fa fa-cloud-upload"></i> Upload
									</button>
								</div>
								<div class="form-group">
									<label>Các dịch vụ của gói</label>
										<div class="row">
											
									        <div class="form-group">
									            <div class="col-sm-12 col-md-12 col-lg-12">
									                <input type="search" class="form-control" id="search" placeholder="Tìm kiếm dịch vụ..">
									            </div>
									        </div>
									    </div>
									<!-- DỊCH VỤ -->
									<div class="row">
										<div class="form-group">
											<div class="searchable-container">
												<c:forEach items="${services }" var="s">
													<div class="items col-xs-5 col-sm-5 col-md-3 col-lg-3">
									                    <div class="info-block block-info clearfix">
									                      <div class="square-box pull-left">
									                            <span class="glyphicon glyphicon-tags glyphicon-lg"></span>
									                        </div>
									                        <div data-toggle="buttons" class="btn-group bizmoduleselect">
									                        	
									                        	<c:set var="isSelected" value=""/>
									                        	<c:forEach var="temp" items="${var_ids}">
									                        			<c:if test="${s.serviceId eq temp }">
									                        					<c:set var="isSelected" value="selected"/>
									                        			</c:if>
									                        	</c:forEach> 
									                        
									                            <label class="btn btn-default ${ isSelected eq 'selected' ? 'active' : ''}">
									                                <div class="bizcontent">
									                                    	<input type="checkbox" ${ isSelected eq 'selected' ? 'checked="checked"' : ''}  name="var_ids" autocomplete="off" value="${s.serviceId }">
									                                   	 	<span class="glyphicon glyphicon-ok glyphicon-lg"></span>
									                                   
									                                    <h5 style="width: 100%;">${s.serviceName }</h5>
									                                </div>
									                            </label>
									                        </div>
									                    </div>
									                </div>
												</c:forEach>
											</div>
										</div>
									</div>
									
									<!-- END DỊCH VỤ -->
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

	<%-- <script
		src="${pageContext.request.contextPath}/themes/plugins/ckeditor/ckeditor.js"></script> --%>
	<!-- <script>
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
	</script> -->

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							// search
							$(function() {
							    $('#search').on('keyup', function() {
							        var pattern = $(this).val();
							        $('.searchable-container .items').hide();
							        $('.searchable-container .items').filter(function() {
							            return $(this).text().match(new RegExp(pattern, 'i'));
							        }).show();
							    });
							});
							// upload
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
												$('#brandImage').attr('value',
														result);
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