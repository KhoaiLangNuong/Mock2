package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;

import common.BussinessException;
import common.ProgramException;
import common.StringProcess;
import form.StudioForm;
import model.bean.Studio;
import model.bo.StudioBO;

/**
 * EventAddListStudioAction.java
 *
 * Version 1.0
 *
 * Date: 29-07-2016
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 29-07-2016 NguyetNT6 Create
 */
public class EventAddListStudioAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception  {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StudioForm studioForm = (StudioForm) form;
		String[] listKey = studioForm.getListKey();
		String[] listData = studioForm.getListData();
		ArrayList<Studio> listStudio = new ArrayList<Studio>();
		Studio studio;
		StudioBO studioBO = new StudioBO();
		System.out.println("lissssst length " + listKey.length);
		String submit= StringProcess.toUTF8(studioForm.getSubmit());
		if("登録(U)".equals(submit)){
			for (int i = 0; i < listKey.length; i++) {
				if(!"".equals(listKey[i]) || !"".equals(listData[i])){
					studio = new Studio();
					studio.setSysfiKey(listKey[i]);
					studio.setSysfiData(StringProcess.toUTF8(listData[i]));
					System.out.println("data["+i+"]"+studio.getSysfiData());
					listStudio.add(studio);
				}
			}
			JSONObject jsonObjectResponse= studioForm.checkValidate(listStudio);
			String validate=jsonObjectResponse.get("validate").toString();
			if("true".equals(validate)){
				boolean kt;
				
				try {
					kt = studioBO.signUp(listStudio);
				} catch (BussinessException e) {
					kt = false;
				} catch (ProgramException e) {
					kt = false;
				}
				System.out.println("ktChen" + kt);
				if(kt){
					return mapping.findForward("addOK");
				}
				else{
					return mapping.findForward("addError");
				}
			}
			else {
				for(int i=0; i<listKey.length; i++){
					if(!"".equals(listKey[i]) || !"".equals(listData[i])){
						listKey[i]=StringProcess.toUTF8(listKey[i]);
						listData[i]=StringProcess.toUTF8(listData[i].trim());
					}
					else {
						listKey[i]="";
						listData[i]="";
					}
				}
				studioForm.setListKey(listKey);
				studioForm.setListData(listData);
				studioForm.setError(jsonObjectResponse.toString());
				return mapping.findForward("addError");
			}
		}
		return mapping.findForward("addError");
	}
}
