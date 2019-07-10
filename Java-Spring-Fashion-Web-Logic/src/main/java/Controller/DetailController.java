package Controller;

import Dto.CartDto;
import Entity.CatalogEntity;
import Entity.ProductEntity;
import Service.CatalogService;
import Service.ProductService;
import Utils.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("detail")
@SessionAttributes("cart")
public class DetailController {
    @Autowired
    ProductService productService;
    @Autowired
    CatalogService catalogService;

    @GetMapping("/{productId}")
    public String Default(@PathVariable Integer productId, ModelMap modelMap, HttpSession httpSession){
        ProductEntity productEntities = productService.getListById(productId);
        List<CatalogEntity> catalogEntities = catalogService.getListAll();
        modelMap.addAttribute("productById", productEntities);
        modelMap.addAttribute("listCatalog",catalogEntities);
        int result = CartUtil.getDealProductOnCart(httpSession);
        if (result != -1) {
            modelMap.addAttribute("dealProductOnCart",result);
        }
        return "detail";
    }
}
