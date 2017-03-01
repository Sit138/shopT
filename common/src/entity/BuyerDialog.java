package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "BuyerDialog")
@Table(name = "buyer_dialog")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.buyer",
            joinColumns = @JoinColumn(name = "buyer_id")),
        @AssociationOverride(name = "primaryKey.dialog",
            joinColumns = @JoinColumn(name = "dialog_id"))
})
public class BuyerDialog {

    @Setter
    private BuyerDialogId primaryKey = new BuyerDialogId();

    @Column(name = "enabled")
    @Getter @Setter
    private boolean enabled;

    @Column(name = "read_all")
    @Getter @Setter
    private boolean readAll;

    @EmbeddedId
    public BuyerDialogId getPrimaryKey() {
        return primaryKey;
    }

    @Transient
    public Buyer getBuyer(){
        return getPrimaryKey().getBuyer();
    }

    public void setBuyer(Buyer buyer){
        getPrimaryKey().setBuyer(buyer);
    }

    @Transient
    public Dialog getDialog(){
        return getPrimaryKey().getDialog();
    }

    public void setDialog(Dialog dialog){
        getPrimaryKey().setDialog(dialog);
    }
}
