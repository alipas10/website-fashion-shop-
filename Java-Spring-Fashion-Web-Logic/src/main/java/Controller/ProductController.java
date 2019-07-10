package Controller;

import Entity.CatalogEntity;
import Entity.ProductEntity;
import Service.CatalogService;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    CatalogService catalogService;

    @Autowired
    ProductService productService;

    @GetMapping("/{id}/{catalogName}")
    public String Default (@PathVariable Integer id,@PathVariable String catalogName, ModelMap modelMap) {
        List<CatalogEntity> catalogEntities = catalogService.getListAll();
        List<ProductEntity> productEntities = productService.getListProductById(id);
        modelMap.addAttribute("listProductByCatalogId",productEntities);
        modelMap.addAttribute("listCatalog",catalogEntities);
        modelMap.addAttribute("catalogName",catalogName);

        return "product";
    }
}
