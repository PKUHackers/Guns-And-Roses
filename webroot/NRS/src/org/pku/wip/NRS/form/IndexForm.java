package org.pku.wip.NRS.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class IndexForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8081204009409895024L;
	private String userName = "";
	private String passWord = "";
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.passWord = null;
		this.userName = null;
	}
}
