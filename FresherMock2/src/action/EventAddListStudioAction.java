package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.StringProcess;
import common.Validations;
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
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StudioForm studioForm = (StudioForm) form;

		String[] listKey = studioForm.getListKey();
		String[] listData = studioForm.getListData();
		ArrayList<Studio> listStudio = new ArrayList<Studio>();
		Studio studio;
		System.out.println("AAAAAAAAAAAAAAA");
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
			JSONObject jsonObjectResponse= checkValidate(listStudio);
			String validate=jsonObjectResponse.get("validate").toString();
			if("true".equals(validate)){
				boolean kt = studioBO.signUp(listStudio);
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
	
	//check validate by JsonArray
	@SuppressWarnings("unchecked")
	public JSONObject checkValidate(ArrayList<Studio> listStudio){
		JSONArray jsonArrayResponse = new JSONArray();
		boolean checkValid;
		StudioBO studioBO= new StudioBO();
		for(int i=0; i<listStudio.size(); i++){
			checkValid=true;
			JSONObject jsonObject= new JSONObject();
			jsonObject.put("id",""+i);
			
			//check valid sysfi_key
			//check valid length
			jsonObject.put("message", "");
			if(!Validations.validLength(listStudio.get(i).getSysfiKey(), 2)){
				checkValid = false;
				jsonObject.put("message","メーカー・コード : 長さが無効です <=2");
			}
			
			//check valid contain special character
			if(Validations.containsSpecialCharacter(listStudio.get(i).getSysfiKey())){
				checkValid = false;
				jsonObject.put("message","メーカー・コード : 特殊文字を入力しません");
			}
			
			//check valid space
			if(Validations.validateSpace(listStudio.get(i).getSysfiKey())){
				checkValid = false;
				jsonObject.put("message","メーカー・コード : 全体のスペースを入力しないでください");
			}
			
			//check valid null
			if(Validations.validateNull(listStudio.get(i).getSysfiKey())){
				checkValid = false;
				jsonObject.put("message","メーカー・コード : 空いてませんしてください");
			}
			
			if(Validations.validateKey(listStudio.get(i).getSysfiKey(), listStudio, i)){
				checkValid = false;
				jsonObject.put("message","メーカー・コード  : 別のものを入力します");
			}
			
			//check key exist
			if(studioBO.checkKeyExist(listStudio.get(i).getSysfiKey())){
				checkValid = false;
				jsonObject.put("message", "メーカー・コード : すでに存在しています");
			}
			
			//check valid sysfi_data
			//check valid length
			if(!Validations.validLength(listStudio.get(i).getSysfiData(),10)){
				checkValid = false;
				if("".equals(jsonObject.get("message").toString())){
					jsonObject.put("message","メーカー名 : 長さが無効です <=10 ");
				}
				else{
					jsonObject.put("message",jsonObject.get("message").toString()+ ", メーカー名 : 長さが無効です <=10 ");
				}
			}
			
			//check valid contain special character
			if(Validations.containsSpecialCharacter(listStudio.get(i).getSysfiData())){
				checkValid=false;
				if("".equals(jsonObject.get("message").toString())){
					jsonObject.put("message","メーカー名 : 特殊文字を入力しません ");
				}
				else{
					jsonObject.put("message",jsonObject.get("message").toString()+ ", メーカー名 : 特殊文字を入力しません ");
				}
			}
			
			//check valid space
			if(listStudio.get(i).getSysfiData().length()>0 && Validations.validateSpace(listStudio.get(i).getSysfiData())){
				checkValid=false;
				if("".equals(jsonObject.get("message").toString())){
					jsonObject.put("message",jsonObject.get("message").toString()+ ", メーカー名 : 全体のスペースを入力しないでください");
				}
				else{
					jsonObject.put("message",jsonObject.get("message").toString()+ ", メーカー名 : 全体のスペースを入力しないでください");
				}				
			}
			else {
				listStudio.get(i).setSysfiData(listStudio.get(i).getSysfiData());
			}
			
			//check valid true or false
			if(!checkValid){
				jsonObject.put("validate","false");
			}else {
				jsonObject.put("validate","true");
			}
			jsonArrayResponse.add(jsonObject);
		}
		JSONObject jsonObjectResponse= new JSONObject();
		jsonObjectResponse.put("jsonArray", jsonArrayResponse);
		for(int i=0; i<jsonArrayResponse.size(); i++){
			if("false".equals(((JSONObject)jsonArrayResponse.get(i)).get("validate").toString())){
				jsonObjectResponse.put("validate", "false");
				return jsonObjectResponse;
			}
		}
		jsonObjectResponse.put("validate", "true");
		return jsonObjectResponse;
	}
}
