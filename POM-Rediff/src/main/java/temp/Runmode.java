package temp;

import com.qtpselenium.pom.util.Xls_Reader;

public class Runmode {

	public static void main(String[] args) {
		String testName="DeletePortFolioTest";
		Xls_Reader xls = new Xls_Reader("D:\\Common\\reports\\Rediff Data.xlsx");
		int rows=xls.getRowCount("TestCases");
		
		for(int rNum=2;rNum<=rows;rNum++) {
			String test=xls.getCellData("TestCases", "TCID", rNum);
			if(test.equals(testName)) {
				String runmode=xls.getCellData("TestCases", "Runmode", rNum);
				if(runmode.equals("Y"))
					System.out.println("Yes");
				else
					System.out.println("No");
			}
		}

	}

}
