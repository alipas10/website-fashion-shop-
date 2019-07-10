package Controller;

import Dto.CartDto;
import Entity.BillDetailEntity;
import Entity.BillDetailId;
import Entity.BillEntity;
import Service.BillDetailService;
import Service.BillService;
import Utils.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    BillService billService;

    @Autowired
    BillDetailService billDetailService;

    @GetMapping
    public String Default(HttpSession httpSession , ModelMap modelMap) {
        List<CartDto> cartDtos = (List<CartDto>) httpSession.getAttribute("cart");
        if ( cartDtos != null) {
            int result =CartUtil.getDealProductOnCart(httpSession);
            modelMap.addAttribute("cart",cartDtos);
            if (result != -1) {
                modelMap.addAttribute("dealProductOnCart", result);
            }
        }
        return "cart";
    }

    @PostMapping
    public String addBill(@RequestParam String customerName,@RequestParam String phoneNumber, @RequestParam String deliveryAddress,
                          @RequestParam String typeReceiving,
                          @RequestParam String note,
                          HttpSession httpSession
                          ) {

        List<CartDto> cartDtos = (List<CartDto>) httpSession.getAttribute("cart");
        if (cartDtos != null) {
            BillEntity billEntity = new BillEntity();
            billEntity.setCustomerName(customerName);
            billEntity.setPhoneNumber(phoneNumber);
            billEntity.setDeliveryAddress(deliveryAddress);
            billEntity.setTypeReceiving(typeReceiving);
            billEntity.setNote(note);

            Integer billId  = billService.addCart(billEntity);


            System.out.println(billId);
            if (billId != null) {
                for (CartDto cartDto : cartDtos) {
                    BillDetailId billDetailId  = new BillDetailId();
                    billDetailId.setProductDetailId(cartDto.getProductDettailId());
                    billDetailId.setBillId(billEntity.getBillId());

                    BillDetailEntity billDetailEntity = new BillDetailEntity();
                    billDetailEntity.setBillDetailId(billDetailId);
                    billDetailEntity.setTotalPrice(cartDto.getPrice());
                    billDetailEntity.setDeal(cartDto.getDeal());

                    billDetailService.addBillDetaild(billDetailEntity);
                }
                System.out.println("ssss");
            }
            else {
                System.out.println("ffff");
            }

        }

        return "cart";

    }

}
