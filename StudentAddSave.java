package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class StudentAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String Student_Username ;
    private String Student_Password ;
    private String Student_Name ;
    private String Student_Sex ;
    private String Student_Class ;
	public String getStudent_Username() {
		return Student_Username;
	}

	public void setStudent_Username(String studentUsername) {
		Student_Username = studentUsername;
	}

	public String getStudent_Password() {
		return Student_Password;
	}

	public void setStudent_Password(String studentPassword) {
		Student_Password = studentPassword;
	}

	public String getStudent_Name() {
		return Student_Name;
	}

	public void setStudent_Name(String studentName) {
		Student_Name = studentName;
	}

	public String getStudent_Sex() {
		return Student_Sex;
	}

	public void setStudent_Sex(String studentSex) {
		Student_Sex = studentSex;
	}

	public String getStudent_Class() {
		return Student_Class;
	}

	public void setStudent_Class(String studentClass) {
		Student_Class = studentClass;
	}

	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		String pageno="2";
		//��ѯ�û����Ƿ����
		List<StudentBean> list=new StudentDao().GetList("Student_Username='"+Student_Username+"'", "");
		if(list.size()>0)
		{
			out.print("<script language='javascript'>alert('�û����Ѿ����ڣ�');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//����
		StudentBean cnbean=new StudentBean();
		cnbean.setStudent_Username(Student_Username);
		cnbean.setStudent_Password(Student_Password);
		cnbean.setStudent_Name(Student_Name);
		cnbean.setStudent_Sex(Student_Sex);
		cnbean.setStudent_Class(Student_Class);
		cnbean.setStudent_State("δ��ס");
		cnbean.setStudent_DomitoryID(0);
		StudentDao sd = new StudentDao();
		sd.Add(cnbean);
		cnbean = sd.GetBean("Student_Username='"+Student_Username+"'");
		HobbyBean hbbean=new HobbyBean();
		hbbean.setStudent_ID(cnbean.getStudent_ID());
		hbbean.setEsport(0);
		hbbean.setLetter(0);
		hbbean.setSport(0);
		new HobbyDao().Add(hbbean);
		//��ת
		out.print("<script language='javascript'>alert('���ӳɹ���');window.location='StudentManager.action';</script>");
		out.flush();out.close();return null;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
//	public static void main(String[] args) {
//		System.out.println();
//	}
	
}