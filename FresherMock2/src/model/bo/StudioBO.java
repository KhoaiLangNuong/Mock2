package model.bo;

import java.util.ArrayList;

import common.ProgramException;
import common.SystemException;
import model.bean.Studio;
import model.dao.StudioDAO;

public class StudioBO {
	
	private StudioDAO studioDAO ;
	
	public StudioBO(){
	studioDAO = new StudioDAO();
	}
	public boolean signUp(ArrayList<Studio> listStudio) throws SystemException, ProgramException{
		return studioDAO.signUp(listStudio);
	}
	
}
