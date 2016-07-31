package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.bean.Studio;

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
	
}