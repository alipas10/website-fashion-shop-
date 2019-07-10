package Utils;

import Dto.CartDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartUtil {

    public static Integer getDealProductOnCart(HttpSession httpSession) {
        if (httpSession.getAttribute("cart") != null) {
            List<CartDto> dtos = (List<CartDto>) httpSession.getAttribute("cart");
            int sl = 0;
            for (int i = 0; i < dtos.size(); i++) {
                sl += dtos.get(i).getDeal();
            }
            return sl;
        }
        return -1;
    }


}
