package common;

import java.util.ArrayList;



public class Pagination {

	public static <T> ArrayList<T> getListStudioAtPage (ArrayList<T> listStudio, int currentPage, int totalRecord) {
		ArrayList<T> listStudioAtPage= new ArrayList<T>();
		int length = 0;
		int totalPage=getTotalPage(listStudio,totalRecord);
		if(listStudio.size()==0){
			return listStudioAtPage;
		}
		try {
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
		} catch (ArithmeticException e) {
			length=0;
		}
		
		for(int i=(currentPage-1)*totalRecord; i<length; i++){
			listStudioAtPage.add(listStudio.get(i));
		}
		return listStudioAtPage;
	}

	public static <T> int getTotalPage(ArrayList<T> listStudio, int totalRecord) {
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
}
