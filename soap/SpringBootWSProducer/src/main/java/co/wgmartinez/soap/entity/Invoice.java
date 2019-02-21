package co.wgmartinez.soap.entity;

import co.wgmartinez.wsdl.ServiceStatus;

import java.io.Serializable;
import java.math.BigDecimal;

public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

    private String invoiceReference;

	private String customerReference;

	private String orderReference;

	private BigDecimal amount;

	private ServiceStatus status;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getInvoiceReference() {
		return invoiceReference;
	}

	public void setInvoiceReference(String invoiceReference) {
		this.invoiceReference = invoiceReference;
	}

	public String getCustomerReference() {
		return customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public String getOrderReference() {
		return orderReference;
	}

	public void setOrderReference(String orderReference) {
		this.orderReference = orderReference;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public ServiceStatus getStatus() {
		return status;
	}

	public void setStatus(ServiceStatus status) {
		this.status = status;
	}
}