package model.bean;
/**
 * Studio.java
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
public class Studio {
	private String sysfiKey;
	private String sysfiData;
	private String index;
	private String action;
	
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSysfiKey() {
		return sysfiKey;
	}

	public void setSysfiKey(String sysfiKey) {
		this.sysfiKey = sysfiKey;
	}

	public String getSysfiData() {
		return sysfiData;
	}

	public void setSysfiData(String sysfiData) {
		this.sysfiData = sysfiData;
	}

}
