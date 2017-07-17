package text_processor;

/**
 * Created by qkfre on 10/26/2016.
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;

public class PDFProcessor extends KeywordFinder {
    public PDFProcessor(){}



    public String readPDF(String name){
        String st = "";
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(name));
            document.getClass();
            if (!document.isEncrypted()){
//                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
//                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                st = Tstripper.getText(document);
//                System.out.print("Test:\n " + st);
            }
        }
        catch (NullPointerException npe) {
            System.out.print("no file selected");
        }
        catch (FileNotFoundException fnf) {
            System.out.print("File not found.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (document != null)
                    document.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return st;
    }
}
