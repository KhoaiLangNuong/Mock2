package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.Validations;
import model.bean.Studio;

/**
 * ListUpdateStudioForm.java
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
public class ListUpdateStudioForm extends ActionForm {

	
	private ArrayList<Studio> listStudio;
	private String submit;
	private String submitNumberPager;
	private String contentSearch;
	private int currentPage;
	private int totalRecord;
	private int totalPage;
	private String dataUpdate;
	private int totalRecordDatabase;
	
	
	public int getTotalRecordDatabase() {
		return totalRecordDatabase;
	}
	public void setTotalRecordDatabase(int totalRecordDatabase) {
		this.totalRecordDatabase = totalRecordDatabase;
	}
	public String getDataUpdate() {
		return dataUpdate;
	}
	public void setDataUpdate(String dataUpdate) {
		this.dataUpdate = dataUpdate;
	}
	public String getSubmitNumberPager() {
		return submitNumberPager;
	}
	public void setSubmitNumberPager(String submitNumberPager) {
		this.submitNumberPager = submitNumberPager;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public ArrayList<Studio> getListStudio() {
		return listStudio;
	}
	public void setListStudio(ArrayList<Studio> listStudio) {
		this.listStudio = listStudio;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getContentSearch() {
		return contentSearch;
	}
	public void setContentSearch(String contentSearch) {
		this.contentSearch = contentSearch;
	}
	
	// check validate
	@SuppressWarnings("unchecked")
	public JSONObject checkValidate(ArrayList<Studio> listStudio) {
		JSONArray jsonArrayResponse = new JSONArray();
		boolean checkValid;
		for (int i = 0; i < listStudio.size(); i++) {
			checkValid = true;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", "" + i);
			jsonObject.put("message", "");

			// check valid for メーカー名
			// check valid contain special character
			if (Validations.containsSpecialCharacter(listStudio.get(i).getSysfiData())) {
				checkValid = false;
				jsonObject.put("message", " メーカー名 : 特殊文字を入力しません");
			}

			// valid max length
			if (!Validations.validLength(listStudio.get(i).getSysfiData(), 10)) {
				checkValid = false;
				jsonObject.put("message", jsonObject.get("message").toString() + " メーカー名 : 長さが無効です <=10 ");
				jsonObject.put("error_type", "length");
			}

			// valid space
			if (listStudio.get(i).getSysfiData().length() > 0
					&& Validations.validateSpace(listStudio.get(i).getSysfiData())) {
				checkValid = false;
				jsonObject.put("message", jsonObject.get("message").toString() + " メーカー名 : 全体のスペースを入力しないでください");
				jsonObject.put("error_type", "space");
			} else {
				listStudio.get(i).setSysfiData(listStudio.get(i).getSysfiData().trim());
			}

			// validate true or false
			if (!checkValid) {
				jsonObject.put("validate", "false");
			} else {
				jsonObject.put("validate", "true");
			}
			jsonArrayResponse.add(jsonObject);
		}
		JSONObject jsonObjectResponse = new JSONObject();
		jsonObjectResponse.put("jsonArray", jsonArrayResponse);
		for (int i = 0; i < jsonArrayResponse.size(); i++) {
			if ("false".equals(((JSONObject) jsonArrayResponse.get(i)).get("validate").toString())) {
				jsonObjectResponse.put("validate", "false");
				return jsonObjectResponse;
			}
		}
		jsonObjectResponse.put("validate", "true");
		return jsonObjectResponse;
	}

}
