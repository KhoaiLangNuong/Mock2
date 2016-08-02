package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.FileProcess;
import common.StringProcess;
import common.Validations;
import form.ListUpdateStudioForm;
import model.bean.Studio;
import model.bo.ListUpdateStudioBO;

/**
 * EventUpdateListStudioAction.java
 *
 * Version 1.0
 *
 * Date: 29/07/2016
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 29/07/2016 NguyetNT6 Create
 */
public class EventUpdateListStudioAction extends Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ListUpdateStudioForm updateForm = (ListUpdateStudioForm) form;
		ListUpdateStudioBO updateStudioBO = new ListUpdateStudioBO();
		int totalRecord = 5;
		int currentPage = 1;
		ArrayList<Studio> listStudio;
		String contenSearch = updateForm.getContentSearch();
		System.out.println("currentpaaaaaaaaaaaage" + updateForm.getCurrentPage());

		// click button search
		if ("検索(S)".equals(StringProcess.toUTF8(updateForm.getSubmit()))) {
			System.out.println(StringProcess.toUTF8(updateForm.getSubmit()));

			// no input
			if (contenSearch == null || contenSearch.length() == 0) {
				updateForm.setContentSearch(contenSearch);
				listStudio = updateStudioBO.getListStudio();
				updateForm.setListStudio(listStudio);
			}

			// search with contentSearch
			else {
				updateForm.setContentSearch(contenSearch);
				listStudio = updateStudioBO.search(contenSearch);
				updateForm.setListStudio(listStudio);
			}
		} else {
			listStudio = updateStudioBO.getListStudio();
		}

		// click button update
		if ("update".equals(updateForm.getSubmit())) {
			String dataUpdate = updateForm.getDataUpdate();
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(dataUpdate);
			ArrayList<Studio> listStudioUpdate = updateStudioBO.parseJsonToListStudio(jsonArray);
			JSONObject jsonObjectError = checkValidate(listStudioUpdate);
			String validate = jsonObjectError.get("validate").toString();
			PrintWriter writer = response.getWriter();
			System.out.println("validate" + validate);

			// if validate true : update or delete list studio
			if ("true".equals(validate)) {
				JSONObject dataResponse = new JSONObject();
				if (updateStudioBO.updateData(listStudioUpdate) && updateStudioBO.deleteData(listStudioUpdate)) {
					dataResponse.put("result", "success");
				} else {
					System.out.println("failed");
					dataResponse.put("result", "failed");
				}
				writer.println(dataResponse.toString());
				return null;
			} else {
				jsonObjectError.put("result", "failed_validate");
				writer.println(jsonObjectError.toString());
				return null;
			}
		}
		if ("表示".equals(StringProcess.toUTF8(updateForm.getSubmit()))) {
			totalRecord = updateForm.getTotalRecord();
			System.out.println("totalRecorddddddddd" + totalRecord);
		}

		// // click export file exel
		// if ("エクスポート(E)".equals(StringProcess.toUTF8(updateForm.getSubmit())))
		// {
		// FileProcess.exportFileExecl(listStudio, "D:/write_test.xls");
		// }
		// exel
		if ("エクスポート(E)".equals(StringProcess.toUTF8(updateForm.getSubmit()))) {
			totalRecord = updateForm.getTotalRecord();
			if (totalRecord > listStudio.size()) {
				totalRecord = listStudio.size();
			}
			currentPage = updateForm.getCurrentPage();
			listStudio = updateStudioBO.getListStudioAtPage(listStudio, currentPage, totalRecord);
			FileProcess.exportFileExecl(listStudio, "D:/write_test.xls");
		}

		// click button 表示
		if ("表示".equals(StringProcess.toUTF8(updateForm.getSubmitNumberPager()))) {
			System.out.println("submit number page" + updateForm.getCurrentPage());
			currentPage = updateForm.getCurrentPage();
			totalRecord=updateForm.getTotalRecord();
		}

		if (totalRecord > listStudio.size()) {
			totalRecord = listStudio.size();
		}
		int totalPage = updateStudioBO.getTotalPage(listStudio, totalRecord);
		listStudio = updateStudioBO.getListStudioAtPage(listStudio, currentPage, totalRecord);
		updateForm.setCurrentPage(currentPage);
		updateForm.setListStudio(listStudio);
		updateForm.setTotalPage(totalPage);
		updateForm.setTotalRecordDatabase(updateStudioBO.getListStudio().size());
		return mapping.findForward("updateOK");
	}

	// check validata
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
