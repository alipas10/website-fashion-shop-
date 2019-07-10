package Controller;


import Dao.ProductColorDao;
import Entity.CatalogEntity;
import Entity.ProductColorEntity;
import Entity.ProductEntity;
import Entity.ProductSizeEntity;
import Service.CatalogService;
import Service.ProductColorService;
import Service.ProductService;
import Service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "themsanpham")
public class ProductAdminController {

    @Autowired
    ProductService productService;

    @Autowired
    CatalogService catalogService;

    @Autowired
    ProductSizeService productSizeService;

    @Autowired
    ProductColorService productColorService;

    @GetMapping
    public String Default(ModelMap modelMap) {
        List<ProductEntity> productEntities = productService.getListAllAndLimit(0);
        List<ProductEntity> productEntityListAll = productService.getListAllAndLimit(-1);

        double totalPage = Math.ceil((double)productEntityListAll.size()/2);
        modelMap.addAttribute("listproduct",productEntities);
        modelMap.addAttribute("totalPage",totalPage);



        List<CatalogEntity> catalogEntities = catalogService.getListAll();
        modelMap.addAttribute("listCatalog",catalogEntities);

        List<ProductSizeEntity> productSizeEntities = productSizeService.getListAll();
        modelMap.addAttribute("listProductSize",productSizeEntities);

        List<ProductColorEntity> productColorEntities = productColorService.getListAll();
        modelMap.addAttribute("listProductColor",productColorEntities);

        return "addproduct";
    }
}
