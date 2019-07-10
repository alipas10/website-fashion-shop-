package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "BILLDETAIL")
public class BillDetailEntity  {
    @EmbeddedId
    BillDetailId billDetailId;

    Integer deal;
    String totalPrice;

}
