package model.bo;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.bean.Studio;
import model.dao.ListUpdateStudioDAO;

/**
 * ListUpdateStudioBO.java
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
public class ListUpdateStudioBO {

	private ListUpdateStudioDAO listUpdateStudioDAO;
	public ListUpdateStudioBO(){
		listUpdateStudioDAO= new ListUpdateStudioDAO();
	}
	public ArrayList<Studio> getListStudioAtPage(ArrayList<Studio> listStudio, int currentPage, int totalRecord) {
		ArrayList<Studio> listStudioAtPage= new ArrayList<Studio>();
		int length=0;
		int totalPage=getTotalPage(listStudio,totalRecord);
		if(listStudio.size()==0){
			return listStudioAtPage;
		}
		if(listStudio.size()%totalRecord==0){
			length=currentPage*totalRecord;
		}
		else {
			if(currentPage<totalPage){
				length=currentPage*totalRecord;
			}
			else if(currentPage==totalPage){
				length=(currentPage-1)*totalRecord+listStudio.size()%totalRecord;
			}
		}
		
		for(int i=(currentPage-1)*totalRecord; i<length; i++){
			listStudioAtPage.add(listStudio.get(i));
		}
		return listStudioAtPage;
	}
	public ArrayList<Studio> getListStudio() {
		return listUpdateStudioDAO.getListStudio();
	}
	public int getTotalPage(ArrayList<Studio> listStudio, int totalRecord) {
		if(listStudio.size()==0){
			return 0;
		}
		else{
			if(listStudio.size()%totalRecord==0){
				return listStudio.size()/totalRecord;
			}
			else {
				return listStudio.size()/totalRecord+1;
			}
		}
	}
	public ArrayList<Studio> parseJsonToListStudio(JSONArray jsonArray) {
		ArrayList<Studio> listStudioUpdate= new ArrayList<Studio>();
		for(int i=0; i<jsonArray.size(); i++){
			JSONObject jsonObject=(JSONObject)jsonArray.get(i);
			Studio studio= new Studio();
			studio.setAction(jsonObject.get("action").toString());
			studio.setSysfiKey(jsonObject.get("sysfi_key").toString());
			studio.setSysfiData(jsonObject.get("sysfi_data").toString());
			listStudioUpdate.add(studio);
		}
		return listStudioUpdate;
	}
	public boolean updateData(ArrayList<Studio> listStudioUpdate) {
		return listUpdateStudioDAO.updateData(listStudioUpdate);
	}
	public boolean deleteData(ArrayList<Studio> listStudioUpdate) {
		return listUpdateStudioDAO.deleteData(listStudioUpdate);
	}
	public ArrayList<Studio> search(String contenSearch) {
		return listUpdateStudioDAO.search(contenSearch);
	}
	
}
