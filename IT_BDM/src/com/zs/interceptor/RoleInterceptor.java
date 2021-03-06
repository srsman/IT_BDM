package com.zs.interceptor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zs.action.xtz.SiteAction;
import com.zs.dao.IBaseDaoOfSpring;
import com.zs.entity.FbdAsdl;
import com.zs.entity.FbdComputer;
import com.zs.entity.FbdListLink;
import com.zs.entity.FbdMonitoring;
import com.zs.entity.GoOut;
import com.zs.entity.Goods;
import com.zs.entity.Permission;
import com.zs.entity.Role;
import com.zs.entity.RolePermission;
import com.zs.entity.Sim;
import com.zs.entity.Timeline;
import com.zs.entity.Users;
import com.zs.entity.XtBranches;
import com.zs.entity.XtSite;
import com.zs.entity.XtZmData;
import com.zs.entity.XtZmNumber;
import com.zs.service.IService;
import com.zs.tools.NameOfDate;

/**
 * @author 张顺
 *<br>2016年9月2日11:37:48
 *<br>权限拦截器（前拦截器）
 */
public class RoleInterceptor extends AbstractInterceptor{

	
	IService ser;
	HttpServletRequest request;
	HttpServletResponse response;
	Map session;
	String path;
	String reqPamrs;
	Object user;
	private static final String PRO_NAME="/IT_BDM";
	private Logger logger=Logger.getLogger(RoleInterceptor.class);
	
	
	
	public IService getSer() {
		return ser;
	}
	public void setSer(IService ser) {
		this.ser = ser;
	}

	private void allInit(ActionInvocation arg0) {
		// 取得请求相关的ActionContext实例  
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
    	//获取其他信息
		ActionContext ctx = arg0.getInvocationContext();  
        session = ctx.getSession();  
        //获得url
        path = request.getRequestURI();//url
        reqPamrs = request.getQueryString();//后面的参数
        //获取登录者信息
        user =session.get("user"); 
	}
	
	/**
	 * 权限判断方法
	 * @param arg0
	 * @param r
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	private String roleControl(ActionInvocation arg0,Role r,String pid) throws Exception {
		List<RolePermission> rps=ser.find("from RolePermission where RId=? and PId=?", new String[]{r.getRId(),pid});
		if(rps.size()>0){
			close();
			return arg0.invoke();
		}else {
			response.sendRedirect("error2.jsp");
			close();
			return null;
		}
	}
	
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		allInit(arg0);
		//以下是权限控制的核心代码
		if (user==null) {//将登录的url排除在外
			if ((PRO_NAME+"/login!login").equals(path)) {
				close();
				return arg0.invoke();
			}else {
				response.sendRedirect("error1.jsp");
				close();
				return null;
			}
		}else{ 
			Users u=(Users)user;
			Role r=u.getR();
			
			if ((PRO_NAME+"/fbd_asdl!queryOfFenyeAsdl").equals(path)) {//硬件组-ASDL-分页
				return roleControl(arg0, r, "1");
			}else if ((PRO_NAME+"/fbd_asdl!deleteAsdl").equals(path)) {//硬件组-ASDL-删除
				return roleControl(arg0, r, "3");
			}else if ((PRO_NAME+"/fbd_asdl!addAsdl").equals(path)) {//硬件组-ASDL-添加
				return roleControl(arg0, r, "2");
			}else if ((PRO_NAME+"/fbd_asdl!updateAsdl").equals(path)) {//硬件组-ASDL-修改
				return roleControl(arg0, r, "4");
			}
			
			else if ((PRO_NAME+"/fbd_m!queryOfFenyeM").equals(path)) {//硬件组-分拨点监控——分页
				return roleControl(arg0, r, "5");
			}else if ((PRO_NAME+"/fbd_m!deleteM").equals(path)) {//硬件组-分拨点监控——删除
				return roleControl(arg0, r, "6");
			}else if ((PRO_NAME+"/fbd_m!updateM").equals(path)) {//硬件组-分拨点监控——修改
				return roleControl(arg0, r, "7");
			}else if ((PRO_NAME+"/fbd_m!addM").equals(path)) {//硬件组-分拨点监控——添加
				return roleControl(arg0, r, "8");
			}
			
			else if ((PRO_NAME+"/fbd_c!queryOfFenyeC").equals(path)) {//硬件组-分拨点电脑——分页
				return roleControl(arg0, r, "9");
			}else if ((PRO_NAME+"/fbd_c!deleteC").equals(path)) {//硬件组-分拨点电脑——删除
				return roleControl(arg0, r, "10");
			}else if ((PRO_NAME+"/fbd_c!updateC").equals(path)) {//硬件组-分拨点电脑——修改
				return roleControl(arg0, r, "11");
			}else if ((PRO_NAME+"/fbd_c!addC").equals(path)) {//硬件组-分拨点电脑——添加
				return roleControl(arg0, r, "12");
			}
			
			else if ((PRO_NAME+"/fbd_ll!queryOfFenyeLL").equals(path)) {//硬件组-分拨点监控材料清单——查看
				return roleControl(arg0, r, "13");
			}else if ((PRO_NAME+"/fbd_ll!deleteLL").equals(path)) {//硬件组-分拨点监控材料清单——删除
				return roleControl(arg0, r, "14");
			}else if ((PRO_NAME+"/fbd_ll!updateLL").equals(path)) {//硬件组-分拨点监控材料清单——修改
				return roleControl(arg0, r, "15");
			}else if ((PRO_NAME+"/fbd_ll!addLL").equals(path)) {//硬件组-分拨点监控材料清单——添加
				return roleControl(arg0, r, "16");
			}
			
			else if ((PRO_NAME+"/sim!queryOfFenyeSIM").equals(path)) {//硬件组-SIM——查看
				return roleControl(arg0, r, "17");
			}else if ((PRO_NAME+"/sim!deleteSIM").equals(path)) {//硬件组-SIM——删除
				return roleControl(arg0, r, "18");
			}else if ((PRO_NAME+"/sim!updateSIM").equals(path)) {//硬件组-SIM——修改
				return roleControl(arg0, r, "19");
			}else if ((PRO_NAME+"/sim!addSIM").equals(path)) {//硬件组-SIM——添加
				return roleControl(arg0, r, "20");
			}
			
			else if ((PRO_NAME+"/goods!queryOfFenyeGoods").equals(path)) {//硬件组-内件收发——查看
				return roleControl(arg0, r, "21");
			}else if ((PRO_NAME+"/goods!deleteGoods").equals(path)) {//硬件组-内件收发——删除
				return roleControl(arg0, r, "22");
			}else if ((PRO_NAME+"/goods!updateGoods").equals(path)) {//硬件组-内件收发——修改
				return roleControl(arg0, r, "23");
			}else if ((PRO_NAME+"/goods!addGoods").equals(path)) {//硬件组-内件收发——添加
				return roleControl(arg0, r, "24");
			}
			
			else if ((PRO_NAME+"/go!queryOfFenyeGo").equals(path)) {//硬件组-外出登记——查看
				return roleControl(arg0, r, "25");
			}else if ((PRO_NAME+"/go!deleteGo").equals(path)) {//硬件组-外出登记——删除
				return roleControl(arg0, r, "26");
			}else if ((PRO_NAME+"/go!updateGo").equals(path)) {//硬件组-外出登记——修改
				return roleControl(arg0, r, "27");
			}else if ((PRO_NAME+"/go!addGo").equals(path)) {//硬件组-外出登记——添加
				return roleControl(arg0, r, "28");
			}
			
			else if ((PRO_NAME+"/outRegister!queryOfFenye").equals(path)) {//硬件组-新外出登记——查看
				return roleControl(arg0, r, "2001");
			}else if ((PRO_NAME+"/outRegister!delete").equals(path)) {//硬件组-新外出登记——删除
				return roleControl(arg0, r, "2002");
			}else if ((PRO_NAME+"/outRegister!update").equals(path)) {//硬件组-新外出登记——修改
				return roleControl(arg0, r, "2003");
			}else if ((PRO_NAME+"/outRegister!add").equals(path)) {//硬件组-新外出登记——添加
				return roleControl(arg0, r, "2004");
			}
			
			//-------------------------------硬件组结束,系统组开始------------------------------------------------
			else if ((PRO_NAME+"/site!queryOfFenye").equals(path)) {//站点资料查询
				return roleControl(arg0, r, "29");
			}else if ((PRO_NAME+"/site!delete").equals(path)) {//站点资料删除
				return roleControl(arg0, r, "30");
			}else if ((PRO_NAME+"/site!delete").equals(path)) {//站点资料修改
				return roleControl(arg0, r, "31");
			}else if ((PRO_NAME+"/site!delete").equals(path)) {//站点资料添加
				return roleControl(arg0, r, "32");
			}
			
			else if ((PRO_NAME+"/branches!queryOfFenye").equals(path)) {//二级站点资料查看
				return roleControl(arg0, r, "33");
			}else if ((PRO_NAME+"/branches!delete").equals(path)) {//二级站点资料删除
				return roleControl(arg0, r, "34");
			}else if ((PRO_NAME+"/branches!update").equals(path)) {//二级站点资料修改
				return roleControl(arg0, r, "35");
			}else if ((PRO_NAME+"/branches!add").equals(path)) {//二级站点资料添加
				return roleControl(arg0, r, "36");
			}
			
			else if ((PRO_NAME+"/zmn!queryOfFenye").equals(path)) {//哲盟账号申请登记查看
				return roleControl(arg0, r, "37");
			}else if ((PRO_NAME+"/zmn!delete").equals(path)) {//哲盟账号申请登记删除
				return roleControl(arg0, r, "38");
			}else if ((PRO_NAME+"/zmn!update").equals(path)) {//哲盟账号申请登记修改
				return roleControl(arg0, r, "39");
			}else if ((PRO_NAME+"/zmn!add").equals(path)) {//哲盟账号申请登记添加
				return roleControl(arg0, r, "40");
			}
			
			else if ((PRO_NAME+"/zmd!queryOfFenye").equals(path)) {//哲盟数据检查登记查看
				return roleControl(arg0, r, "41");
			}else if ((PRO_NAME+"/zmd!delete").equals(path)) {//哲盟数据检查登记删除
				return roleControl(arg0, r, "42");
			}else if ((PRO_NAME+"/zmd!update").equals(path)) {//哲盟数据检查登记修改
				return roleControl(arg0, r, "43");
			}else if ((PRO_NAME+"/zmd!add").equals(path)) {//哲盟数据检查登记添加
				return roleControl(arg0, r, "44");
			}
			
			else if ((PRO_NAME+"/pdachange!queryOfFenye").equals(path)) {//巴枪条码变更查看
				return roleControl(arg0, r, "1001");
			}else if ((PRO_NAME+"/pdachange!delete").equals(path)) {//巴枪条码变更删除
				return roleControl(arg0, r, "1002");
			}else if ((PRO_NAME+"/pdachange!update").equals(path)) {//巴枪条码变更修改
				return roleControl(arg0, r, "1003");
			}else if ((PRO_NAME+"/pdachange!add").equals(path)) {//巴枪条码变更添加
				return roleControl(arg0, r, "1004");
			}
			
			else if ((PRO_NAME+"/bqrepair!queryOfFenye").equals(path)) {//设备维修登记查看
				return roleControl(arg0, r, "1005");
			}else if ((PRO_NAME+"/bqrepair!delete").equals(path)) {//设备维修登记删除
				return roleControl(arg0, r, "1006");
			}else if ((PRO_NAME+"/bqrepair!update").equals(path)) {//设备维修登记修改
				return roleControl(arg0, r, "1007");
			}else if ((PRO_NAME+"/bqrepair!add").equals(path)) {//设备维修登记添加
				return roleControl(arg0, r, "1008");
			}
			
			else if ((PRO_NAME+"/hitches!queryOfFenye").equals(path)) {//哲盟异常登记查看
				return roleControl(arg0, r, "1009");
			}else if ((PRO_NAME+"/hitches!delete").equals(path)) {//哲盟异常登记删除
				return roleControl(arg0, r, "1010");
			}else if ((PRO_NAME+"/hitches!update").equals(path)) {//哲盟异常登记修改
				return roleControl(arg0, r, "1011");
			}else if ((PRO_NAME+"/hitches!add").equals(path)) {//哲盟异常登记添加
				return roleControl(arg0, r, "1012");
			}
			
			else if ((PRO_NAME+"/bqq!queryOfFenye").equals(path)) {//公司BQQ登记查看
				return roleControl(arg0, r, "1013");
			}else if ((PRO_NAME+"/bqq!delete").equals(path)) {//公司BQQ登记删除
				return roleControl(arg0, r, "1014");
			}else if ((PRO_NAME+"/bqq!update").equals(path)) {//公司BQQ登记修改
				return roleControl(arg0, r, "1015");
			}else if ((PRO_NAME+"/bqq!add").equals(path)) {//公司BQQ登记添加
				return roleControl(arg0, r, "1016");
			}
			
			else if ((PRO_NAME+"/netImo!queryOfFenye").equals(path)) {//网点IMO登记查看
				return roleControl(arg0, r, "1017");
			}else if ((PRO_NAME+"/netImo!delete").equals(path)) {//网点IMO登记删除
				return roleControl(arg0, r, "1018");
			}else if ((PRO_NAME+"/netImo!update").equals(path)) {//网点IMO登记修改
				return roleControl(arg0, r, "1019");
			}else if ((PRO_NAME+"/netImo!add").equals(path)) {//网点IMO登记添加
				return roleControl(arg0, r, "1020");
			}
			
			else if ((PRO_NAME+"/project!queryOfFenye").equals(path)) {//系统开发登记查看
				return roleControl(arg0, r, "1021");
			}else if ((PRO_NAME+"/project!delete").equals(path)) {//系统开发登记删除
				return roleControl(arg0, r, "1022");
			}else if ((PRO_NAME+"/project!update").equals(path)) {//系统开发登记修改
				return roleControl(arg0, r, "1023");
			}else if ((PRO_NAME+"/project!add").equals(path)) {//系统开发登记添加
				return roleControl(arg0, r, "1024");
			}
			//-----------统计报表----------------
			else if ((PRO_NAME+"/siteCount!queryOfFenye").equals(path)) {//站点资料统计查看
				return roleControl(arg0, r, "1101");
			}else if ((PRO_NAME+"/siteCount!delete").equals(path)) {//站点资料统计删除
				return roleControl(arg0, r, "1102");
			}else if ((PRO_NAME+"/siteCount!update").equals(path)) {//站点资料统计修改
				return roleControl(arg0, r, "1103");
			}else if ((PRO_NAME+"/siteCount!add").equals(path)) {//站点资料统计添加
				return roleControl(arg0, r, "1104");
			}
			
			else if ((PRO_NAME+"/branchesCount!queryOfFenye").equals(path)) {//二级站点资料统计查看
				return roleControl(arg0, r, "1105");
			}else if ((PRO_NAME+"/branchesCount!delete").equals(path)) {//二级站点资料统计删除
				return roleControl(arg0, r, "1106");
			}else if ((PRO_NAME+"/branchesCount!update").equals(path)) {//二级站点资料统计修改
				return roleControl(arg0, r, "1107");
			}else if ((PRO_NAME+"/branchesCount!add").equals(path)) {//二级站点资料统计添加
				return roleControl(arg0, r, "1108");
			}
			
			else if ((PRO_NAME+"/zmNumberCount!queryOfFenye").equals(path)) {//哲盟职能用户统计查看
				return roleControl(arg0, r, "1109");
			}else if ((PRO_NAME+"/zmNumberCount!delete").equals(path)) {//哲盟职能用户统计删除
				return roleControl(arg0, r, "1110");
			}else if ((PRO_NAME+"/zmNumberCount!update").equals(path)) {//哲盟职能用户统计修改
				return roleControl(arg0, r, "1111");
			}else if ((PRO_NAME+"/zmNumberCount!add").equals(path)) {//哲盟职能用户统计添加
				return roleControl(arg0, r, "1112");
			}
			
			else if ((PRO_NAME+"/zmDataCount!queryOfFenye").equals(path)) {//哲盟数据检查统计查看
				return roleControl(arg0, r, "1113");
			}else if ((PRO_NAME+"/zmDataCount!delete").equals(path)) {//哲盟数据检查统计删除
				return roleControl(arg0, r, "1114");
			}else if ((PRO_NAME+"/zmDataCount!update").equals(path)) {//哲盟数据检查统计修改
				return roleControl(arg0, r, "1115");
			}else if ((PRO_NAME+"/zmDataCount!add").equals(path)) {//哲盟数据检查统计添加
				return roleControl(arg0, r, "1116");
			}
			
			else if ((PRO_NAME+"/pdaChangeCount!queryOfFenye").equals(path)) {//巴枪条码变更统计查看
				return roleControl(arg0, r, "1117");
			}else if ((PRO_NAME+"/pdaChangeCount!delete").equals(path)) {//巴枪条码变更统计删除
				return roleControl(arg0, r, "1118");
			}else if ((PRO_NAME+"/pdaChangeCount!update").equals(path)) {//巴枪条码变更统计修改
				return roleControl(arg0, r, "1119");
			}else if ((PRO_NAME+"/pdaChangeCount!add").equals(path)) {//巴枪条码变更统计添加
				return roleControl(arg0, r, "1120");
			}
			
			else if ((PRO_NAME+"/bqRepairCount!queryOfFenye").equals(path)) {//设备维修登记统计查看
				return roleControl(arg0, r, "1121");
			}else if ((PRO_NAME+"/bqRepairCount!delete").equals(path)) {//设备维修登记统计删除
				return roleControl(arg0, r, "1122");
			}else if ((PRO_NAME+"/bqRepairCount!update").equals(path)) {//设备维修登记统计修改
				return roleControl(arg0, r, "1123");
			}else if ((PRO_NAME+"/bqRepairCount!add").equals(path)) {//设备维修登记统计添加
				return roleControl(arg0, r, "1124");
			}
			
			else if ((PRO_NAME+"/hitchesCount!queryOfFenye").equals(path)) {//哲盟异常登记统计查看
				return roleControl(arg0, r, "1125");
			}else if ((PRO_NAME+"/hitchesCount!delete").equals(path)) {//哲盟异常登记统计删除
				return roleControl(arg0, r, "1126");
			}else if ((PRO_NAME+"/hitchesCount!update").equals(path)) {//哲盟异常登记统计修改
				return roleControl(arg0, r, "1127");
			}else if ((PRO_NAME+"/hitchesCount!add").equals(path)) {//哲盟异常登记统计添加
				return roleControl(arg0, r, "1128");
			}
			
			else if ((PRO_NAME+"/projectCount!queryOfFenye").equals(path)) {//系统开发统计查看
				return roleControl(arg0, r, "1129");
			}else if ((PRO_NAME+"/projectCount!delete").equals(path)) {//系统开发统计删除
				return roleControl(arg0, r, "1130");
			}else if ((PRO_NAME+"/projectCount!update").equals(path)) {//系统开发统计修改
				return roleControl(arg0, r, "1131");
			}else if ((PRO_NAME+"/projectCount!add").equals(path)) {//系统开发统计添加
				return roleControl(arg0, r, "1132");
			}
			
			//---------------------------系统组结束，系统设置开始----------------------------------------
			else if ((PRO_NAME+"/users!queryOfFenye").equals(path)) {//用户管理查看
				return roleControl(arg0, r, "1001");
			}else if ((PRO_NAME+"/users!delete").equals(path)) {//用户管理删除
				return roleControl(arg0, r, "1002");
			}else if ((PRO_NAME+"/users!update").equals(path)) {//用户管理修改
				return roleControl(arg0, r, "1003");
			}else if ((PRO_NAME+"/users!add").equals(path)) {//用户管理添加
				return roleControl(arg0, r, "1004");
			}
			
			else if ((PRO_NAME+"/role!queryOfFenye").equals(path)) {//角色管理查看
				return roleControl(arg0, r, "49");
			}else if ((PRO_NAME+"/role!delete").equals(path)) {//角色管理删除
				return roleControl(arg0, r, "50");
			}else if ((PRO_NAME+"/role!update").equals(path)) {//角色管理修改
				return roleControl(arg0, r, "51");
			}else if ((PRO_NAME+"/role!add").equals(path)) {//角色管理添加
				return roleControl(arg0, r, "52");
			}
			//---------------------------个人中心开始----------------------------------------
			else if ((PRO_NAME+"/timeline!query").equals(path)) {//时间轴查看
				return roleControl(arg0, r, "53");
			}
			
			else if ((PRO_NAME+"/information!query").equals(path)) {//消息提醒查看
				return roleControl(arg0, r, "54");
			}
			//------------------------------通讯录---------------------------------------
			else if ((PRO_NAME+"/section!queryOfFenyeQb").equals(path)) {//区部信息查看
				return roleControl(arg0, r, "55");
			}else if ((PRO_NAME+"/section!deleteQb").equals(path)) {//区部信息删除
				return roleControl(arg0, r, "56");
			}else if ((PRO_NAME+"/section!updateQb").equals(path)) {//区部信息修改
				return roleControl(arg0, r, "57");
			}else if ((PRO_NAME+"/section!addQb").equals(path)) {//区部信息添加
				return roleControl(arg0, r, "58");
			}
			
			else if ((PRO_NAME+"/section!queryOfFenyeFb").equals(path)) {//分部信息查看
				return roleControl(arg0, r, "59");
			}else if ((PRO_NAME+"/section!deleteFb").equals(path)) {//分部信息删除
				return roleControl(arg0, r, "60");
			}else if ((PRO_NAME+"/section!updateFb").equals(path)) {//分部信息修改
				return roleControl(arg0, r, "61");
			}else if ((PRO_NAME+"/section!addFb").equals(path)) {//分部信息添加
				return roleControl(arg0, r, "62");
			}
			
			else if ((PRO_NAME+"/section!queryOfFenyeFbd").equals(path)) {//分拨点信息查看
				return roleControl(arg0, r, "63");
			}else if ((PRO_NAME+"/section!deleteFbd").equals(path)) {//分拨点信息删除
				return roleControl(arg0, r, "64");
			}else if ((PRO_NAME+"/section!updateFbd").equals(path)) {//分拨点信息修改
				return roleControl(arg0, r, "65");
			}else if ((PRO_NAME+"/section!addFbd").equals(path)) {//分拨点信息添加
				return roleControl(arg0, r, "66");
			}
			//-----公司内部------
			else if ((PRO_NAME+"/cs!query").equals(path) || (PRO_NAME+"/cs!queryInfor").equals(path)) {//公司组织架构查看
				return roleControl(arg0, r, "67");
			}else if ((PRO_NAME+"/cs!myDelete").equals(path)) {//公司组织架构删除
				return roleControl(arg0, r, "68");
			}else if ((PRO_NAME+"/cs!update").equals(path)) {//公司组织架构修改
				return roleControl(arg0, r, "69");
			}else if ((PRO_NAME+"/cs!add").equals(path)) {//公司组织架构添加
				return roleControl(arg0, r, "70");
			}
			 
			//------------------桌面组---------------------
			else if ((PRO_NAME+"/vpn!queryOfFenye").equals(path)) {//VPN查看
				return roleControl(arg0, r, "71");
			}else if ((PRO_NAME+"/vpn!delete").equals(path)) {//VPN删除
				return roleControl(arg0, r, "72");
			}else if ((PRO_NAME+"/vpn!update").equals(path)) {//VPN修改
				return roleControl(arg0, r, "73");
			}else if ((PRO_NAME+"/vpn!add").equals(path)) {//VPN添加
				return roleControl(arg0, r, "74");
			}
			
			else if ((PRO_NAME+"/by!queryOfFenye").equals(path)) {//IMO账号登记查看
				return roleControl(arg0, r, "75");
			}else if ((PRO_NAME+"/by!delete").equals(path)) {//IMO账号登记删除
				return roleControl(arg0, r, "76");
			}else if ((PRO_NAME+"/by!update").equals(path)) {//IMO账号登记修改
				return roleControl(arg0, r, "77");
			}else if ((PRO_NAME+"/by!add").equals(path)) {//IMO登记添加
				return roleControl(arg0, r, "78");
			}
			
			else if ((PRO_NAME+"/oa!queryOfFenye").equals(path)) {//OA账号登记查看
				return roleControl(arg0, r, "79");
			}else if ((PRO_NAME+"/oa!delete").equals(path)) {//OA账号登记删除
				return roleControl(arg0, r, "80");
			}else if ((PRO_NAME+"/oa!update").equals(path)) {//OA账号登记修改
				return roleControl(arg0, r, "81");
			}else if ((PRO_NAME+"/oa!add").equals(path)) {//OA账号登记添加
				return roleControl(arg0, r, "82");
			}
			
			else if ((PRO_NAME+"/print!queryOfFenye").equals(path)) {//打印机登记查看
				return roleControl(arg0, r, "83");
			}else if ((PRO_NAME+"/print!delete").equals(path)) {//打印机登记删除
				return roleControl(arg0, r, "84");
			}else if ((PRO_NAME+"/print!update").equals(path)) {//打印机登记修改
				return roleControl(arg0, r, "85");
			}else if ((PRO_NAME+"/print!add").equals(path)) {//打印机登记添加
				return roleControl(arg0, r, "86");
			}
			
			else if ((PRO_NAME+"/computer!queryOfFenye").equals(path)) {//管理电脑统计查看
				return roleControl(arg0, r, "87");
			}else if ((PRO_NAME+"/computer!delete").equals(path)) {//管理电脑统计删除
				return roleControl(arg0, r, "88");
			}else if ((PRO_NAME+"/computer!update").equals(path)) {//管理电脑统计修改
				return roleControl(arg0, r, "89");
			}else if ((PRO_NAME+"/computer!add").equals(path)) {//管理电脑统计添加
				return roleControl(arg0, r, "90");
			}
			 
			else if ((PRO_NAME+"/bq!queryOfFenye").equals(path)) {//小仓巴枪电脑登记查看
				return roleControl(arg0, r, "91");
			}else if ((PRO_NAME+"/bq!delete").equals(path)) {//小仓巴枪电脑登记删除
				return roleControl(arg0, r, "92");
			}else if ((PRO_NAME+"/bq!update").equals(path)) {//小仓巴枪电脑登记修改
				return roleControl(arg0, r, "93");
			}else if ((PRO_NAME+"/bq!add").equals(path)) {//小仓巴枪电脑登记添加
				return roleControl(arg0, r, "94");
			}
			
			else if ((PRO_NAME+"/wifi!queryOfFenye").equals(path)) {//园区wifi管理查看
				return roleControl(arg0, r, "95");
			}else if ((PRO_NAME+"/wifi!delete").equals(path)) {//园区wifi管理删除
				return roleControl(arg0, r, "96");
			}else if ((PRO_NAME+"/wifi!update").equals(path)) {//园区wifi管理修改
				return roleControl(arg0, r, "97");
			}else if ((PRO_NAME+"/wifi!add").equals(path)) {//园区wifi管理添加
				return roleControl(arg0, r, "98");
			}
			
			else if ((PRO_NAME+"/phone!queryOfFenye").equals(path)) {//电话线分布查看
				return roleControl(arg0, r, "99");
			}else if ((PRO_NAME+"/phone!delete").equals(path)) {//电话线分布删除
				return roleControl(arg0, r, "100");
			}else if ((PRO_NAME+"/phone!update").equals(path)) {//电话线分布修改
				return roleControl(arg0, r, "101");
			}else if ((PRO_NAME+"/phone!add").equals(path)) {//电话线分布添加
				return roleControl(arg0, r, "102");
			}
			
			else if ((PRO_NAME+"/call!queryOfFenye").equals(path)) {//总部呼叫系统查看
				return roleControl(arg0, r, "3001");
			}else if ((PRO_NAME+"/call!delete").equals(path)) {//总部呼叫系统删除
				return roleControl(arg0, r, "3002");
			}else if ((PRO_NAME+"/call!update").equals(path)) {//总部呼叫系统修改
				return roleControl(arg0, r, "3003");
			}else if ((PRO_NAME+"/call!add").equals(path)) {//总部呼叫系统添加
				return roleControl(arg0, r, "3004");
			}
			
			else if ((PRO_NAME+"/netCall!queryOfFenye").equals(path)) {//网点呼叫系统查看
				return roleControl(arg0, r, "3005");
			}else if ((PRO_NAME+"/netCall!delete").equals(path)) {//网点呼叫系统删除
				return roleControl(arg0, r, "3006");
			}else if ((PRO_NAME+"/netCall!update").equals(path)) {//网点呼叫系统修改
				return roleControl(arg0, r, "3007");
			}else if ((PRO_NAME+"/netCall!add").equals(path)) {//网点呼叫系统添加
				return roleControl(arg0, r, "3008");
			}
			
			else if ((PRO_NAME+"/mail!queryOfFenye").equals(path)) {//邮箱账号查看
				return roleControl(arg0, r, "3009");
			}else if ((PRO_NAME+"/mail!delete").equals(path)) {//邮箱账号删除
				return roleControl(arg0, r, "3010");
			}else if ((PRO_NAME+"/mail!update").equals(path)) {//邮箱账号修改
				return roleControl(arg0, r, "3011");
			}else if ((PRO_NAME+"/mail!add").equals(path)) {//邮箱账号添加
				return roleControl(arg0, r, "3012");
			}
			
			
			//-----------统计报表----------------
			else if ((PRO_NAME+"/vpnCount!queryOfFenye").equals(path)) {//VPN账号登记统计查看
				return roleControl(arg0, r, "3101");
			}else if ((PRO_NAME+"/vpnCount!delete").equals(path)) {//VPN账号登记统计删除
				return roleControl(arg0, r, "3102");
			}else if ((PRO_NAME+"/vpnCount!update").equals(path)) {//VPN账号登记统计修改
				return roleControl(arg0, r, "3103");
			}else if ((PRO_NAME+"/vpnCount!add").equals(path)) {//VPN账号登记统计添加
				return roleControl(arg0, r, "3104");
			}
			
			else if ((PRO_NAME+"/byNumberCount!queryOfFenye").equals(path)) {//IMO邮箱账号统计查看
				return roleControl(arg0, r, "3105");
			}else if ((PRO_NAME+"/byNumberCount!delete").equals(path)) {//IMO邮箱账号统计删除
				return roleControl(arg0, r, "3106");
			}else if ((PRO_NAME+"/byNumberCount!update").equals(path)) {//IMO邮箱账号统计修改
				return roleControl(arg0, r, "3107");
			}else if ((PRO_NAME+"/byNumberCount!add").equals(path)) {//IMO邮箱账号统计添加
				return roleControl(arg0, r, "3108");
			}
			
			else if ((PRO_NAME+"/computerCount!queryOfFenye").equals(path)) {//公司电脑登记统计查看
				return roleControl(arg0, r, "3109");
			}else if ((PRO_NAME+"/computerCount!delete").equals(path)) {//公司电脑登记统计删除
				return roleControl(arg0, r, "3110");
			}else if ((PRO_NAME+"/computerCount!update").equals(path)) {//公司电脑登记统计修改
				return roleControl(arg0, r, "3111");
			}else if ((PRO_NAME+"/computerCount!add").equals(path)) {//公司电脑登记统计添加
				return roleControl(arg0, r, "3112");
			}
			
			else if ((PRO_NAME+"/callCount!queryOfFenye").equals(path)) {//总部呼叫系统统计查看
				return roleControl(arg0, r, "3113");
			}else if ((PRO_NAME+"/callCount!delete").equals(path)) {//总部呼叫系统统计删除
				return roleControl(arg0, r, "3114");
			}else if ((PRO_NAME+"/callCount!update").equals(path)) {//总部呼叫系统统计修改
				return roleControl(arg0, r, "3115");
			}else if ((PRO_NAME+"/callCount!add").equals(path)) {//总部呼叫系统统计添加
				return roleControl(arg0, r, "3116");
			}
			
			else if ((PRO_NAME+"/oaCount!queryOfFenye").equals(path)) {//OA账号登记统计查看
				return roleControl(arg0, r, "3117");
			}else if ((PRO_NAME+"/oaCount!delete").equals(path)) {//OA账号登记统计删除
				return roleControl(arg0, r, "3118");
			}else if ((PRO_NAME+"/oaCount!update").equals(path)) {//OA账号登记统计修改
				return roleControl(arg0, r, "3119");
			}else if ((PRO_NAME+"/oaCount!add").equals(path)) {//OA账号登记统计添加
				return roleControl(arg0, r, "3120");
			}
			
			else if ((PRO_NAME+"/printCount!queryOfFenye").equals(path)) {//打印机登记统计查看
				return roleControl(arg0, r, "3121");
			}else if ((PRO_NAME+"/printCount!delete").equals(path)) {//打印机登记统计删除
				return roleControl(arg0, r, "3122");
			}else if ((PRO_NAME+"/printCount!update").equals(path)) {//打印机登记统计修改
				return roleControl(arg0, r, "3123");
			}else if ((PRO_NAME+"/printCount!add").equals(path)) {//打印机登记统计添加
				return roleControl(arg0, r, "3124");
			}
			
			
			//------------------------------维护组--------------------------
			
			else if ((PRO_NAME+"/device!queryOfFenye").equals(path)) {//操作设备巡检查看
				return roleControl(arg0, r, "4001");
			}else if ((PRO_NAME+"/device!delete").equals(path)) {//操作设备巡检删除
				return roleControl(arg0, r, "4002");
			}else if ((PRO_NAME+"/device!update").equals(path)) {//操作设备巡检修改
				return roleControl(arg0, r, "4003");
			}else if ((PRO_NAME+"/device!add").equals(path)) {//操作设备巡检添加
				return roleControl(arg0, r, "4004");
			}
			
			else if ((PRO_NAME+"/monitor!queryOfFenye").equals(path)) {//监控设备巡检查看
				return roleControl(arg0, r, "4005");
			}else if ((PRO_NAME+"/monitor!delete").equals(path)) {//监控设备巡检删除
				return roleControl(arg0, r, "4006");
			}else if ((PRO_NAME+"/monitor!update").equals(path)) {//监控设备巡检修改
				return roleControl(arg0, r, "4007");
			}else if ((PRO_NAME+"/monitor!add").equals(path)) {//监控设备巡检添加
				return roleControl(arg0, r, "4008");
			}
			
			else if ((PRO_NAME+"/threeMeet!queryOfFenye").equals(path)) {//观澜3楼巡检查看
				return roleControl(arg0, r, "4009");
			}else if ((PRO_NAME+"/threeMeet!delete").equals(path)) {//观澜3楼巡检删除
				return roleControl(arg0, r, "4010");
			}else if ((PRO_NAME+"/threeMeet!update").equals(path)) {//观澜3楼巡检修改
				return roleControl(arg0, r, "4011");
			}else if ((PRO_NAME+"/threeMeet!add").equals(path)) {//观澜3楼巡检添加
				return roleControl(arg0, r, "4012");
			}
			
			else if ((PRO_NAME+"/meet!queryOfFenye").equals(path)) {//新仓仓库巡检查看
				return roleControl(arg0, r, "4013");
			}else if ((PRO_NAME+"/meet!delete").equals(path)) {//新仓仓库巡检删除
				return roleControl(arg0, r, "4014");
			}else if ((PRO_NAME+"/meet!update").equals(path)) {//新仓仓库巡检修改
				return roleControl(arg0, r, "4015");
			}else if ((PRO_NAME+"/meet!add").equals(path)) {//新仓仓库巡检添加
				return roleControl(arg0, r, "4016");
			}
			
			else if ((PRO_NAME+"/massage!queryOfFenye").equals(path)) {//巴枪维修登记查看
				return roleControl(arg0, r, "4017");
			}else if ((PRO_NAME+"/massage!delete").equals(path)) {//巴枪维修登记删除
				return roleControl(arg0, r, "4018");
			}else if ((PRO_NAME+"/massage!update").equals(path)) {//巴枪维修登记修改
				return roleControl(arg0, r, "4019");
			}else if ((PRO_NAME+"/massage!add").equals(path)) {//巴枪维修登记添加
				return roleControl(arg0, r, "4020");
			}
			
			else if ((PRO_NAME+"/exp!queryOfFenye").equals(path)) {//快递单号登记查看
				return roleControl(arg0, r, "4021");
			}else if ((PRO_NAME+"/exp!delete").equals(path)) {//快递单号登记删除
				return roleControl(arg0, r, "4022");
			}else if ((PRO_NAME+"/exp!update").equals(path)) {//快递单号登记修改
				return roleControl(arg0, r, "4023");
			}else if ((PRO_NAME+"/exp!add").equals(path)) {//快递单号登记添加
				return roleControl(arg0, r, "4024");
			}
			
			else if ((PRO_NAME+"/bqDraw!queryOfFenye").equals(path)) {//巴枪领取登记查看
				return roleControl(arg0, r, "4025");
			}else if ((PRO_NAME+"/bqDraw!delete").equals(path)) {//巴枪领取登记删除
				return roleControl(arg0, r, "4026");
			}else if ((PRO_NAME+"/bqDraw!update").equals(path)) {//巴枪领取登记修改
				return roleControl(arg0, r, "4027");
			}else if ((PRO_NAME+"/bqDraw!add").equals(path)) {//巴枪领取登记添加
				return roleControl(arg0, r, "4028");
			}
			
			else if ((PRO_NAME+"/out!queryOfFenye").equals(path)) {//设备外修登记查看
				return roleControl(arg0, r, "4029");
			}else if ((PRO_NAME+"/out!delete").equals(path)) {//设备外修登记删除
				return roleControl(arg0, r, "4030");
			}else if ((PRO_NAME+"/out!update").equals(path)) {//设备外修登记修改
				return roleControl(arg0, r, "4031");
			}else if ((PRO_NAME+"/out!add").equals(path)) {//设备外修登记添加
				return roleControl(arg0, r, "4032");
			}
			//-----------统计报表----------------
			else if ((PRO_NAME+"/deviceCount!queryOfFenye").equals(path)) {//操作设备巡检统计查看
				return roleControl(arg0, r, "4101");
			}else if ((PRO_NAME+"/deviceCount!delete").equals(path)) {//操作设备巡检统计删除
				return roleControl(arg0, r, "4102");
			}else if ((PRO_NAME+"/deviceCount!update").equals(path)) {//操作设备巡检统计修改
				return roleControl(arg0, r, "4103");
			}else if ((PRO_NAME+"/deviceCount!add").equals(path)) {//操作设备巡检统计添加
				return roleControl(arg0, r, "4104 ");
			}
			
			else if ((PRO_NAME+"/monitorCount!queryOfFenye").equals(path)) {//监控设备巡检统计查看
				return roleControl(arg0, r, "4105");
			}else if ((PRO_NAME+"/monitorCount!delete").equals(path)) {//监控设备巡检统计删除
				return roleControl(arg0, r, "4106");
			}else if ((PRO_NAME+"/monitorCount!update").equals(path)) {//监控设备巡检统计修改
				return roleControl(arg0, r, "4107");
			}else if ((PRO_NAME+"/monitorCount!add").equals(path)) {//监控设备巡检统计添加
				return roleControl(arg0, r, "4108 ");
			}
			
			else if ((PRO_NAME+"/threeMeetingCount!queryOfFenye").equals(path)) {//观澜3楼巡检统计查看
				return roleControl(arg0, r, "4109");
			}else if ((PRO_NAME+"/threeMeetingCount!delete").equals(path)) {//观澜3楼巡检统计删除
				return roleControl(arg0, r, "4110");
			}else if ((PRO_NAME+"/threeMeetingCount!update").equals(path)) {//观澜3楼巡检统计修改
				return roleControl(arg0, r, "4111");
			}else if ((PRO_NAME+"/threeMeetingCount!add").equals(path)) {//观澜3楼巡检统计添加
				return roleControl(arg0, r, "4112 ");
			}
			
			else if ((PRO_NAME+"/meetingCount!queryOfFenye").equals(path)) {//新仓仓库巡检统计查看
				return roleControl(arg0, r, "4113");
			}else if ((PRO_NAME+"/meetingCount!delete").equals(path)) {//新仓仓库巡检统计删除
				return roleControl(arg0, r, "4114");
			}else if ((PRO_NAME+"/meetingCount!update").equals(path)) {//新仓仓库巡检统计修改
				return roleControl(arg0, r, "4115");
			}else if ((PRO_NAME+"/meetingCount!add").equals(path)) {//新仓仓库巡检统计添加
				return roleControl(arg0, r, "4116 ");
			}
			
			else if ((PRO_NAME+"/massageCount!queryOfFenye").equals(path)) {//巴枪维修登记统计查看
				return roleControl(arg0, r, "4117");
			}else if ((PRO_NAME+"/massageCount!delete").equals(path)) {//巴枪维修登记统计删除
				return roleControl(arg0, r, "4118");
			}else if ((PRO_NAME+"/massageCount!update").equals(path)) {//巴枪维修登记统计修改
				return roleControl(arg0, r, "4119");
			}else if ((PRO_NAME+"/massageCount!add").equals(path)) {//巴枪维修登记统计添加
				return roleControl(arg0, r, "4120 ");
			}
			
			else if ((PRO_NAME+"/outRepairCount!queryOfFenye").equals(path)) {//设备外修登记统计查看
				return roleControl(arg0, r, "4121");
			}else if ((PRO_NAME+"/outRepairCount!delete").equals(path)) {//设备外修登记统计删除
				return roleControl(arg0, r, "4122");
			}else if ((PRO_NAME+"/outRepairCount!update").equals(path)) {//设备外修登记统计修改
				return roleControl(arg0, r, "4123");
			}else if ((PRO_NAME+"/outRepairCount!add").equals(path)) {//设备外修登记统计添加
				return roleControl(arg0, r, "4124 ");
			}
			
			
			
			//------------------------故障报修处理跟进系统----------------------------
			//-------------------------------故障管理--------------------
			else if ((PRO_NAME+"/daManager!queryOfFenye").equals(path)) {//故障报修管理查看
				return roleControl(arg0, r, "103");
			}else if ((PRO_NAME+"/daManager!delete").equals(path)) {//故障报修管理删除
				return roleControl(arg0, r, "104");
			}else if ((PRO_NAME+"/daManager!update").equals(path)) {//故障报修管理修改
				return roleControl(arg0, r, "105");
			}else if ((PRO_NAME+"/daManager!add").equals(path)) {//故障报修管理添加
				return roleControl(arg0, r, "106");
			}
			
			else if ((PRO_NAME+"/count!queryOfFenye").equals(path)) {//故障报修统计——客服统计查看
				return roleControl(arg0, r, "107");
			}else if ((PRO_NAME+"/count!delete").equals(path)) {//故障报修统计——客服统计删除
				return roleControl(arg0, r, "108");
			}else if ((PRO_NAME+"/count!update").equals(path)) {//故障报修统计——客服统计修改
				return roleControl(arg0, r, "109");
			}else if ((PRO_NAME+"/count!add").equals(path)) {//故障报修统计——客服统计添加
				return roleControl(arg0, r, "110");
			}
			
			else if ((PRO_NAME+"/countZy!queryOfFenye").equals(path)) {//故障报修统计——专员统计查看
				return roleControl(arg0, r, "111");
			}else if ((PRO_NAME+"/countZy!delete").equals(path)) {//故障报修统计——专员统计删除
				return roleControl(arg0, r, "112");
			}else if ((PRO_NAME+"/countZy!update").equals(path)) {//故障报修统计——专员统计修改
				return roleControl(arg0, r, "113");
			}else if ((PRO_NAME+"/countZy!add").equals(path)) {//故障报修统计——专员统计添加
				return roleControl(arg0, r, "114");
			}
			
			else if ((PRO_NAME+"/handle!queryOfFenye").equals(path)) {//故障报修处理查看
				return roleControl(arg0, r, "115");
			}else if ((PRO_NAME+"/handle!delete").equals(path)) {//故障报修处理删除
				return roleControl(arg0, r, "116");
			}else if ((PRO_NAME+"/handle!update").equals(path)) {//故障报修处理修改
				return roleControl(arg0, r, "117");
			}else if ((PRO_NAME+"/handle!add").equals(path)) {//故障报修处理添加
				return roleControl(arg0, r, "118");
			} 
			
			else if ((PRO_NAME+"/auditing!queryOfFenye").equals(path)) {//故障报修转发审核查看
				return roleControl(arg0, r, "119");
			}else if ((PRO_NAME+"/auditing!delete").equals(path)) {//故障报修转发审核删除
				return roleControl(arg0, r, "120");
			}else if ((PRO_NAME+"/auditing!update").equals(path)) {//故障报修转发审核修改
				return roleControl(arg0, r, "121");
			}else if ((PRO_NAME+"/auditing!add").equals(path)) {//故障报修转发审核添加
				return roleControl(arg0, r, "122");
			}
			
			
			
			
			
			
		}
		close(); 
		return arg0.invoke(); 
	}
	
	private void close() {

	}
}
