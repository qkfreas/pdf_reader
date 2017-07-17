package text_processor;

/**
 * Created by qkfre on 10/26/2016.
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.FileNotFoundException;

public class PDFProcessor extends KeywordFinder {
    public PDFProcessor(){}

    public void run(String path) {
        String documentText = readPDF(path);
//        System.out.print("Test:\n " + documentText);
        String[] wordArr = toArray(documentText);
        processText(wordArr, getNextIndex(wordArr));
    }

    public String readPDF(String name){
        String st = "";
        PDDocument document = null;
        try {
            document = PDDocument.load(new File(name));
//            document.getClass();
            if (!document.isEncrypted()){
                PDFTextStripper stripper = new PDFTextStripper();
//                stripper.setSortByPosition(true);
                st = stripper.getText(document);
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
