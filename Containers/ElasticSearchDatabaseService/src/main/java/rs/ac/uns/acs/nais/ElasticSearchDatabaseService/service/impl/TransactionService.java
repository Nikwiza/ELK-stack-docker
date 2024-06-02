package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Product;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transaction;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.ProductRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TransactionRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IProductService;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITransactionService;

import java.util.List;


@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> findByNameOrDescription(String name, String description) {
        return transactionRepository.findByNameOrDescription(name, description);
    }

//    public List<Product> findByNameContainingOrDescriptionContaining(String name, String description) {
//        return productRepository.findByNameContainingOrDescriptionContaining(name, description);
//    }
//
//    public List<Product> findByCustomQuery(String query) {
//        return productRepository.findByCustomQuery(query);
//    }
//
//    public List<Product> searchByDescriptionPhrase(String phrase) {
//        return productRepository.searchByDescriptionPhrase(phrase);
//    }
//
//    public List<Product> searchByNameOrDescriptionFuzzy(String searchTerm) {
//        return productRepository.searchByNameOrDescriptionFuzzy(searchTerm);
//    }
//
//    public List<Product> findByNameAndDescriptionNotAndOptional(String name, String mustNotTerms, String shouldTerms) {
//        return productRepository.findByNameAndDescriptionNotAndOptional(name, mustNotTerms, shouldTerms);
//    }
//
//
//    public List<Product> findByFunctionScore(String searchTerm, String boostTerms) {
//        return productRepository.findByFunctionScore(searchTerm, boostTerms);
//    }
/* 
    public byte[] export(List<Product> products) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        String filename = "podaci/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);

        Paragraph title = new Paragraph("PRODUCTS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable reportTable = new PdfPTable(3);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Product", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Description", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("ID", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);

        for (Product product : products) {
            reportTable.addCell(product.getName());
            reportTable.addCell(product.getDescription());
            reportTable.addCell(product.getId());
        }

        document.add(reportTable);
        document.close();

        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            byteArrayOutputStream.writeTo(fileOutputStream);
        }

        return byteArrayOutputStream.toByteArray();
    }
    */
}