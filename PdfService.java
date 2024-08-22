package com.example.Event.Management.Service;

import java.io.ByteArrayOutputStream;


import com.example.Event.Management.Entity.Event;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for generating PDF documents for events.
 */
@Service
public class PdfService {

    private static final Logger logger = LoggerFactory.getLogger(PdfService.class);

    /**
     * Creates a PDF document for the given event.
     * @param event the event for which the PDF document is to be created.
     * @return a byte array containing the PDF data.
     */
    public static byte[] createEventPdf(Event event) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            // Initialize PDF writer and document
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Add event details to the document
            document.add(new Paragraph("Event Details"));
            document.add(new Paragraph("Event ID: " + event.getId()));
            document.add(new Paragraph("Event Name: " + event.getName()));
            document.add(new Paragraph("Event Date: " + event.getDate()));
            document.add(new Paragraph("Event Location: " + event.getLocation()));
            document.add(new Paragraph("Event Description: " + event.getDescription()));

            // Close the document
            document.close();
            logger.info("PDF generated successfully for Event ID: {}", event.getId());
        } catch (Exception e) {
            logger.error("Error generating PDF for Event ID: {}", event.getId(), e);
        }

        // Return the PDF byte array
        return byteArrayOutputStream.toByteArray();
    }
}
