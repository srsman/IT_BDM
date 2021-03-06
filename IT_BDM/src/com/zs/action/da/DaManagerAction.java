package com.zs.action.da;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.zs.action.IMyBaseAction;
import com.zs.action.MyBaseAction;
import com.zs.entity.DaDemPer;
import com.zs.entity.DaDemand;
import com.zs.entity.DaPerform;
import com.zs.entity.Users;
import com.zs.entity.custom.MailModel;
import com.zs.mail.MailManager;
import com.zs.mail.MailTest;
import com.zs.service.IService;
import com.zs.tools.NameOfDate;
import com.zs.tools.Page;

import freemarker.template.SimpleDate;

public class DaManagerAction extends MyBaseAction implements IMyBaseAction{

	IService ser;
	Page page;
	
	String result="manager";
	String result_succ="succ";
	String result_fail="fail";

	DaDemand d;
	DaPerform p;
	List demPers;
	
	String id;
	String cz;
	
	String dates;
	String datee;
	String type;
	
	Logger logger=Logger.getLogger(DaManagerAction.class);
	private static MailManager mailManager=MailManager.getInstance();//邮件发送者
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public IService getSer() {
		return ser;
	}
	public void setSer(IService ser) {
		this.ser = ser;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public DaDemand getD() {
		return d;
	}
	public void setD(DaDemand d) {
		this.d = d;
	}
	public DaPerform getP() {
		return p;
	}
	public void setP(DaPerform p) {
		this.p = p;
	}
	public List<DaDemPer> getDemPers() {
		return demPers;
	}
	public void setDemPers(List<DaDemPer> demPers) {
		this.demPers = demPers;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getDatee() {
		return datee;
	}
	public void setDatee(String datee) {
		this.datee = datee;
	}
	public String getCz() {
		return cz;
	}
	public void setCz(String cz) {
		this.cz = cz;
	}
	//----------------------------------------------------------
	public void clearOptions() {
		id=null;
		cz=null;
		dates=null;
		datee=null;
		type=null;
		d=null;
		p=null;
		demPers=null;
		if (page==null) {
			page=new Page(1, 0, 10);
		}else {
			page.setPageOn(1);
		}
	}
	
	private void clearSpace() {
		if (id!=null) {
			id=id.trim();
		}
		if (cz!=null) {
			cz=cz.trim();
		}
		if (dates!=null) {
			dates=dates.trim();
		}
		if (datee!=null) {
			datee=datee.trim();
		}
		if (type!=null) {
			type=type.trim();
		}
	}
	
	
	public String queryOfFenye() throws UnsupportedEncodingException {
		clearSpace();
		if (cz!=null && cz.equals("yes")) {
			clearOptions();
		}
		String hql="from DaDemand where 1=1 ";
		if(id!=null)
			hql=hql+"and DId like '%"+id+"%' ";
		if (type!=null && !type.equals(""))
			hql=hql+"and DType = '"+type+"' ";
		if(dates!=null && !dates.equals(""))
			hql=hql+" and DTime >= '"+dates+" 0:0:0' ";
		if(datee!=null && !datee.equals(""))
			hql=hql+" and DTime <= '"+datee+" 23:59:59' ";
		hql=hql+"order by DTime desc";
		List dems=ser.query(hql, null, hql, page, ser);
		demPers=ser.initDemPers(dems);
		ser.bringUsers(getRequest());
		JSONArray json=JSONArray.fromObject(demPers);
		getRequest().setAttribute("json", json);
		return result;
	}
	
	public String gotoQuery() throws UnsupportedEncodingException {
		clearOptions();
		String hql="from DaDemand order by DTime desc";
		String ss[]={};
		String hql2="from DaDemand order by DTime desc";
		List dems=ser.query(hql, ss, hql2, page, ser);
		demPers=ser.initDemPers(dems);
		ser.bringUsers(getRequest());
		JSONArray json=JSONArray.fromObject(demPers);
		getRequest().setAttribute("json", json);
		return result;
	}
    
	
	public String add() throws Exception {
		String dTimeExpect = getRequest().getParameter("DTimeExpect");
		Users um = (Users) ser.get(Users.class, p.getUNum());
		if (d!=null) {
			d.setDId("d"+NameOfDate.getNum());
//			d.setDTime(new Timestamp(new Date().getTime()));
			if (dTimeExpect!=null && !dTimeExpect.trim().equals("")) {
				Timestamp  ts= Timestamp.valueOf(dTimeExpect);
				d.setDTimeExpect(ts);
				ser.save(d);
				getRequest().setAttribute("d", d);
//			new Thread(new AutoTransState(d, getSer())).start();  
				p.setPId("p"+NameOfDate.getNum());
				p.setDId(d.getDId());
				p.setPTime(new Timestamp(new Date().getTime()));
				p.setPState("进行中");
				ser.save(p);
				getRequest().setAttribute("p",p);
				String sj=um.getUMail();
				if(outMailFromAdd(um,d)==false){
					//日后换成邮件错误界面
					getResponse().getWriter().write("邮件发送错误!请手动发送邮件");
					logger.error("邮件发送错误!请手动发送邮件,错误单号"+d.getDId());
					return null;
				}
			}else {
				return result_fail;
			}
		}
		cleardap();
		return gotoQuery();
	}

	
	private void cleardap() {
		d=null;
		p=null;
	}
	
	public String delete() throws Exception {
		return null;
	}
	
	
	public String update() throws Exception {
		//邮件发送所需数据
		Users um = null;
		Users umnext=null;
		if (d!=null && !"".equals(d.getDId())) {
			d=(DaDemand) ser.get(DaDemand.class, d.getDId());
			//找到当前执行表数据
			List templi=ser.find("from DaPerform where DId=? order by PTime desc", new String[]{d.getDId()});
			if (templi.size()>0) {
				DaPerform tmpper=(DaPerform) templi.get(0);
				tmpper.setPTime(new Timestamp(new Date().getTime()));
				tmpper.setUNumNext(p.getUNumNext());
				tmpper.setPState("转发");
				tmpper.setPNote(p.getPNote());
				ser.update(tmpper);
				getRequest().setAttribute("p1",tmpper);
				
				DaPerform daPerform=new DaPerform();
				daPerform.setPId("p"+NameOfDate.getNum());
				daPerform.setDId(d.getDId());
				daPerform.setUNum(p.getUNumNext());
				Date date1=new Date();
				Date date2=new Date(date1.getYear(), date1.getMonth(), date1.getDate(), date1.getHours(), date1.getMinutes(), date1.getSeconds()+1);
				daPerform.setPTime(new Timestamp(date2.getTime()));
				daPerform.setPState("进行中");
				ser.save(daPerform);
				getRequest().setAttribute("p2",tmpper);
				
				//邮件模块需要带的数据
				um = (Users) ser.get(Users.class, tmpper.getUNum());
				umnext = (Users) ser.get(Users.class, tmpper.getUNumNext());
				
				if(outMailFromUpdate(um, umnext, d)==false){
					//日后换成邮件错误界面
					getResponse().getWriter().write("邮件发送错误!请手动发送邮件");
					logger.error("邮件发送错误!请手动发送邮件,错误单号"+d.getDId());
					return null;
				}
			}
		}
		cleardap();
		return gotoQuery();
	}
	public String addTimeOut() throws Exception{
		String timeId= getRequest().getParameter("timeId");
		List templi=ser.find("from DaDemand where DId=? ", new String[]{timeId});
		if(templi.size()!=0){
			DaDemand dad = (DaDemand) templi.get(0);
			dad.setDTimeExpect(new Timestamp(dad.getDTimeExpect().getTime()+1000*60*60*24));
			ser.update(dad);
		}
		return gotoQuery();
	}

	public static boolean outMailFromAdd(Users um,DaDemand d){
		//邮件
		String title="故障处理提醒";
		String sj=um.getUMail();
		String content="<style type=\"text/css\">span{display:block;margin:5px 0;font-size:15px;} .table1{	border: #224466;	border-collapse:collapse;	width: 600px;} .tleft{text-align:left;}</style>";
		content=content+"<div style=\"font-family:微软雅黑;font-size:15px;\">"+
		"<div style=\"height:400px;\">"+
		"<span>Dear "+um.getUName()+"</span>"+
		"<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您收到有新的故障处理，请尽快解决！ 详情如下</span>"+
		"<table class=\"table1\" border=\"1\">" +
		"<tr>" +
		"<td class=\"tleft\">编&nbsp;&nbsp; 号:</td><td>"+d.getDId()+"</td><td class=\"tleft\">发 起 人:</td><td>"+d.getDApplicant()+"</td></tr>" +
		"<tr><td class=\"tleft\">故障类型:</td><td>"+d.getDType()+"</td><td class=\"tleft\">创建时间:</td><td>"+new SimpleDate(d.getDTime())+"</td></tr>" +
		"<tr><td>超时时间:</td><td>"+d.getDTimeExpect()+"</td></tr>" +
		"</table>"+
		"<span>故障描述:</span>"+
		"<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+d.getDContent()+"</span>"+
		"</div>"+
		"<br/>Best Wishes<br/>"+
		"以流程为导向，以服务为宗旨。<br/>"+
		"*****************************************************"+
		"<br/>信息与流程管理部-客服专员 "+
		"<br/>地址：广东省深圳市龙华新区观澜大道114号（交警中队正对面）<br/>"+
		"***************************************************<br/></div>";
		try {
			mailManager.addMail(new MailModel(sj,MailTest.IT_ROBOT, content, title));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean outMailFromUpdate(Users um,Users umnext,DaDemand d){
		//邮件标题
		String title="故障处理提醒";
		String sj=umnext.getUMail();
		//编写邮件内容
		String content="<style type=\"text/css\">span{display:block;margin:5px 0;font-size:15px;} .table1{	border: #224466;	border-collapse:collapse;	width: 600px;} .tleft{text-align:left;}</style>";
		content=content+"<div style=\"font-family:微软雅黑;font-size:15px;\">"+
		"<div style=\"height:400px;\" >"+
		"<span>Dear "+umnext.getUName()+"</span>"+
		"<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您收到有"+um.getUName()+"转发给您的故障处理，请尽快解决！ 详情如下</span>"+
		"<table class=\"table1\" border=\"1\">" +
		"<tr>" +
		"<td class=\"tleft\">编&nbsp;&nbsp; 号:</td><td>"+d.getDId()+"</td><td class=\"tleft\">发起人:</td><td>"+d.getDApplicant()+"</td></tr>" +
		"<tr><td class=\"tleft\">故障类型:</td><td>"+d.getDType()+"</td><td class=\"tleft\">创建时间:</td><td>"+new SimpleDate(d.getDTime())+"</td></tr>" +
		"</table>"+
		"<span>故障描述:</span>"+
		"<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+d.getDContent()+"</span>"+
		"</div>"+
		"<br/>Best Wishes<br/>"+
		"以流程为导向，以服务为宗旨。<br/>"+
		"*****************************************************"+
		"<br/>信息与流程管理部-"+um.getUJob()+"  "+um.getUName()+""+
		"<br/>深圳市韵达速递有限公司<br/>邮箱："+um.getUMail()+
		"<br/>地址：广东省深圳市龙华新区观澜大道114号（交警中队正对面）<br/>"+
		"***************************************************<br/></div>";
		try {
			mailManager.addMail(new MailModel(sj,MailTest.IT_ROBOT, content, title));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
