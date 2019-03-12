package com.qtpselenium.pom.util;

public class DataUtil {

	
	public static Object[][] getTestData(Xls_Reader xls, String sheetName){

		int rows = xls.getRowCount(sheetName);
		int cols = xls.getColumnCount(sheetName);
		Object data[][]= new Object[rows-1][cols];

		//System.out.println(rows);
		//System.out.println(cols);
		for(int rNum=2;rNum<=rows;rNum++) {
			for(int cNum=0;cNum<cols;cNum++) {
				data[rNum-2][cNum] = xls.getCellData(sheetName, cNum, rNum);
				// 00 01 02
				// 10 11 12
				// 20 21 22
			}
			//System.out.println("-----------------");
		}
		
		return data;

	}
	// true- Y
	// false- N
	public static boolean isRunnable(Xls_Reader xls, String testName) {
		int rows=xls.getRowCount("TestCases");
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String test=xls.getCellData("TestCases", "TCID", rNum);
			if(test.equals(testName)) {
				String runmode=xls.getCellData("TestCases", "Runmode", rNum);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return  false;
	}
}
