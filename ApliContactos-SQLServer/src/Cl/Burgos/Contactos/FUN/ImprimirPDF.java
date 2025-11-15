/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Contactos.FUN;

import Cl.Burgos.Contactos.BD.Log;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;


/**
 *
 * @author march
 */
public class ImprimirPDF {
    
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    
    public void ImprimirPDF(JTable table,String nombreUs,String nombreAr) {
        Directorio d = new Directorio();
        
        try {
            Document doc = new Document();
            
            PdfWriter.getInstance(doc, new FileOutputStream(d.selecDirectrorio()+"/"+nombreAr+".pdf"));
            doc.open();
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            //Agregando encabezados de tabla
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }
            //Extraer datos de la JTable e insertarlo en PdfPTable
            for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
                for (int cols = 0; cols < table.getColumnCount(); cols++) {
                    pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

                }
            }
            Paragraph preface = new Paragraph();
                // Añadimos una línea vacía
                addEmptyLine(preface, 1);
                // Permite escribir un encabezado grande
                preface.add(new Paragraph("Título del documento", catFont));
                addEmptyLine(preface, 1);
                
                // Creará: Informe generado por: _name, _date
                preface.add(new Paragraph("Informe generado por: Nombre de usuario " +nombreUs + ", " + new Date(),smallBold));
                addEmptyLine(preface, 1);
                
                preface.add(new Paragraph("Este documento describe algo que es muy importante ",smallBold));
                addEmptyLine(preface, 1);

                preface.add(new Paragraph("Este documento es una versión preliminar y no está sujeto a su contrato de licencia o cualquier otro acuerdo.",redFont));
            addEmptyLine(preface, 1);
            doc.add(preface);
            
            doc.add(pdfTable);
            doc.close();
            System.out.println("done");
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            Log.log(ex.getMessage());
           
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Hubo un error"+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            Log.log(ex.getMessage());
        }

    }
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
                for (int i = 0; i < number; i++) {
                        paragraph.add(new Paragraph(" "));
                }
        }
}
