<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>骏达设备登记</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/themes/${theme }/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/jquery-easyui/demo/demo.css">
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/jquery-easyui/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript" src="<%=path %>/FRAMEWORK/js/myjs.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path %>/FRAMEWORK/css/mycss.css">

	<script type="text/javascript">
	$(function(){
		$("#sele option[value='"+${page.size}+"']").attr("selected",true);
		$("#eidtASubjectWindow1").show();
		$('#tt').show();
	});
	
	function update(u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11){
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
		$('#u_11').val(u11);
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
	function sub(){
		var name = $('#filename').val();
		if(name=="" || name==null){
			alert("请选择文件");
		}else{
			show_hint(['in']);
			$('#ff').submit();
		}
	}
	</script>
	
  </head>
  
  <body>
    
    <div class="easyui-panel" title="骏达设备登记" style="width:100%;padding: 5px;display: none;" data-options="tools:'#tt'">
    
    <div class="kscx">
   		<div class="inp">
	    	<form id="ks" action="<%=path %>/bq!queryOfFenye" method="post">
	    		<div>
		    		<div>
			    		编号:<input name="id" type="text" value="${id }"/>
		    		</div>
		    		<div>
		    			PDA品牌：<input name="BPda" type="text" value="${BPda }"/>
		    		</div>
	    		</div>
	    		<div>
		    		<div>
			    		型号:<input name="BModel" type="text" value="${BModel }"/>
		    		</div>
	    			<div>
	    				组别:<input name="BType" type="text" value="${BType }"/>
	    			</div>
	    		</div>
	    		<div>
		    		<div>
			    		巴枪编号:<input name="BNum" type="text" value="${BNum }"/>
		    		</div>
	    			<div>
	    				SN:<input name="BSn" type="text" value="${BSn }"/>
	    			</div>
	    		</div>
	    		<div>
		    		<div>
			    		MAC地址:<input name="BMac" type="text" value="${BMac }"/>
		    		</div>
	    			<div>
	    			</div>
	    		</div>
	    	</form>
   		</div>
   		<div  class="btn">
   			<input type="submit" value="查询" onclick="$('.kscx .inp form').submit();"/>
   		</div>
   		<div style="clear:both;"></div>
    </div>
    
    
    <div style="margin-bottom: 5px;">
	    <table border="1" id="eidtASubjectWindow1" style="font-size: 12px;">
	    <tr>
	    	<th width="120">编号</th>
	    	<th width="80">PDA品牌</th>
	    	<th width="70">型号</th>
	    	<th>组别</th>
	    	<th>巴枪编号</th>
	    	<th>SN</th>
	    	<th>MAC</th>
	    	<th>IP地址</th>
	    	<th>分配WIFI</th>
	    	<th>WIFI密码</th>
	    	<th>维护人</th>
	    	<th>操作类型</th>
	    	<th>备注说明</th>
	    	<th>操作</th>
	    </tr>
	    <c:forEach items="${bqs}" var="bq">
	    <tr>
			<td width="">${bq.BId }</td>
			<td width="">${bq.BPda }</td>
			<td width="">${bq.BModel }</td>
			<td width="">${bq.BType }</td>
			<td width="">${bq.BNum }</td>
			<td width="">${bq.BSn }</td>
			<td width="">${bq.BMac }</td>
			<td width="">${bq.BIp }</td>
			<td width="">${bq.BWifi }</td>
			<td width="">${bq.BWifiPass }</td>
			<td width="">${bq.BServiceMan }</td>
			<td width="">${bq.BServiceType }</td>
			<td width="">${bq.BNote }</td>
			<td width="5%" align="center">
				<a onclick="update('${bq.BId }','${bq.BPda }','${bq.BModel }','${bq.BType }','${bq.BNum }','${bq.BSn }','${bq.BMac }','${bq.BIp }','${bq.BWifi }','${bq.BWifiPass }','${bq.BNote }')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" title="修改"></a>
				<a href="<%=path %>/bq!delete?id=${bq.BId}" onclick="return confirm('确定删除吗?')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-delete'" title="删除"></a>
			</td>
	    </tr>
	    </c:forEach>
	    </table>
	</div>
	
	<div class="easyui-panel" style="padding:5px;width: 100%;display: none;background-color: white;">
		<form id="f1" action="<%=path %>/bq!queryOfFenye?id=${id}&BPda=${BPda }&BModel=${BModel }&BType=${BType }&BNum=${BNum }&BSn=${BSn }&Bsac=${BMac }" method="post">
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
	
	<div id="u" class="easyui-window" title="修改" data-options="modal:true,closed:true" style="width:350px;height:auto;padding:10px;display: none;">
		<form action="<%=path %>/bq!update" method="post">
		<table border="0" class="table1">
			<tr>
				<td width="80">编号：</td>
				<td>
					<input id="u_1" name="bq.BId" type="text" style="width: 100%;" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td>PDA品牌：</td>
				<td>
					<input id="u_2" name="bq.BPda" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>型号：</td>
				<td>
					<input id="u_3" name="bq.BModel" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>组别：</td>
				<td>
					<input id="u_4" name="bq.BType" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>巴枪编号：</td>
				<td>
					<input id="u_5" name="bq.BNum" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>SN：</td>
				<td>
					<input id="u_6" name="bq.BSn" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>MAC：</td>
				<td>
					<input id="u_7" name="bq.BMac" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>IP地址：</td>
				<td>
					<input id="u_8" name="bq.BIp" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>分配WIFI：</td>
				<td>
					<input id="u_9" name="bq.BWifi" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>WIFI密码：</td>
				<td>
					<input id="u_10" name="bq.BWifiPass" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>备注说明：</td>
				<td>
					<input id="u_11" name="bq.BNote" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>操作类型</td>
				<td>
					<select name="bq.BServiceType" style="width:80px;">
						<option value="维护">维 护</option>
						<option value="注册">注 册</option>
						<option value="注销">注 销</option>
					</select>
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
	
	<div id="a" class="easyui-window" title="添加" data-options="modal:true,closed:true" style="width:350px;height:auto;padding:10px;display: none;">
		<form action="<%=path %>/bq!add" method="post">
		<table border="0" class="table1">
			<tr>
				<td width="80">PDA品牌：</td>
				<td>
					<input name="bq.BPda" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>型号：</td>
				<td>
					<input name="bq.BModel" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>组别：</td>
				<td>
					<input name="bq.BType" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>巴枪编号：</td>
				<td>
					<input name="bq.BNum" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>SN：</td>
				<td>
					<input name="bq.BSn" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>MAC：</td>
				<td>
					<input name="bq.BMac" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>IP地址：</td>
				<td>
					<input name="bq.BIp" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>分配WIFI：</td>
				<td>
					<input name="bq.BWifi" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>WIFI密码：</td>
				<td>
					<input name="bq.BWifiPass" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>备注说明：</td>
				<td>
					<input name="bq.BNote" type="text" style="width: 100%;"/>
				</td>
			</tr>
			<tr>
				<td>操作类型</td>
				<td>
					<select name="bq.BServiceType" style="width:80px;">
						<option value="注册">注 册</option>
						<option value="维护">维 护</option>
						<option value="注销">注 销</option>
					</select>
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
		<form id="ff" action="<%=path %>/bq!importExcel" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<td>Excel模板</td>
				<td onmousemove="$(this).css('background-color','red');" onmouseout="$(this).css('background-color','white');" style="cursor: pointer;" onclick="window.location.href='<%=path%>/files/import/zmz/骏达设备登记.xlsx';">
					下载模板
				</td>
			</tr>
			<tr>
				<td>导入Excel数据</td>
				<td>
					<input id="filename" type="file" name="fileExcel"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="导入" onclick="return sub()"/>	
				</td>
			</tr>
		</table>
		</form>
	</div>
	
	<jsp:include page="../hintModal.jsp"/>
  </body>
</html>
