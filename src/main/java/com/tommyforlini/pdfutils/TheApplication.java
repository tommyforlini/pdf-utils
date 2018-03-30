package com.tommyforlini.pdfutils;

import java.io.FileOutputStream;
import java.lang.reflect.Field;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String userHome = System.getProperty("user.home");
		System.err.println("Using user-home: " + userHome);

		String userWritePath = userHome + "/Downloads/";
		System.err.println("Using write-path: " + userWritePath);

		if (args.length < 1) {
			System.err.println("\n\nExiting, input arguments required.\n\n");
			System.exit(0);
		}

		// step 1
		Document document = new Document();
		// step 2
		PdfCopy copy = new PdfCopy(document, new FileOutputStream(userWritePath + "Result.pdf"));
		// step 3
		document.open();
		// step 4
		PdfReader reader;
		int n;
		// loop over the documents you want to concatenate
		for (int i = 0; i < args.length; i++) {
			reader = new PdfReader(userWritePath+args[i]);

			if (reader.isEncrypted()) {
				unlockPdf(reader);
			}

			// loop over the pages in that document
			n = reader.getNumberOfPages();
			for (int page = 0; page < n;) {
				copy.addPage(copy.getImportedPage(reader, ++page));
			}
			copy.freeReader(reader);
			reader.close();
		}
		// step 5
		document.close();

		System.err.println("DONE !");
	}

	public static PdfReader unlockPdf(PdfReader reader) {
		if (reader == null) {
			return reader;
		}
		try {
			Field f = reader.getClass().getDeclaredField("encrypted");
			f.setAccessible(true);
			f.set(reader, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reader;
	}

}
