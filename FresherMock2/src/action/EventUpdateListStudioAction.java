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
import common.Pagination;
import common.StringProcess;
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
		int totalRecord = 10;
		int currentPage = 1;
		ArrayList<Studio>listStudio= updateStudioBO.getListStudio();
		System.out.println("currentpaaaaaaaaaaaage" + updateForm.getCurrentPage());
		
		// click button update
		if ("update".equals(updateForm.getSubmit())) {
			String dataUpdate = updateForm.getDataUpdate();
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(dataUpdate);
			ArrayList<Studio> listStudioUpdate = updateStudioBO.parseJsonToListStudio(jsonArray);
			JSONObject jsonObjectError = updateForm.checkValidate(listStudioUpdate);
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
		if ("エクスポート(E)".equals(StringProcess.toUTF8(updateForm.getSubmit()))) {
			totalRecord = updateForm.getTotalRecord();
			if (totalRecord > listStudio.size()) {
				totalRecord = listStudio.size();
			}
			currentPage = updateForm.getCurrentPage();
			ArrayList<Studio> lStudios = Pagination.getListStudioAtPage(listStudio, currentPage, totalRecord);
			FileProcess.exportFileExecl(lStudios, "D:/write_test.xls");
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
		int totalPage = Pagination.getTotalPage(listStudio, totalRecord);	
		listStudio = Pagination.getListStudioAtPage(listStudio, currentPage, totalRecord);
		updateForm.setCurrentPage(currentPage);
		updateForm.setListStudio(listStudio);
		updateForm.setTotalPage(totalPage);
		updateForm.setTotalRecordDatabase(updateStudioBO.getListStudio().size());
		return mapping.findForward("updateOK");
	}
}
