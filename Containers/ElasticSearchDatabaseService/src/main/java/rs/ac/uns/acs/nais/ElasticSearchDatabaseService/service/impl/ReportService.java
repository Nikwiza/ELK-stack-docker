package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {
    private final RequestService requestService;
    private final TransactionService transactionService;

    public ReportService(RequestService requestService, TransactionService transactionService) {
        this.requestService = requestService;
        this.transactionService = transactionService;
    }


    public byte[] export_report() throws IOException {
        List<Transactions> transactions_notApproved = transactionService.findLesserThan(10);
        Iterable<Requests> requests_notApproved = requestService.notApprovedRequests();
        List<Transactions> urgent_transactions = transactionService.searchByCommentOrCompanyNameFuzzy("important");
        List<Transactions> mainClientTransactions = transactionService.findByCompanyNameAndCommentNotAndOptional("Khan-Rojas", "casual", "official");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("TRANSACTIONS ABOVE LIMIT TO BE APPROVED", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("TransactionId", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Company", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("UserId", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);

        for (Transactions transaction : transactions_notApproved) {
            reportTable.addCell(transaction.getTransaction_id());
            reportTable.addCell(transaction.getCompany_name());
            reportTable.addCell(transaction.getUser_id());
        }

        document.add(reportTable);


        title = new Paragraph("REQUESTS TO BE APPROVED", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        PdfPTable requestReportTable = new PdfPTable(3);

        PdfPCell headerReq1 = new PdfPCell(new Paragraph("RequestId", headerFont));
        PdfPCell headerReq2 = new PdfPCell(new Paragraph("UserId", headerFont));
        PdfPCell headerReq3 = new PdfPCell(new Paragraph("Likes", headerFont));

        headerReq1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerReq2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerReq3.setBackgroundColor(new Color(110, 231, 234, 255));

        requestReportTable.addCell(headerReq1);
        requestReportTable.addCell(headerReq2);
        requestReportTable.addCell(headerReq3);

        for (Requests request : requests_notApproved) {
            requestReportTable.addCell(request.getRequest_id());
            requestReportTable.addCell(request.getUser_id());
            requestReportTable.addCell(request.getLikes().toString());
        }

        document.add(requestReportTable);


        title = new Paragraph("URGENTE TRANSACTIONS", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable urgentTransactions = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        PdfPCell urgentCell1 = new PdfPCell(new Paragraph("TransactionId", headerFont));
        PdfPCell urgentCell2 = new PdfPCell(new Paragraph("Company", headerFont));
        PdfPCell urgentCell3 = new PdfPCell(new Paragraph("UserId", headerFont));

        urgentCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        urgentCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        urgentCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        urgentTransactions.addCell(urgentCell1);
        urgentTransactions.addCell(urgentCell2);
        urgentTransactions.addCell(urgentCell3);

        for (Transactions transaction : urgent_transactions) {
            urgentTransactions.addCell(transaction.getTransaction_id());
            urgentTransactions.addCell(transaction.getCompany_name());
            urgentTransactions.addCell(transaction.getUser_id());
        }

        document.add(urgentTransactions);

        title = new Paragraph("MAIN CLIENT TRANSACTIONS", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable mainClientTransactionsTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        PdfPCell mainCli1 = new PdfPCell(new Paragraph("TransactionId", headerFont));
        PdfPCell mainCli2 = new PdfPCell(new Paragraph("Company", headerFont));
        PdfPCell mainCli3 = new PdfPCell(new Paragraph("UserId", headerFont));

        mainCli1.setBackgroundColor(new Color(110, 231, 234, 255));
        mainCli2.setBackgroundColor(new Color(110, 231, 234, 255));
        mainCli3.setBackgroundColor(new Color(110, 231, 234, 255));

        mainClientTransactionsTable.addCell(urgentCell1);
        mainClientTransactionsTable.addCell(urgentCell2);
        mainClientTransactionsTable.addCell(urgentCell3);

        for (Transactions transaction : mainClientTransactions) {
            mainClientTransactionsTable.addCell(transaction.getTransaction_id());
            mainClientTransactionsTable.addCell(transaction.getCompany_name());
            mainClientTransactionsTable.addCell(transaction.getUser_id());
        }

        document.add(mainClientTransactionsTable);

        document.close();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }

}
