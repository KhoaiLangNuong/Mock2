package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Pagination;
import common.StringProcess;
import form.ListUpdateStudioForm;
import model.bean.Studio;
import model.bo.ListUpdateStudioBO;

public class EventSearchListStudioAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ListUpdateStudioForm updateForm = (ListUpdateStudioForm) form;
		ListUpdateStudioBO updateStudioBO = new ListUpdateStudioBO();
		String contenSearch = updateForm.getContentSearch();
		ArrayList<Studio> listStudio=null;
		int totalRecord = 10;
		int currentPage = 1;
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
		return mapping.findForward("searchOK");
	}

}
