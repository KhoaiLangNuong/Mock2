package form;

import org.apache.struts.action.ActionForm;

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

}
