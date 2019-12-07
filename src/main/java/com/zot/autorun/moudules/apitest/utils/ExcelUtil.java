package com.zot.autorun.moudules.apitest.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取Excel工具类
 * @author jianping.zhou
 *
 */
public class ExcelUtil {
	
    public Workbook workbook;
    public Sheet sheet;
    public Cell cell;
    int rows;
    int columns;
    
    //文件路径
    public  String filePath;
    //文件名，不包含文件后缀.xls
    public  String fileName;
    //sheet名
    public String Sheet1 = "sku";
    public String Sheet2 = "psc";
    String sourceFile; //excel 文件路径

	
	public ExcelUtil(String filePath,String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	
	/**
	 * 读取Excel中的数据
	 * @return 返回数据 list 列表 数据库结构 ：{warehouse：{skuName：skuNum}} 
	 * @throws IOException
	 * @throws BiffException
	 */
	public List< Map<String, Map <String, String>>> getExcelData() throws IOException, BiffException{
		
//        workbook = Workbook.getWorkbook(new FileInputStream(this.getInputString(filePath, fileName)));
		
	 	File file = new File(filePath+fileName+".xls");
        // 创建输入流，读取Excel  
        InputStream is = new FileInputStream(file.getAbsolutePath());  
        workbook = Workbook.getWorkbook(is);
        is.close();
        
        sheet = workbook.getSheet(Sheet1);
        //获取该sheet行数
        rows = sheet.getRows();
        //获取该sheet列数
        columns = sheet.getColumns();
        
        System.out.println("rows:"+ rows);
        System.out.println("columns:"+ columns);
        
        List<Map <String, Map <String, String>>> listData =new ArrayList<Map<String, Map <String, String>>>();
        Map<String, Map<String, String>> map0 = new HashMap<String, Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        
        // 遍历当前sheet中的所有列
        for (int j = 1; j < columns; j++) {
        	//获取 表格中第二行 所有仓库的名称
            String warehouse = sheet.getCell(j, 1).getContents();
            // map0 数据结构为{warehouse：{skuName：skuNum}}
            //map0 = new HashMap<String, Map<String, String>>();
            // 遍历所有的行
            for (int y = 2; y < rows ; y++) {
            	//取excel表格中第一列的sku 名称
            	String skuName = sheet.getCell(0, y).getContents();
            	
            	//遍历取excel表中每个sku 所需要下单的个数
            	String skuNum = sheet.getCell(j,y).getContents();
            	//map 数据结构为 {skuName：skuNum}
            	//map = new HashMap<String, String>();
            	if( skuNum != "") {
            		//System.out.println(" 第 "+ j + "列" + "第 " +y+"行" +""+"= "+skuName+","+skuNum);
            		//每次new map对象，避免同一个map把相同的key 值被最后一个覆盖
            		map = new HashMap<String, String>();
            		map0 = new HashMap<String, Map<String, String>>();
            		map.put(skuName, skuNum);            	
            		map0.put(warehouse, map);
            		listData.add(map0);            		
            	}
            	
            }
        }
        return listData;

	}
	
	public String getPsc(String warehouse) {
		
		String cellinfo = null;
	 	File file = new File(filePath+fileName+".xls");
        // 创建输入流，读取Excel  
        InputStream is =null;
		try {
			is = new FileInputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        try {
			workbook = Workbook.getWorkbook(is);
			is.close();
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        sheet = workbook.getSheet(Sheet2);
        //获取该sheet行数
        //rows = sheet.getRows();
        //获取该sheet列数
        columns = sheet.getColumns();
        //System.out.println("psc总列数：" + columns);
        
        //遍历总列数
        for (int i = 1; i < columns; i++) {  
            // sheet.getColumns()返回该页的总列数  
        	if (sheet.getCell(i,1).getContents().equals(warehouse)) {
        		 cellinfo = sheet.getCell(i, 2).getContents();
        		 if(null !=cellinfo && cellinfo == "") {
        			 cellinfo = "OW01010327";
        		 }
        		 //System.out.println(sheet.getCell(i,1).getContents()+":"+cellinfo);                      		
        		}
        }
		return cellinfo;
		
	}
	
    
	
	/**
	 * 将上架成功的 仓库，商品编码，数量写入Excel文件中
	 * @param sku 上架数据实体类（仓库，商品编码，数量）
	 */
	public void setResult(List<SuccessSku> sku) {
		WritableWorkbook wwb = null;
		File file = new File(filePath+fileName+".xls");
        // 创建输出流，读取Excel  
        OutputStream os =null;
		try {
			os = new FileOutputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        try {
        	wwb = Workbook.createWorkbook(os);
        	WritableSheet sheet =wwb.createSheet("result", 3);
        	
        	// 要插入到的Excel表格的行号，默认从0开始
        	Label labelWarehhouse = new Label(0, 0, "仓库编码", getHeader());
        	Label labelSkuName = new Label(1, 0, "商品名称", getHeader());
        	Label labelSkuNum = new Label(2, 0, "商品数量", getHeader());
        	Label labelStatus = new Label(3, 0, "状态", getHeader());
        	Label labelfailStatus = new Label(4, 0, "备注", getHeader());
        	//设置每列的宽度
        	sheet.setColumnView(0, 20);
        	sheet.setColumnView(1, 30);
        	sheet.setColumnView(2, 20);
        	sheet.setColumnView(3, 30);
        	sheet.setColumnView(4, 80);

			try {
				sheet.addCell(labelWarehhouse);
				sheet.addCell(labelSkuName);
				sheet.addCell(labelSkuNum);
				sheet.addCell(labelStatus);
				sheet.addCell(labelfailStatus);
			} catch (RowsExceededException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
			
            //设置单元格属性
            WritableCellFormat wc = new WritableCellFormat();
            WritableCellFormat wcf = new WritableCellFormat();

            try {
                // 设置居中
                wc.setAlignment(Alignment.CENTRE);
                // 设置边框线
				wc.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
				wcf.setBackground(jxl.format.Colour.RED);
				
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0; i < sku.size(); i++) {
	        	// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
				//成功标记
				if(sku.get(i).getStatus().equals("success")) {
		        	Label labelWarehhouse_i = new Label(0, i+1, sku.get(i).getWarehouse(), wc);
		        	Label labelSkuName_i = new Label(1, i+1, sku.get(i).getSkuName(), wc);
		        	Label labelSkuNum_i = new Label(2, i+1, sku.get(i).getSkuNum()+"", wc);
		        	Label labaeStatus_i	= new Label(3, i+1, "上架成功", wc);
		        	Label labaefailStatus_i	= new Label(4, i+1, "", wc);
		        	
		        	try {
						sheet.addCell(labelWarehhouse_i);
						sheet.addCell(labelSkuName_i);
						sheet.addCell(labelSkuNum_i);
						sheet.addCell(labaeStatus_i);
						sheet.addCell(labaefailStatus_i);
						
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//失败标记
				if(sku.get(i).getStatus().equals("fail")) {
		        	Label labelWarehhouse_i = new Label(0, i+1, sku.get(i).getWarehouse(), wc);
		        	Label labelSkuName_i = new Label(1, i+1, sku.get(i).getSkuName(), wc);
		        	Label labelSkuNum_i = new Label(2, i+1, sku.get(i).getSkuNum(), wc);
		        	Label labaeStatus_i	= new Label(3, i+1, "下单失败,无垃圾数据产生", wcf);
		        	Label labaefailStatus_i	= new Label(4, i+1, sku.get(i).getFailStatus(), wcf);
		        	
		        	try {
						sheet.addCell(labelWarehhouse_i);
						sheet.addCell(labelSkuName_i);
						sheet.addCell(labelSkuNum_i);
						sheet.addCell(labaeStatus_i);	
						sheet.addCell(labaefailStatus_i);
						
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
        	wwb.write();
        	try {
				wwb.close();
				os.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
    /**
     * 设置单元格格式
     * @return
     */
	public static WritableCellFormat getHeader() {
		// 定义字体
		WritableFont font = new WritableFont(WritableFont.TIMES, 10,
				WritableFont.BOLD);
		try {
			// 黑色字体
			font.setColour(jxl.format.Colour.RED);
		} catch (WriteException e1) {
			e1.printStackTrace();
		}
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			// 左右居中
			format.setAlignment(jxl.format.Alignment.CENTRE);
			// 上下居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 黑色边框
			format.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			// 黄色背景
			format.setBackground(jxl.format.Colour.BRIGHT_GREEN);
			
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return format;
	}	
	
	
    public static void main(String[] args) {
//    	ExcelReadUtil er = new ExcelReadUtil("","InventoryData");
//    	List<Map<String, Map<String, String>>> ls = null;
//        //Map<String, Map<String, String>> map0 = new HashMap<String, Map<String, String>>();
//        //Map<String, String> map = new HashMap<String, String>();
//		
//        try {
//			ls = er.getExcelData();
//		} catch (BiffException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	for (int i=0; i<ls.size(); i++ ) {
//
//    		for(Map.Entry<String, Map<String, String>> entry :ls.get(i).entrySet()) {  
//    			//System.out.println("key= " + entry.getKey() + 
//    			//		" and value= "+ entry.getValue());
//    			//if(entry.getKey().equals("USWC")) {
//    				//System.out.println(entry.getValue());
//    			String warehouse = entry.getKey();
//    				for(String str :entry.getValue().keySet()) {
//    					System.out.println("warehouse: "+ warehouse+
//    				"; skuName: " + str + 
//    	    		"; skuNum: "+ Integer.parseInt(entry.getValue().get(str)));
//    				}
//    			//}
//    		} 
//    	
//    		
//    	}
//    	
//		System.out.println("USWC : "+er.getPsc("USWC"));
//		System.out.println("USKY2 : "+er.getPsc("USKY2"));
//		System.out.println("PLSC : "+er.getPsc("PLSC"));
//
	
//	//写入Excel文件
//	SuccessSku su =null;
//	ExcelUtil er = new ExcelUtil("","SuccessResult");
//	List<SuccessSku> list = new ArrayList<SuccessSku>();
//	 su = new SuccessSku("USWC","LB001", 5);
//	 list.add(su);
//	 su = new SuccessSku("UKMA","auto_sku", 15);
//	 list.add(su);
//
//	 er.setResult(list);
		
    }
       
}
