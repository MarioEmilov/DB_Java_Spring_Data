package _05.entities;

import javax.persistence.*;

@Entity
@Table(name = "_05_billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BillingDetail extends BaseEntity {

    private String number;
    private BankUser owner;

    public BillingDetail() {
    }

    @Column(nullable = false, unique = true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    public BankUser getOwner() {
        return owner;
    }

    public void setOwner(BankUser owner) {
        this.owner = owner;
    }
}
