<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'welcom.jsp' starting page</title>
    
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
	
	<script>
	$(function(){
		var index = 0;
		var t = $('#tt');
		var tabs = t.tabs('tabs');
		setInterval(function(){
			t.tabs('select', tabs[index].panel('options').title);
			index++;
			if (index >= tabs.length){
				index = 0;
			}
		}, 3000);
	});
	</script>
	
  </head>
  
  <body style="padding: 6px;">
  	<div class="easyui-layout" data-options="border:false" style="width: 100%;height: 100%;">
  	
  		<div data-options="region:'west',split:true,border:false" style="width:50%;">
  			<div class="easyui-layout" data-options="fit:true,border:false">
  				<div class="easyui-layout" data-options="fit:true,border:false">
  					<div data-options="region:'north',split:true" title="公司简介" style="height:50%;padding:5px">
  						<div style="color:#FF6600;font-size: 26px;font-family: 隶书; margin-left:330px;">韵达简介</div>
  						<div style="font-size:16px;">
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;深圳市韵达速递有限公司（www.yundaex.com）成立于2003年8月12日，是一家国内民营的专业快递公司，主要静音国内快件寄递。
  						速递业务。公司秉承“准时送达、安全可靠”的服务准则，为客户提供“可限点、点到点、门到门、次日达”为主的递送服务。<br/>
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在全体深圳韵达人的共同努力下，一起走过了风雨兼程的十余年。在公司目前有在职员工3200余人，其中有一批相关稳固的中层管理团队，
  						也是韵达的服务更加标准化，同事也是深圳韵达文化的传承者。<br/>
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;随着国内电子商务的快速发展，结合公司整体战略的规划，深圳韵达建立了一级操作中心1个、分拨点55个、合作加盟商424余个，以此保证了公司对客户快件的操作及派送时效。
  						国内一、二线城市承诺在48小时内送大，三线城市或乡村也能在72小时内送达。为确保客户快件安全，公司于2007年底投入巨资，增添网络跟踪和查询系统，客户可实时在线查询每份快件的运行状态。<br>
  						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;深圳韵达聚集了大批快递、物流、电子商务业精英，运行行业先进的快递、物流管理系统，采用现代化的管理模式，力争行业领域先锋，确保民营企业品牌，
  						以客户为中心、为客户提供高效的服务、让客户始终满意是深圳韵达人永远追求的目标。公司坚持“以人为本”，兼顾加盟商、客户间的利益，为深圳韵达持续、健康、快速、稳定发展打下了坚实的基础。
  						</div>
  						
  					</div>
  					<div data-options="region:'center',split:true" title="公告" style="height:50%;padding:5px">
						

		        	<marquee behavior="alternate" direction="up" loop="-1" scrollamount="1" scrolldelay="1" style="font-size:16px;width: 100%;height: 100%;" onMouseOver="this.stop();" onMouseOut="this.start();">
	        		   一、关于管理制度<br/>
					     1、系统组5个开发管理制度<br/>
					     2、桌面组2个《电脑及周边管理制度》、《网络设备及周边管理制度》、《电脑系统安装管理制度》、《日常运维维护管理制度》<br/>
					     3、硬件组2个《分拨点搬迁管理制度》、《分拨点维护管理制度》、《分拨点网络迁移维护管理制度》<br/>
					    <br/>
					     二、时间节点<br/>
					     1、9月12日前按经理要求目标/流程/注意事项进行任务分解，5W2H自行安排。<br/>
					     2、档案提交分2部分<br/>
					     ①9月3日前提交5W2H工作任务时间节点；<br/>
					     ②9月12日前提交《目标/流程/注意事项》内容；<br/>
					  <br/>  
					
					Best Wishes<br/>
					以流程为导向，以服务为宗旨。<br/>
					*****************************************************<br/>
					信息与流程管理部-技术支持主管 徐迪军<br/>
					深圳市韵达速递有限公司<br/>
					邮箱：dijun_xu@szexpress.com.cn<br/>
					手机：+86-13925206957(短号63630)<br/>
					地址：广东省深圳市龙华新区观澜大道114号（交警中队正对面）<br/>
					*****************************************************<br/>
		        		
	        		</marquee>
					        
					        

					</div>
  				</div>
  			</div>
  		</div>
  		
  		<div data-options="region:'center',split:true,border:false" style="width:50%;">
			<div class="easyui-layout" data-options="fit:true,border:false">
  				<div class="easyui-layout" data-options="fit:true,border:false">
  					<div data-options="region:'north',split:true" title="新闻" style="height:50%;padding:5px">
  						
  						<div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
							<div title="Shirt1" style="padding:5px 5px;">
								<img src="FRAMEWORK/image/bag5.png">
							</div>
							<div title="Shirt2" style="padding:5px 5px;">
								<img src="FRAMEWORK/image/bag1.png">
							</div>
							<div title="Shirt3" style="padding:5px 5px;">
								<img src="FRAMEWORK/image/bag2.png">
							</div>
							<div title="Shirt4" style="padding:5px 5px;">
								<img src="FRAMEWORK/image/bag3.png">
							</div>
							<div title="Shirt5" style="padding:5px 5px;">
								<img src="FRAMEWORK/image/bag4.png">
							</div>
						</div>
  						
  						
  					</div>
  					<div data-options="region:'center',split:true" title="签到" style="height:50%;padding:5px;">
						<div class="easyui-calendar" style="width:70%;height:100%;display: inline-block;"></div> 
					</div>
  				
  				</div>
  			
  			
  			</div>
		</div>
  	</div>
  </body>
</html>
