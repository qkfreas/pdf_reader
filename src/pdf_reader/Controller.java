package pdf_reader;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.pdfbox.cos.COSDocument;
import org.pdfbox.io.RandomAccessFileInputStream;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import java.io.*;


public class Controller {
    @FXML
    private TextField user_file;

    @FXML
    private void get_file() {
        FileChooser fileChooser = new FileChooser();
        File file;
        try {
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extensionFilter);
            file = fileChooser.showOpenDialog(new Stage(StageStyle.UTILITY));
            user_file.setText(file.getPath());
        } catch (NullPointerException npe) {
//            npe.printStackTrace();
            System.out.print("No file chosen");
        }
    }

    @FXML
    private void process_file() {
        PDFTextStripper pdfTextStripper;
        PDDocument pdDocument;
        COSDocument cosDocument;

        String fileName = user_file.getText();
        boolean tf = !fileName.equals("File path here...");

        try {
            assert tf;
        } catch (AssertionError ae) {
            System.out.println("File error");
            return;
        }

        File file = new File(fileName);
        System.out.println("File name: " + file.getName() + "\nAssert = " + tf);
        try {
System.out.println("Step 1");
            FileInputStream fileInputStream = new FileInputStream(file);
System.out.println("Step 2");
            PDFParser parser = new PDFParser(fileInputStream);
System.out.println("Step 3");
            parser.parse();
System.out.println("Step 4");
            cosDocument = parser.getDocument();
            pdfTextStripper = new PDFTextStripper();
            pdDocument = new PDDocument(cosDocument);
            pdfTextStripper.setStartPage(1);
            pdfTextStripper.setEndPage(5);
            String parsedText = pdfTextStripper.getText(pdDocument);
            System.out.println(parsedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
