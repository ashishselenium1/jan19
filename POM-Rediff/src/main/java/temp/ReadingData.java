package temp;

import com.qtpselenium.pom.util.Xls_Reader;

public class ReadingData {

	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("D:\\Common\\reports\\Rediff Data.xlsx");
		String sheetName="CreatePortfolioTest";
		
		int rows = xls.getRowCount("CreatePortfolioTest");
		int cols = xls.getRowCount("CreatePortfolioTest");
		Object data[][]= new Object[rows-1][cols];

		System.out.println(rows);
		System.out.println(cols);
		for(int rNum=2;rNum<=rows;rNum++) {
			for(int cNum=0;cNum<cols;cNum++) {
				data[rNum-2][cNum] = xls.getCellData("CreatePortfolioTest", cNum, rNum);
				// 00 01 02
				// 10 11 12
				// 20 21 22
			}
			System.out.println("-----------------");
		}

	}

}
