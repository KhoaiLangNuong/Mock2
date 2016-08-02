package common;

import java.io.File;
import java.util.ArrayList;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import model.bean.Studio;

/**
 * FileProcess.java
 *
 * Version 1.0
 *
 * Date: 29-07-2016
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 29-07-2016 NguyetNT6 Create
 */
public class FileProcess {

	
	public static void exportFileExecl(ArrayList<Studio> listStudio, String filePath){
		try {
			File exlFile= new File(filePath);
			WritableWorkbook writableWorkBook = Workbook.createWorkbook(exlFile);
			WritableSheet writableSheet = writableWorkBook.createSheet("Sheet1", 0);
			Label columName1= new Label(0, 0,"メーカー・コード");
			Label columName2= new Label(1, 0,"メーカー名");
			writableSheet.addCell(columName1);
			writableSheet.addCell(columName2);
			Label cellKey;
			Label cellData;
			for(int i=0; i<listStudio.size(); i++ ){
				cellKey= new Label(0, i+1, listStudio.get(i).getSysfiKey());
				cellData= new Label(1, i+1, listStudio.get(i).getSysfiData());
				writableSheet.addCell(cellKey);
				writableSheet.addCell(cellData);
			}
			writableWorkBook.write();
			writableWorkBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
