package co.wgmartinez.soap.service;

import java.util.ArrayList;
import java.util.List;

import co.wgmartinez.soap.entity.Invoice;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {


	@Override
	public List<Invoice> getAllInvoices() {
		List<Invoice> list = new ArrayList<>();
		return list;
	}

	@Override
	public Invoice getInvoiceById(String invoiceId) {
		Invoice obj = new Invoice();
		return obj;
	}

	@Override
	public boolean addInvoice(Invoice invoice) {
		return true;
	}

	@Override
	public void updateInvoice(Invoice invoice) {

	}
}
