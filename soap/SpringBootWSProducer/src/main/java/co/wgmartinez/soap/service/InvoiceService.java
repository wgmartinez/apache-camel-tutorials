package co.wgmartinez.soap.service;

import java.util.List;

import co.wgmartinez.soap.entity.Invoice;

public interface InvoiceService {
     List<Invoice> getAllInvoices();
     Invoice getInvoiceById(String invoiceId);
     boolean addInvoice(Invoice invoice);
     void updateInvoice(Invoice invoice);
}
