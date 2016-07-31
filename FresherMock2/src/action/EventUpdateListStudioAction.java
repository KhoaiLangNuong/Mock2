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

public class EventUpdateListStudioAction extends Action{
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ListUpdateStudioForm updateForm=(ListUpdateStudioForm)form;
		ListUpdateStudioBO updateStudioBO= new ListUpdateStudioBO();
		int totalRecord=5;
		int currentPage=1;
		ArrayList<Studio> listStudio;
		String contenSearch = updateForm.getContentSearch();
		System.out.println("currentpaaaaaaaaaaaage"+updateForm.getCurrentPage());
		if("検索(S)".equals(StringProcess.toUTF8(updateForm.getSubmit()))){
			System.out.println(StringProcess.toUTF8(updateForm.getSubmit()));
			if (contenSearch == null || contenSearch.length() == 0 ){
				updateForm.setContentSearch(contenSearch);
				listStudio=updateStudioBO.getListStudio();
				updateForm.setListStudio(listStudio);
			}
			else{
				updateForm.setContentSearch(contenSearch);
				listStudio=updateStudioBO.search(contenSearch);
				updateForm.setListStudio(listStudio);
			}
		}
		else{
			listStudio=updateStudioBO.getListStudio();
		}
		if("update".equals(updateForm.getSubmit())){
			String dataUpdate=updateForm.getDataUpdate();
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray=(JSONArray)jsonParser.parse(dataUpdate);
			ArrayList<Studio> listStudioUpdate= updateStudioBO.parseJsonToListStudio(jsonArray);
			JSONObject jsonObjectError = checkValidate(listStudioUpdate);
			String validate=jsonObjectError.get("validate").toString();
			PrintWriter writer=response.getWriter();
			System.out.println("validate"+validate);
			if("true".equals(validate)){
				JSONObject dataResponse= new JSONObject();
				if(updateStudioBO.updateData(listStudioUpdate) && updateStudioBO.deleteData(listStudioUpdate)){
					dataResponse.put("result", "success");
				}
				else {
					System.out.println("failed");
					dataResponse.put("result", "failed");
				}
				writer.println(dataResponse.toString());
				return null;
			}
			else {
				jsonObjectError.put("result","failed_validate");
				writer.println(jsonObjectError.toString());
				return null;
			}
		}
		if("表示".equals(StringProcess.toUTF8(updateForm.getSubmit()))){
			totalRecord=updateForm.getTotalRecord();
			if(totalRecord==-1){
				totalRecord= listStudio.size();
			}
			System.out.println("totalRecorddddddddd"+totalRecord);
		}
		if("エクスポート(E)".equals(StringProcess.toUTF8(updateForm.getSubmit()))){
			FileProcess.exportFileExecl(listStudio, "D:/write_test.xls");
		}
		if("表示".equals(StringProcess.toUTF8(updateForm.getSubmitNumberPager()))){
			System.out.println("submit number page"+updateForm.getCurrentPage());
			currentPage=updateForm.getCurrentPage();
		}
		
		if(totalRecord>listStudio.size()){
			totalRecord=listStudio.size();
		}
		int totalPage= updateStudioBO.getTotalPage(listStudio,totalRecord);
		listStudio=updateStudioBO.getListStudioAtPage(listStudio, currentPage,totalRecord);
		updateForm.setCurrentPage(currentPage);
		updateForm.setListStudio(listStudio);
		updateForm.setTotalPage(totalPage);
		return mapping.findForward("updateOK");
		
	}
	@SuppressWarnings("unchecked")
	public JSONObject checkValidate(ArrayList<Studio> listStudio){
		JSONArray jsonArrayResponse = new JSONArray();
		boolean checkValid;
		for(int i=0; i<listStudio.size(); i++){
			checkValid=true;
			JSONObject jsonObject= new JSONObject();
			jsonObject.put("id",""+i);
			//check valid sysfi_key
			//check valid length
			jsonObject.put("message", "");
			if(!Validations.validLength(listStudio.get(i).getSysfiKey(), 2)){
				checkValid=false;
				jsonObject.put("message","メーカー・コード : 長さが無効です <=2");
			}
			if(Validations.validateSpace(listStudio.get(i).getSysfiKey())){
				checkValid=false;
				jsonObject.put("message","メーカー・コード : 全体のスペースを入力しないでください");
			}
			//check valid sysfi_data
			if(!Validations.validLength(listStudio.get(i).getSysfiData(),10)){
				checkValid=false;
				jsonObject.put("message",jsonObject.get("message").toString()+ " メーカー名 : 長さが無効です <=10 ");
			}
			if(Validations.validateSpace(listStudio.get(i).getSysfiData())){
				checkValid=false;
				jsonObject.put("message",jsonObject.get("message").toString()+ " メーカー名 : 全体のスペースを入力しないでください");
			}
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
