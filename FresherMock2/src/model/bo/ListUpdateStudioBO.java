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
	
	public ArrayList<Studio> getListStudio() {
		return listUpdateStudioDAO.getListStudio();
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
