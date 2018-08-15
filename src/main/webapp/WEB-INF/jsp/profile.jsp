<%@ page errorPage="//WEB-INF/jsp/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Thành viên: ${account.fullname} | PioSpa Admin</title>
<jsp:include page="includes/_head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<c:set var="currentPageParent" value="pageAccount" scope="request"/>
<c:set var="currentPage" value="pageAccountProfile" scope="request"/>
<div class="wrapper">


<jsp:include page="includes/_header.jsp"/>
<jsp:include page="includes/_sidebar.jsp"/>

 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        ${account.fullname}
        <small>Thành viên</small>
      </h1>
      
    </section>

	
	<!-- Main content -->
    <section class="content">
      <div class="row">
		
		
		<div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <img class="profile-user-img img-responsive img-circle" src="${account.staffAvatar}" alt="User profile picture">

              <h3 class="profile-username text-center">${account.fullname}</h3>

              <p class="text-muted text-center">
              		<c:if test="${account.isAdmin eq 1}"><span class="label label-success">Quản trị viên</span></c:if>
	                <c:if test="${account.isAdmin eq 0}"><span class="label label-warning">Nhân viên</span></c:if>
	          </p>
              
              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Tên tài khoản</b> <a class="pull-right">${account.account}</a>
                </li>
               <%--  <li class="list-group-item">
                  <b>Ngày lập</b> <a class="pull-right"><fmt:formatDate pattern="dd '/' MM '/' yyyy" value="${account.createdAt}"/></a>
                </li> --%>
               <%--  <li class="list-group-item">
                  <b>Tình trạng</b> 
                  <a class="pull-right">
                  	<c:if test="${account.isActive eq 1}"><span class="label label-success">Hoạt động</span></c:if>
	                <c:if test="${account.isActive eq 0}"><span class="label label-warning">Khóa</span></c:if>
                  </a>
                </li> --%>
              </ul>
		     
		     <c:if test="${account.isAdmin eq 0 }">
		           <button id="btn-delete-account" type="button" data-toggle="modal" data-target="#modal-delete-account" class="btn btn-danger btn-block"><b>Xóa tài khoản</b></button>
           
		     </c:if>
		  
            
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
         
        </div>
		
		
		<div class="col-md-9">
		  <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="${empty result_reset_password ? 'active':''}"><a href="#infomation" data-toggle="tab">Thông tin cá nhân</a></li>
              <li class="${not empty result_reset_password ? 'active':''}"><a href="#reset-password" data-toggle="tab">Bảo mật</a></li>
            </ul>
            <div class="tab-content">
            
              <div class="tab-pane${empty result_reset_password ? ' active':''}" id="infomation">
              
				<form class="form-horizontal" action="${pageContext.request.contextPath}/admin/profile/${account.staffId}" method="post">
				
				<div class="form-group">
	                  <div class="col-md-12">
				        <c:if test="${result}">
					        <div class="alert alert-success alert-dismissible">
					        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					        	<h4><i class="icon fa fa-check"></i> Thành công!</h4> Cập nhật thông tin tài khoản <strong>${account.fullname}</strong> thành công.
					        </div>
				         </c:if>
				         <c:if test="${result eq false}">
					        <div class="alert alert-warning alert-dismissible">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				                <h4><i class="icon fa fa-warning"></i> Thất bại!</h4> Cập nhật thông tin tài khoản <strong>${account.fullname}</strong> thất bại.
				              </div>
				         </c:if>
				       </div>
			       </div>
				
				
                  <div class="form-group">
                    
                    <label for="inputLastName" class="col-sm-2 control-label">Họ và tên</label>
                    <div class="col-sm-4">
                      <input type="text" class="form-control" id="inputLastName" name="account_name" value="${account.fullname}" placeholder="Nhập và tên">
                    </div>
                  </div>
                
                  <div class="form-group">
                    <label for="inputPhone" class="col-sm-2 control-label">Điện thoại</label>

                    <div class="col-sm-10">
                      <input type="text" class="form-control" id="inputPhone" name="account_phone" value="${account.phone}" placeholder="Nhập số điện thoại">
                    </div>
                   </div>
              
              	  <div class="form-group">
                    <label for="inputBiography" class="col-sm-2 control-label">Quyền</label>

                    <div class="col-sm-10">
                      <select class="form-control" name="account_role">
	                    <option value="1" ${account.isAdmin eq 1 ? 'selected':''}>Quản trị viên</option>
	                    <option value="0" ${account.isAdmin eq 0 ? 'selected':''}>Nhân viên</option>
	                  </select>
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputBiography" class="col-sm-2 control-label">Hình đại diện</label>

                    <div id="form-upload" class="col-sm-10">
	                  <input type="hidden" class="form-control" id="account_avatar" name="account_avatar" value="${account.staffAvatar}" placeholder="Hình đại diện">
                      <input type="file" id="file-data" class="form-control-file" name="files[]" aria-describedby="fileHelp">
                      <button type="button" id="btn-upload" class="btn btn-primary"><i class="fa fa-cloud-upload" ></i> Upload</button>
                    </div>
                  </div>
                  
                  <hr/>
                  
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" class="btn btn-primary btn-lg"><i class="fa fa-floppy-o""></i> Lưu</button>
                    </div>
                  </div>
                </form>
              </div>
              <!-- /.tab-pane -->
              
              <div class="tab-pane${not empty result_reset_password ? ' active':''}" id="reset-password">
                <form class="form-horizontal" action="${pageContext.request.contextPath}/admin/profile/${account.staffId}/reset-password" method="post">
                  <div class="form-group">
	                  <div class="col-md-12">
				        <c:if test="${result_reset_password}">
					        <div class="alert alert-success alert-dismissible">
					        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
					        	<h4><i class="icon fa fa-check"></i> Thành công!</h4> Cập nhật mật khẩu tài khoản <strong>${account.fullname}</strong> thành công.
					        </div>
				         </c:if>
				         <c:if test="${result_reset_password eq false}">
					        <div class="alert alert-warning alert-dismissible">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				                <h4><i class="icon fa fa-warning"></i> Thất bại!</h4> Cập nhật mật khẩu tài khoản <strong>${account.fullname}</strong> thất bại.
				              </div>
				         </c:if>
				       </div>
			       </div>
                  
                  <div class="form-group">
                    <label for="inputNewPassword" class="col-sm-3 control-label">Mật khẩu hiện tại</label>

                    <div class="col-sm-9">
                      <input type="password" class="form-control" id="inputOldPassword" name="current_password" placeholder="Nhập mật khẩu hiện tại">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputNewPassword" class="col-sm-3 control-label">Mật khẩu mới</label>

                    <div class="col-sm-9">
                      <input type="password" class="form-control" id="inputNewPassword" name="new_password" placeholder="Nhập mật khẩu mới">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputComfirmPassword" class="col-sm-3 control-label">Nhập lại</label>

                    <div class="col-sm-9">
                      <input type="password" class="form-control" id="inputComfirmPassword" name="confirm_password" placeholder="Nhập lại mật khẩu">
                    </div>
                  </div>
                  
                  <hr>
                  
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                      <button type="submit" class="btn btn-primary btn-lg"><i class="fa fa-floppy-o""></i> Lưu</button>
                    </div>
                  </div>
                </form>
              </div>
              <!-- /.tab-pane -->
              
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
		
		</div>
		
		
		
	  </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
   <div id="modal-delete-account" class="modal fade">
	  <div class="modal-dialog" role="document">
	  <form action="${pageContext.request.contextPath}/admin/delete-account" method="post">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Xóa tài khoản</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <p>Bạn chắc chắn muốn xóa tài khoản <strong>${account.fullname}</strong></p>
	        <input type="hidden" name="id_account" value="${account.staffId}">
	      </div>
	      <div class="modal-footer">
	        <button type="submit" class="btn btn-danger">Xóa</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
	      </div>
	    </div>
	  </form>
	  </div>
	</div>
    
	</div>
  <!-- /.content-wrapper -->
	<jsp:include page="includes/_footer.jsp"/>
	</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#inputOldPassword').attr('value', '');
	$('#inputNewPassword').attr('value', '');
	$('#inputComfirmPassword').attr('value', '');
	var files = [];
	$(document).on("change", "#form-upload", function(event) {
                 files=event.target.files;
                })

	$(document).on("click", "#btn-upload", function() {
                	processUpload();
                })	
	
	function processUpload() {
        var oMyForm = new FormData();
        oMyForm.append("file", files[0]);
       	$.ajax({dataType : 'json',
              url : "${pageContext.request.contextPath}/ajax/upload/one-file",
              data : oMyForm,
              type : "POST",
              enctype: 'multipart/form-data',
              processData: false, 
              contentType:false,
              dataType:"text",
              success: function(result) {
            	  console.log("SUCCESS: ", result);
            	  $('#account_avatar').val(result);
            	  $('#account_avatar').attr("value", result);
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