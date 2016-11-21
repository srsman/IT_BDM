<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.zs.tools.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>条码变更明细</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/themes/yellow/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/jquery-easyui/jquery.easyui.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/css/mycss.css">
	<script type="text/javascript">
	$(function(){
		$("#sele option[value='"+${page.size}+"']").attr("selected",true);
		$("#eidtASubjectWindow1").show();
		$('#tt').show();
	});
	
	function update(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10){
		$('#u').window('open');
		$('#u_1').val(u1);
		$('#u_2').val(u2);
		$('#u_3').val(u3);
		$('#u_4').val(u4);
		$('#u_5').val(u5);
		$('#u_6').val(u6);
		$('#u_7').val(u7);
		$('#u_8').val(u8);
		$('#u_9').val(u9);
		$('#u_10').val(u10);
	}
	function page(no,cz){
		var num1=$('#page').val();
		if(cz==1){//上下页
			$('#page').val(num1*1+no*1);
		}else if(cz==2){//首末页
			$('#page').val(no);
		}else{
		}
		if($('#page').val()*1<1){
			$('#page').val(1);
		}else if($('#page').val()*1>${page.pageMax}*1){
			$('#page').val(${page.pageMax});
		}
		$('#f1').submit();
	}
	
	</script>
	
  </head>
  
  <body>
    
    <div class="easyui-panel" title="条码变更明细" style="padding: 5px;display: none;" data-options="tools:'#tt'">
    
    <div style="background-color:white;margin-bottom: 5px;padding: 5px;border: 1px solid #224466; ">
    	快速查询
    	<br/>
    	<form action="<%=path %>/pdachange!queryOfFenye" method="post">
    		编号:<input name="id" type="text" value="${id }"/>
    		&nbsp;&nbsp;&nbsp;&nbsp;
    		维护周：<input name="weekNum" type="text" value="${weekNum }"/>
    		&nbsp;&nbsp;&nbsp;&nbsp;
    		SN号：<input name="sn" type="text" value="${sn }"/>
    		&nbsp;&nbsp;&nbsp;&nbsp;
    		变更日期:<input name="dates" type="date" value="${dates }"/>
    		~
    		<input name="datee" type="date" value="${datee }"/>
    		<br/>
    		<input type="submit" value="查询"/>
    	</form>	
    </div>
    
    <div style="margin-bottom: 5px;">
    
    <table border="1" id="eidtASubjectWindow1" style="font-size: 12px;">
    <tr>
    	<th width="130px">编号</th>
    	<th>提交日期</th>
    	<th>维护周</th>
    	<th>申请人</th>
    	<th>单据</th>
    	<th>原条码</th>
    	<th>新条码</th>
    	<th>SN号</th>
    	<th>负责IT</th>
    	<th>变更日期</th>
    	<th>领枪人</th>
    	<th>结果</th>
    	<th>备注</th>
    	<th>操作</th>
    </tr>
    <c:forEach items="${pdas}" var="pda">
    <tr>
		<td width="">${pda.CId }</td>
		<td width=""><fmt:formatDate value="${pda.CDate}" pattern="yyyy/M/d" /></td>
		<td width="">${pda.CWeek }</td>
		<td width="">${pda.CMan }</td>
		<td width="">${pda.CPage }</td>
		<td width="">${pda.CNewNum }</td>
		<td width="">${pda.COldNum }</td>
		<td width="">${pda.CSn }</td>
		<td width="">${pda.CIt }</td>
		<td width=""><fmt:formatDate value="${pda.CChangeDate }" pattern="yyyy/M/d"/></td>
		<td width="">${pda.CResult }</td>
		<td width="">${pda.CReceiveMan }</td>
		<td width="">${pda.CNote }</td>
		<td width="5%" align="center">
			<a onclick="update('${pda.CId }','${pda.CDate }','${pda.CMan }','${pda.CPage }','${pda.CNewNum }','${pda.COldNum }','${pda.CSn }','${pda.CResult}','${pda.CReceiveMan }','${pda.CNote }' )" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" title="修改"></a>
			<a href="<%=path %>/pdachange!delete?id=${pda.CId}" onclick="return confirm('确定删除吗?')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-delete'" title="删除"></a>
		</td>
    </tr>
    </c:forEach>
    </table>
	</div>
	
	<div class="easyui-panel" style="padding:5px;width: 100%;display: none;">
		<form id="f1" action="<%=path %>/pdachange!queryOfFenye?id=${id}&weekNum=${weekNum }&sn=${sn }&dates=${dates}&datee=${datee}" method="post">
		<select id="sele" style="float: left;margin-top: 3px;margin-left: 5px;" name="page.size" onchange="$('#f1').submit();">
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="15">15</option>
		</select>
		
		<span style="float: left;margin-left: 5px;">
		<span style="color: #A5A5A5;">|</span>
		<a onclick="page(1,2)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-last'" title="首页"></a>
		<a onclick="page(-1,1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-l'" title="上一页"></a>
		<span style="color: #A5A5A5;">|</span>
		</span>
		
		<span style="float: left;margin-top: 3px;margin-left: 5px;">
		<input id="page" name="page.pageOn" type="number" style="width: 50px;height: 20px;" value="${page.pageOn }" min="1" max="${page.pageMax }" onchange="$('#f1').submit();"/>
		</span>
		
		<span style="float: left;margin-top: 5px;margin-left: 5px;">/${page.pageMax }</span>
		
		<span style="float: left;margin-left: 5px;">
		<span style="color: #A5A5A5;">|</span>
		<a onclick="page(1,1)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-n'" title="下一页"></a>
		<a onclick="page('${page.pageMax}',2)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-next'" title="末页"></a>
		</span>
		</form>
	</div>
	</div>
	
	<div id="u" class="easyui-window" title="修改" data-options="modal:true,closed:true" style="width:500px;height:auto;padding:10px;display: none;">
		<form action="<%=path %>/pdachange!update" method="post">
		<table border="0" class="table1">
			<tr>
				<td width="120">编号：</td>
				<td>
					<input id="u_1" name="pda.CId" type="text" style="width: 100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>提交日期：</td>
				<td>
					<input id="u_2" name="pda.CDate" type="date" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>申请人：</td>
				<td>
					<input id="u_3" name="pda.CMan" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>单据：</td>
				<td>
					<input id="u_4" name="pda.CPage" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>原条码：</td>
				<td>
					<input id="u_5" name="pda.COldNum" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>新条码：</td>
				<td>
					<input id="u_6" name="pda.CNewNum" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>SN号：</td>
				<td>
					<input id="u_7" name="pda.CSn" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>结果：</td>
				<td>
					<input id="u_8" name="pda.CResult" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>领枪人：</td>
				<td>
					<input id="u_9" name="pda.CReceiveMan" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					<input id="u_10" name="pda.CNote" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="easyui-linkbutton" type="submit" style="width: 95%;padding: 5px;" value="提交"/>
				</td>			
			</tr>
		</table>
		</form>
	</div>
	
	<div id="a" class="easyui-window" title="添加" data-options="modal:true,closed:true" style="width:500px;height:auto;padding:10px;display: none;">
		<form action="<%=path %>/pdachange!add" method="post">
		<table border="0" class="table1">
			<tr>
				<td width="120">提交日期：</td>
				<td>
					<input name="pda.CDate" type="date" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>申请人：</td>
				<td>
					<input name="pda.CMan" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>单据：</td>
				<td>
					<input name="pda.CPage" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>原条码：</td>
				<td>
					<input name="pda.COldNum" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>新条码：</td>
				<td>
					<input name="pda.CNewNum" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>SN号：</td>
				<td>
					<input name="pda.CSn" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>结果：</td>
				<td>
					<input name="pda.CResult" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>领枪人：</td>
				<td>
					<input name="pda.CReceiveMan" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					<input name="pda.CNote" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input class="easyui-linkbutton" type="submit" style="width: 95%;padding: 5px;" value="提交"/>
				</td>			
			</tr>
		</table>
		</form>
	</div>
	
	<div id="tt" style="display: none;">
		<a class="icon-zs-import" onclick="$('#in').window('open')" style="margin-left: 10px;" title="excel导入"></a>
		<a class="icon-add" onclick="$('#a').window('open')" style="margin-left: 10px;" title="添加"></a>
	</div>
	
	<div id="in" class="easyui-window" title="数据导入" data-options="modal:true,closed:true" style="width:400px;height:auto;padding:10px;display: none;overflow-x:hidden;">
		<form action="<%=path %>/pdachange!importExcel" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>Excel模板</td>
				<td onmousemove="$(this).css('background-color','red');" onmouseout="$(this).css('background-color','white');" style="cursor: pointer;" onclick="window.location.href='<%=Constant.WEB_URL %>/files/import/xtz/巴枪送修坂田.xlsx';">
					下载模板
				</td>
			</tr>
			<tr>
				<td>导入Excel数据</td>
				<td>
					<input type="file" name="fileExcel"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="导入"/>	
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	
  </body>
</html>
