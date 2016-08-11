<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/jquery-easyui/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/css/mycss.css">

	<script type="text/javascript">
	$(function(){
		//$('#tt').tree('collapseAll');
	});
	</script>
	
  </head>
  
<body style="padding: 0px;background-color: ;height: 100%;">

<div class="easyui-accordion" style="width:100%;height:100%;" data-options="fillSpace:true,fit: true,animate: true,selected:false">
	<div title="数据中心" data-options="iconCls:'icon-add'" style="overflow:auto;padding:10px;">
		<ul id="tt" class="easyui-tree" data-options="animate:true,lines:true">
        <li><span>数据与流程管理部</span>
        	<ul>
        		<li><span>硬件组</span>
        			<ul>
						<li><span><a href="<%=path %>/fbd_asdl!queryOfFenyeAsdl?cz=yes" target="right">分拨点宽带登记ASDL</a></span></li>
						<li><span><a href="<%=path %>/fbd_m!queryOfFenyeM?cz=yes" target="right">分拨点监控登记</a></span></li>
						<li><span><a href="<%=path %>/fbd_c!queryOfFenyeC?cz=yes" target="right">分拨点电脑登记</a></span></li>
						<li><span><a href="<%=path %>/fbd_ll!queryOfFenyeLL?cz=yes" target="right">分拨点监控材料申请清单</a></span></li>
						<li><span><a href="<%=path %>/sim!queryOfFenyeSIM?cz=yes" target="right">SIM费用报销</a></span></li>
						<li><span><a href="<%=path %>/goods!queryOfFenyeGoods?cz=yes" target="right">内件收发登记</a></span></li>
						<li><span><a href="<%=path %>/go!queryOfFenyeGo?cz=yes" target="right">外出登记</a></span></li>        			
        			</ul>
        		</li>
        		<li><span>系统组</span>
        			<ul>
						<li><span><a href="<%=path %>/site!queryOfFenye?cz=yes" target="right">站点资料</a></span></li>
						<li><span><a href="<%=path %>/branches!queryOfFenye?cz=yes" target="right">二级网点资料</a></span></li>
						<li><span><a href="<%=path %>/fbd_c!queryOfFenyeC?cz=yes" target="right">哲盟账号</a></span></li>
						<li><span><a href="<%=path %>/fbd_ll!queryOfFenyeLL?cz=yes" target="right">哲盟数据检查登记</a></span></li>
						<li><span><a href="<%=path %>/sim!queryOfFenyeSIM?cz=yes" target="right">停止站点</a></span></li>
						<li><span><a href="<%=path %>/goods!queryOfFenyeGoods?cz=yes" target="right">哲盟开发会议纪要</a></span></li>
						<li><span><a href="<%=path %>/go!queryOfFenyeGo?cz=yes" target="right">开发效率</a></span></li> 
						<li><span><a href="<%=path %>/go!queryOfFenyeGo?cz=yes" target="right">日程表</a></span></li> 
						<li><span><a href="<%=path %>/go!queryOfFenyeGo?cz=yes" target="right">系统开发项目优先级清单</a></span></li> 
						<li><span><a href="<%=path %>/go!queryOfFenyeGo?cz=yes" target="right">哲盟异常数据跟进</a></span></li> 
        			</ul>
        		</li>
        		<li><span>桌面组</span></li>
        		<li><span>开发组</span></li>
        	</ul>
        </li>
        </ul>
	</div>
	<div title="通讯录" data-options="iconCls:'icon-print'" style="padding:10px;">
		<ul id="tt" class="easyui-tree" data-options="animate:true,lines:true">
        <li><span><a href="<%=path %>/section!gotoAdd" target="right">添加</a></span></li>
        <li><span>移动</span></li>
        <li><span><a href="<%=path%>/index!contacts" target="right">查看</a></span></li>
    </ul>
	</div>
	<div title="数据分析" data-options="iconCls:'icon-large-shapes'" style="padding:10px;">
		<a href="<%=path %>/manual!queryDx" target="right" class="easyui-linkbutton c1" style="width:100%">电信表数据编辑</a>
		<a href="<%=path %>/manual!queryYd" target="right" class="easyui-linkbutton c1" style="width:100%;margin-top: 2px;">移动明细表数据编辑</a>
		<a href="<%=path %>/manual!queryHz" target="right" class="easyui-linkbutton c1" style="width:100%;margin-top: 2px;">移动汇总表数据编辑</a>
		<a href="<%=path %>/manual!queryDetail" target="right" class="easyui-linkbutton c1" style="width:100%;margin-top: 2px;">最终表数据编辑</a>
	</div>
	<div title="权限管理" data-options="iconCls:'icon-large-smartart'" style="padding:10px;">
		<ul id="tt" class="easyui-tree" data-options="animate:true,lines:true">
      		<li><span><a href="<%=path %>/users!queryOfFenye?cz=yes" target="right">用户管理</a></span></li>
      		<li><span><a href="<%=path %>/role!queryOfFenye?cz=yes" target="right">角色管理</a></span></li>
        </ul>
	</div>
	<div title="个人中心" data-options="iconCls:'icon-large-smartart'" style="padding:10px;">
		<ul id="tt" class="easyui-tree" data-options="animate:true,lines:true">
       		<li><span><a href="<%=path %>/timeline!query" target="right">时间轴</a></span></li>
        </ul>
	</div>
</div>

</body>
</html>
