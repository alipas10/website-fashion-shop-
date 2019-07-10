package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class BillDetailId implements Serializable {
    Integer billId;
    Integer productDetailId;


}
