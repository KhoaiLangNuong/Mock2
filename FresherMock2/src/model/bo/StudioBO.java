package model.bo;

import java.util.ArrayList;

import common.ProgramException;
import common.SystemException;
import model.bean.Studio;
import model.dao.StudioDAO;

/**
 * StudioBO.java
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
public class StudioBO {
	
	private StudioDAO studioDAO ;
	
	public StudioBO(){
	studioDAO = new StudioDAO();
	}
	public boolean signUp(ArrayList<Studio> listStudio) throws SystemException, ProgramException{
		return studioDAO.signUp(listStudio);
	}

	public boolean checkKeyExist(String sysfiKey) {
		return studioDAO.checkKeyExist(sysfiKey);
	}
	
}
