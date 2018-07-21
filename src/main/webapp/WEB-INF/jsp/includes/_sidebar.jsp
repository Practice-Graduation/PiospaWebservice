<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${user.staffAvatar}" class="img-circle" alt="${user.fullname}">
        </div>
        <div class="pull-left info">
          <p>${user.fullname}</p>
          <a><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">Bảng hoạt động chính</li>
   
       <li class="treeview ${currentPageParent eq 'pageProduct' ? 'active':''}">
          <a href="#">
           <i class="fa fa-shopping-bag" aria-hidden="true"></i>
            <span>Sản phẩm</span>
             <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class="${currentPage eq 'pageAddNewProduct' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/add-product"><i class="fa fa-circle-o"></i>Thêm sản phẩm</a></li>
            <li class="${currentPage eq 'pageProductList' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/product"><i class="fa fa-circle-o"></i>Danh sách sản phẩm</a></li>
          </ul>
        </li>
        
        <li class="treeview ${currentPageParent eq 'pageService' ? 'active':''}">
          <a href="#">
           <i class="fa fa-bed" aria-hidden="true"></i>
            <span>Dịch vụ</span>
             <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class="${currentPage eq 'pageAddNewService' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/add-service"><i class="fa fa-circle-o"></i>Thêm dịch vụ</a></li>
             <li class="${currentPage eq 'pageAddNewServicePackage' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/add-service-package"><i class="fa fa-circle-o"></i>Thêm gói dịch vụ</a></li>
            <li class="${currentPage eq 'pageServiceList' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/service"><i class="fa fa-circle-o"></i>Danh sách dịch vụ</a></li>
            <li class="${currentPage eq 'pageServicePackageList' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/service-package"><i class="fa fa-circle-o"></i>Danh sách gói dịch vụ</a></li>
          </ul>
        </li>
        
        
        <li class="treeview ${currentPageParent eq 'pageServicePrice' ? 'active':''}">
          <a href="#">
           <i class="fa fa-usd" aria-hidden="true"></i>
            <span>Giá dịch vụ</span>
             <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class="${currentPage eq 'pageAddNewServicePrice' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/add-service-price"><i class="fa fa-circle-o"></i>Thêm giá dịch vụ</a></li>
            <li class="${currentPage eq 'pageServicePriceList' ? 'active':''}"><a href="${pageContext.request.contextPath }/admin/service-price"><i class="fa fa-circle-o"></i>Danh sách giá dịch vụ</a></li>
            
          </ul>
        </li>
        
      
       <li class="treeview ${currentPageParent eq 'pageOrder' ? 'active':''}">
          <a href="${pageContext.request.contextPath }/admin/order-list">
            <i class="fa fa-shopping-cart"></i>
            <span>Đơn hàng</span>
            <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class="${currentPage eq 'pageOrderList' ? 'active':''}"><a href="${pageContext.request.contextPath}/admin/order-list"><i class="fa fa-circle-o"></i> Danh sách đơn hàng</a></li>
          </ul>
        </li>
    
       <li class="treeview ${currentPageParent eq 'pageAccount' ? 'active':''}">
          <a href="${pageContext.request.contextPath}/admin/members">
            <i class="fa fa-users"></i> <span>Thành viên</span>
            <i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
            <li class="${currentPage eq 'pageAddNewAccount' ? 'active':''}"><a href="${pageContext.request.contextPath}/admin/add-new-account"><i class="fa fa-circle-o"></i> Thêm thành viên</a></li>
            <li class="${currentPage eq 'pageAccountList' ? 'active':''}"><a href="${pageContext.request.contextPath}/admin/members"><i class="fa fa-circle-o"></i> Danh sách thành viên</a></li>
          </ul>
        </li>
       
       <li class="treeview">
          <a href="${pageContext.request.contextPath}/swagger-ui.html" target="_blank">
            <i class="fa fa-external-link" aria-hidden="true"></i>
            <span>PioSpa Services</span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>