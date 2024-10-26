/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.FUN;

import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 *
 * @author march
 */
public class ImprimirWord {
    public void ImprimirWord(JTable jTable,String nombreArchivo) {
           File file = null; 
           FileOutputStream fos = null; 
           XWPFDocument document = null; 
           XWPFParagraph para = null; 
           XWPFRun run = null;
           Directorio d = new Directorio();
           try { 
               // Crear el primer párrafo y establecer su texto. 
               document = new XWPFDocument(); 
               para = document.createParagraph(); 

               para.setAlignment(ParagraphAlignment.CENTER); 

               para.setSpacingAfter(100); 

               para.setSpacingAfterLines(10);
               run = para.createRun(); 
//               for(int i=1; i<=5; i++)
//               run.setText("Test Name Marchelo Value 12345678 Normal Ranges09384"); 
//               run.addBreak();    // similar a la nueva línea
//               run.addBreak();

               XWPFTable table = document.createTable(jTable.getRowCount(),jTable.getColumnCount());
               
               
                for (int i = 0; i < jTable.getColumnCount(); i++) {
                        System.out.println(jTable.getColumnName(i)+"-");
                        table.getRow(0).getCell(i).setText(jTable.getColumnName(i));
                        
                }
                for (int rows = 0; rows < jTable.getRowCount();rows++) {
				for (int cols = 0; cols < jTable.getColumnCount(); cols++) {
					System.out.print(jTable.getValueAt(rows, cols)+"-");
                                        table.getRow(rows).getCell(cols).setText(jTable.getValueAt(rows, cols).toString()); 
                                        
				}
				System.out.println();
			}
                
               table.setRowBandSize(1);
               table.setWidth(1);
               table.setColBandSize(1);
               table.setCellMargins(1, 1, 100, 30);


//             file = new File("c:/Users/march/Desktop/nwhpe.docx"); 
               
               String url=d.selecDirectrorio();
               file = new File(url+"/"+nombreArchivo+".docx"); 
               if(file.exists())
                   file.delete();


               FileOutputStream out = new FileOutputStream(file);
               document.write(out);
               out.close();
           } catch (Exception e){
               System.out.println("Error: "+e.getMessage());
           }
    }
}
