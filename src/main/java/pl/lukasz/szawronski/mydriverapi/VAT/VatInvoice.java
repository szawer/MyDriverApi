package pl.lukasz.szawronski.mydriverapi.VAT;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vat_invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VatInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String fvNumber;

    @Column(nullable = false)
    private String placeOfIssue;

    @Column(nullable = false)
    private String dateOfIssue;

    @Column(nullable = false)
    private String dateOfSale;

    @Column(nullable = false)
    private String sellersName;

    @Column(nullable = false, length = 3)
    private int sellersNip;

    @Column(nullable = false)
    private String sellersStreet;

    @Column(nullable = false)
    private int sellersZipCode;

    @Column(nullable = false)
    private String buyersName;

    @Column(nullable = false, length = 3)
    private int buyersNip;

    @Column(nullable = false)
    private String buyersStreet;

    @Column(nullable = false)
    private int buyersZipCode;

    private String invoiceDescription;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private float totalNetPrice;

    @Column(nullable = false)
    private float vatRate;

    private float totalGrossPrice;

    private String methodOfPayment;

}
