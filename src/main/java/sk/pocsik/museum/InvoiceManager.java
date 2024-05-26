package sk.pocsik.museum;

import java.util.ArrayList;

public class InvoiceManager {
    private final ArrayList<Invoice> invoices = new ArrayList<>();

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }
}
