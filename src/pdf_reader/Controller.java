package pdf_reader;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


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
        File file = new File(user_file.getText());
        try {
            PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(file));
            parser.parse();
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
