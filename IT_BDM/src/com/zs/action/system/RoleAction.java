package com.zs.action.system;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zs.action.MyBaseAction;
import com.zs.entity.Permission;
import com.zs.entity.Role;
import com.zs.entity.RolePermission;
import com.zs.entity.Users;
import com.zs.entity.XtBranches;
import com.zs.service.IService;
import com.zs.tools.NameOfDate;
import com.zs.tools.Page;

public class RoleAction extends MyBaseAction{
	IService ser;
	Role r;
	List<Role> rs;
 	
	Page page;
	
	String result_role="role";
	String result_succ="succ";
	String result_fail="fail";
	
	public IService getSer() {
		return ser;
	}
	public void setSer(IService ser) {
		this.ser = ser;
	}
	public Role getR() {
		return r;
	}
	public void setR(Role r) {
		this.r = r;
	}
	public List<Role> getRs() {
		return rs;
	}
	public void setRs(List<Role> rs) {
		this.rs = rs;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	//------------------------------------------------
	public String queryOfFenye() throws UnsupportedEncodingException {
		String id=getRequest().getParameter("id");
		String cz=getRequest().getParameter("cz");//用于判断是否清理page，yes清理，no不清理
		if (page==null) {
			page=new Page(1, 0, 5);
		}
		if (cz!=null && cz.equals("yes")) {
			page=new Page(1, 0, 5);
		}
		if (id!=null) {
			String hql="from Role where RId  = ?";
			String ss[]={id};
			String hql2="from Role where RId = '"+id+"'";
			rs=ser.query(hql, ss, hql2, page, ser);
		}else {
			String hql="from Role";
			String ss[]={};
			String hql2="from Role";
			rs=ser.query(hql, ss, hql2, page, ser);
		}
		//带上该角色的权限
		for (int i = 0; i < rs.size(); i++) {
			List<Permission> permissions=ser.find("from Permission where PId in(select PId from RolePermission where RId=?)", new String[]{rs.get(i).getRId()});
			rs.get(i).setPs(permissions);
		}
		//带上权限列表
		List<Permission> ps=ser.find("from Permission", new String[]{});
		getRequest().setAttribute("ps", ps);
		return result_role;
	}
	
	public String delete() throws Exception {
		String id=getRequest().getParameter("id");
		if (id!=null) {
			//删除前先处理相关联的表
			List<RolePermission> rps=ser.find("from RolePermission where RId=?",new String[]{id});
			for (int i = 0; i < rps.size(); i++) {
				ser.delete(rps.get(i));
			}
			r=(Role) ser.get(Role.class, id);
			ser.delete(r);
		}
		r=null;
		return result_succ;
	}
	
	public String update() throws Exception {
		if(r!=null && r.getRId()!=null && !"".equals(r.getRId().trim())){
			//保存角色-权限关系
			List<Permission> list=new ArrayList<Permission>();
			List<Permission> pers=ser.find("from Permission",new String[]{});
			for (int i = 0; i < pers.size(); i++) {
				String str=getRequest().getParameter("per"+pers.get(i).getPId());
				if (str!=null) {
					list.add(pers.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				RolePermission rp=new RolePermission("rp"+NameOfDate.getNum(), r.getRId(), list.get(i).getPId());
				ser.save(rp);
			}
			ser.update(r);
		}
		getRequest().setAttribute("r", r);
		r=null;
		return result_succ;
	}
	
	public String add() throws UnsupportedEncodingException {
		if(r!=null){
			r.setRId("r"+NameOfDate.getNum());
			//保存角色-权限关系
			List<Permission> list=new ArrayList<Permission>();
			List<Permission> pers=ser.find("from Permission",new String[]{});
			for (int i = 0; i < pers.size(); i++) {
				String str=getRequest().getParameter("per"+pers.get(i).getPId());
				if (str!=null) {
					list.add(pers.get(i));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				RolePermission rp=new RolePermission("rp"+NameOfDate.getNum(), r.getRId(), list.get(i).getPId());
				ser.save(rp);
			}
			ser.save(r);
		}
		getRequest().setAttribute("r", r);
		r=null;
		return result_succ;
	}	
}
