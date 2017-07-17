package pdf_reader;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import text_processor.PDFProcessor;

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

        String fileName = user_file.getText();
        boolean tf = !fileName.equals("File path here...");

        try {
            assert tf;
        } catch (AssertionError ae) {
            System.out.println("File error");
            return;
        }

        File file = new File(fileName);
        System.out.println("File name: " + file.getName() + "\nFile path: " + "\nAssert = " + tf);

        PDFProcessor pdfProcessor = new PDFProcessor();
        pdfProcessor.run(file.getPath());

        System.out.println(pdfProcessor.getInvoice());


    }
}
