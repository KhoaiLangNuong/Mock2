
package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.Validations;
import model.bean.Studio;
import model.bo.StudioBO;

/**
 * StudioForm.java
 *
 * Version 1.0
 *
 * Date: 29/07/2016
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 29/07/2016       	NguyetNT6       Create
 */
@SuppressWarnings("serial")
public class StudioForm extends ActionForm {
	private String submit;
	private String[] listKey= new String[10];
	private String[] listData= new String[10];
	private String error;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String[] getListKey() {
		return listKey;
	}

	public void setListKey(String[] listKey) {
		this.listKey = listKey;
	}

	public String[] getListData() {
		return listData;
	}

	public void setListData(String[] listData) {
		this.listData = listData;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	//check validate by JsonArray
	@SuppressWarnings("unchecked")
	public JSONObject checkValidate(ArrayList<Studio> listStudio){
		JSONArray jsonArrayResponse = new JSONArray();
		boolean checkValid;
		JSONObject jsonObject;
		StudioBO studioBO= new StudioBO();
		System.out.println("sizzzzzzzzzzzzzze"+listStudio.size());
		if(listStudio.size()==0){
			checkValid=false;
			jsonObject= new JSONObject();
			jsonObject.put("id", 0);
			jsonObject.put("message", "あなたは、コンテンツが入力されていません");
			jsonObject.put("validate", "false");
			jsonArrayResponse.add(jsonObject);
		}
		for(int i=0; i<listStudio.size(); i++){
			checkValid=true;
			jsonObject= new JSONObject();
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
			
//			//check valid space
//			if(Validations.validateSpace(listStudio.get(i).getSysfiKey())){
//				checkValid = false;
//				jsonObject.put("message","メーカー・コード : 全体のスペースを入力しないでください");
//			}
//			
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
//			
//			//check valid space
//			if(listStudio.get(i).getSysfiData().length()>0 && Validations.validateSpace(listStudio.get(i).getSysfiData())){
//				checkValid=false;
//				if("".equals(jsonObject.get("message").toString())){
//					jsonObject.put("message",jsonObject.get("message").toString()+ ", メーカー名 : 全体のスペースを入力しないでください");
//				}
//				else{
//					jsonObject.put("message",jsonObject.get("message").toString()+ ", メーカー名 : 全体のスペースを入力しないでください");
//				}				
//			}
//			else {
//				listStudio.get(i).setSysfiData(listStudio.get(i).getSysfiData());
//			}
			
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
