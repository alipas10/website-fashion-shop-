package Controller;

import Dao.ProductDao;
import Dto.CartDto;
import Dto.ProductDto;
import Entity.*;
import Service.CatalogService;
import Service.ProductService;
import Service.StaffService;
import Utils.CartUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("api/")
@SessionAttributes("staff")
public class ApiController {
    @Autowired
    StaffService staffService;

    @Autowired
    ProductService productService;

    @Autowired
    CatalogService catalogService;

//    @PostMapping("checkLogin")
//    @ResponseBody
//    public String checkLogin(@RequestParam String email, @RequestParam String password, ModelMap modelMap) {
//        if (email != null && password != null) {
//            StaffEntity staffEntity =staffService.checkLogin(email, password);
//            if (staffEntity != null) {
//                modelMap.addAttribute("staff",staffEntity);
//                return "index";
//            }
//            modelMap.addAttribute(WebConstant.MESSAGE_RESPONSE,"co loi ");
//
//        }
//        return "login";
//    }

    @GetMapping("addCart")
    @ResponseBody
    public void addCart(@RequestParam Integer productId, @RequestParam Integer productColorId, @RequestParam Integer productSizeId,
                        @RequestParam String productName, @RequestParam String price, @RequestParam String colorName, @RequestParam String sizeName,
                        @RequestParam Integer productDetailId, HttpSession httpSession) {

        List<CartDto> cartDtos = (List<CartDto>) httpSession.getAttribute("cart");

        if (cartDtos == null) {
            List<CartDto> dtos = new ArrayList<CartDto>();
            CartDto cartDto = addDataCartDto(productId, productColorId, productSizeId, productName, price, colorName, sizeName, productDetailId);
            dtos.add(cartDto);
            httpSession.setAttribute("cart", dtos);
        } else {
            int location = checkProductIsExistsCart(cartDtos, productId, productColorId, productSizeId);
            if (location == -1) {
                CartDto cartDto = addDataCartDto(productId, productColorId, productSizeId, productName, price, colorName, sizeName, productDetailId);
                cartDtos.add(cartDto);
            } else {
                int newDeal = cartDtos.get(location).getDeal() + 1;
                cartDtos.get(location).setDeal(newDeal);
            }
        }
    }

    private CartDto addDataCartDto(Integer productId, Integer productColorId, Integer productSizeId, String productName, String price, String colorName, String sizeName, Integer productDetailId) {
        CartDto cartDto = new CartDto();
//                CartDto.builder().productColorId(productId).
//                productColorId(productColorId).
//                productSizeId(productSizeId).
//                productName(productName).
//                price(price).
//                colorName(colorName).
//                sizeName(sizeName).
//                deal(1).
//                productDettailId(productDetailId).
//                build();
        cartDto.setProductId(productId);
        cartDto.setProductColorId(productColorId);
        cartDto.setProductSizeId(productSizeId);
        cartDto.setProductName(productName);
        cartDto.setPrice(price);
        cartDto.setColorName(colorName);
        cartDto.setSizeName(sizeName);
        cartDto.setDeal(1);
        cartDto.setProductDettailId(productDetailId);
        return cartDto;
    }

    private int checkProductIsExistsCart(List<CartDto> dtos, int productId, int colorId, int sizeId) {
        for (int i = 0; i < dtos.size(); i++) {
            if (dtos.get(i).getProductId() == productId && dtos.get(i).getProductColorId() == colorId && dtos.get(i).getProductSizeId() == sizeId) {
                return i;
            }
        }
        return -1;
    }

    @GetMapping("getProductDeal")
    @ResponseBody
    public String getProductDeal(HttpSession httpSession) {
        int result = CartUtil.getDealProductOnCart(httpSession);
        if (result == -1) {
            return "";
        } else
            return "" + result;
    }

    @GetMapping("updateCart")
    @ResponseBody
    public void updateCart(HttpSession httpSession, @RequestParam Integer productId, @RequestParam Integer colorId, @RequestParam Integer sizeId, @RequestParam Integer deal) {
        if (httpSession.getAttribute("cart") != null) {
            List<CartDto> dtos = (List<CartDto>) httpSession.getAttribute("cart");
            int result = checkProductIsExistsCart(dtos, productId, colorId, sizeId);
            if (result != -1) {
                dtos.get(result).setDeal(deal);

            }
        }
    }


    @GetMapping("deleteProductInCart")
    @ResponseBody
    public void updateCart(HttpSession httpSession, @RequestParam Integer productId, @RequestParam Integer colorId, @RequestParam Integer sizeId) {
        if (httpSession.getAttribute("cart") != null) {
            List<CartDto> dtos = (List<CartDto>) httpSession.getAttribute("cart");
            int result = checkProductIsExistsCart(dtos, productId, colorId, sizeId);
            if (result != -1) {
                dtos.remove(result);
            }
        }
    }


    @GetMapping("getListProductByPage")
    public String getListProductByPage(@RequestParam Integer productStart, ModelMap modelMap) {
        List<ProductEntity> listproduct = productService.getListAllAndLimit(productStart);
        List<ProductEntity> productEntityListAll = productService.getListAllAndLimit(-1);
        double totalPage = Math.ceil((double) productEntityListAll.size() / 2);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("listproduct", listproduct);

        List<CatalogEntity> catalogEntities = catalogService.getListAll();
        modelMap.addAttribute("listCatalog", catalogEntities);

        return "addproduct";
    }


    @GetMapping("deleteProduct")
    @ResponseBody
    public String deleteProduct(@RequestParam Integer productId) {
        productService.deleteProductById(productId);
        return "true";
    }

    @Autowired
    ServletContext context;

    @PostMapping("uploadFiles")
    @ResponseBody
    public String uploadFiles(MultipartHttpServletRequest request) {
        String pathRealFile = context.getRealPath("/resource/template/image/");
        Iterator<String> listNames = request.getFileNames();
        MultipartFile mpf = request.getFile(listNames.next());
        File file = new File(pathRealFile + mpf.getOriginalFilename());
        try {
            mpf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }


    @PostMapping("apiAddProduct")
    @ResponseBody
    public void apiAddProduct(@RequestParam String dataJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObject;
        try {

            jsonObject = objectMapper.readTree(dataJson);

            CatalogEntity catalogEntity = new CatalogEntity();
            catalogEntity.setCatalogId(jsonObject.get("catalog").asInt());

            JsonNode jsonDetails = jsonObject.get("productDetails");
            Set<ProductDetailEntity> productDetailEntities = new HashSet<ProductDetailEntity>();
            for (JsonNode jsonDetail : jsonDetails
                 ) {
                ProductColorEntity productColorEntity = new ProductColorEntity();
                productColorEntity.setProductColorId(jsonDetail.get("productColor").asInt());

                ProductSizeEntity productSizeEntity = new ProductSizeEntity() ;
                productSizeEntity.setProductSizeId(jsonDetail.get("productSize").asInt());

                ProductDetailEntity productDetailEntity =  new ProductDetailEntity();
//                        ProductDetailEntity.builder().
//                        productColorEntity(productColorEntity).
//                        productSizeEntity(productSizeEntity).
//                        deal(jsonDetail.get("dealProduct").asInt()).
//                        build();
                productDetailEntity.setProductColorEntity(productColorEntity);
                productDetailEntity.setProductSizeEntity(productSizeEntity);
                productDetailEntity.setDeal(jsonDetail.get("dealProduct").asInt());

                productDetailEntities.add(productDetailEntity);
            }
            String productName = jsonObject.get("productName").asText();
            String price = jsonObject.get("productPrice").asText();
            String decription = jsonObject.get("productDecription").asText();
            String productImage = jsonObject.get("imageName").asText();
            String reserve = jsonObject.get("optioneRadio").asText();


            ProductEntity productEntity = new ProductEntity();

//                    ProductEntity.builder().
//                    productDetailEntities(productDetailEntities).
//                    catalogEntity(catalogEntity).
//                    productName(productName).
//                    price(price).
//                    productDescription(decription).
//                    productImage(productImage).
//                    reserve(reserve).
//                    build();
            productEntity.setProductDetailEntities(productDetailEntities);
            productEntity.setCatalogEntity(catalogEntity);
            productEntity.setProductName(productName);
            productEntity.setPrice(price);
            productEntity.setProductDescription(decription);
            productEntity.setProductImage(productImage);
            productEntity.setReserve(reserve);


            productService.addProduct(productEntity);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping(path = "getProductById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ProductDto getProductById(@RequestParam int productId){
        ProductEntity productEntity = productService.getListById(productId);

        CatalogEntity catalogEntity = new CatalogEntity();
        catalogEntity.setCatalogId(productEntity.getCatalogEntity().getCatalogId());

        Set<ProductDetailEntity> productDetailEntities = new HashSet<ProductDetailEntity>();
        for (ProductDetailEntity item : productEntity.getProductDetailEntities()
             ) {

            ProductColorEntity productColorEntity = new ProductColorEntity();
            productColorEntity.setProductColorId(item.getProductColorEntity().getProductColorId());
            productColorEntity.setProductColorName(item.getProductColorEntity().getProductColorName());

            ProductSizeEntity productSizeEntity = new ProductSizeEntity();
            productSizeEntity.setProductSizeId(item.getProductSizeEntity().getProductSizeId());
            productSizeEntity.setProductSizeName(item.getProductSizeEntity().getProductSizeName());

            ProductDetailEntity productDetailEntity =  new ProductDetailEntity();
//
//                    ProductDetailEntity.builder().
//                    productDetailId(item.getProductDetailId()).
//                    deal(item.getDeal()).
//                    productSizeEntity(item.getProductSizeEntity()).
//                    productColorEntity(item.getProductColorEntity()).
//                    build();

            productDetailEntity.setProductDetailId(item.getProductDetailId());
            productDetailEntity.setDeal(item.getDeal());
            productDetailEntity.setProductSizeEntity(item.getProductSizeEntity());
            productDetailEntity.setProductColorEntity(item.getProductColorEntity());


            productDetailEntities.add(productDetailEntity);
        }

        ProductDto productDto = new ProductDto();
//        ProductDto.builder().
//                productId(productEntity.getProductId()).
//                price(productEntity.getPrice()).
//                productDescription(productEntity.getProductDescription()).
//                productImage(productEntity.getProductImage()).
//                reserve(productEntity.getReserve()).
//        .build();
        productDto.setProductId(productEntity.getProductId());
        productDto.setPrice(productEntity.getPrice());
        productDto.setProductDescription(productEntity.getProductDescription());
        productDto.setProductImage(productEntity.getProductImage());
        productDto.setReserve(productEntity.getReserve());
        productDto.setProductName(productEntity.getProductName());
        productDto.setCatalogEntity(catalogEntity);
        productDto.setProductDetailEntities(productDetailEntities);



        return productDto;
    }


    @PostMapping("apiupdateProduct")
    @ResponseBody
    public void apiupdateProduct(@RequestParam String dataJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObject;
        try {
            ProductEntity productEntity = new ProductEntity();
            jsonObject = objectMapper.readTree(dataJson);

            CatalogEntity catalogEntity = new CatalogEntity();
            catalogEntity.setCatalogId(jsonObject.get("catalog").asInt());

            JsonNode jsonDetails = jsonObject.get("productDetails");
            Set<ProductDetailEntity> productDetailEntities = new HashSet<ProductDetailEntity>();
            for (JsonNode jsonDetail : jsonDetails
            ) {
                ProductDetailEntity productDetailEntity  = new ProductDetailEntity();

                ProductColorEntity productColorEntity = new ProductColorEntity();
                productColorEntity.setProductColorId(jsonDetail.get("productColor").asInt());

                ProductSizeEntity productSizeEntity = new ProductSizeEntity() ;
                productSizeEntity.setProductSizeId(jsonDetail.get("productSize").asInt());

                productDetailEntity.setProductColorEntity(productColorEntity);
                productDetailEntity.setProductSizeEntity(productSizeEntity);
                productDetailEntity.setDeal(jsonDetail.get("dealProduct").asInt());

                productDetailEntities.add(productDetailEntity);
            }

            String productName = jsonObject.get("productName").asText();
            String price = jsonObject.get("productPrice").asText();
            String decription = jsonObject.get("productDecription").asText();
            String productImage = jsonObject.get("imageName").asText();
            String reserve = jsonObject.get("optioneRadio").asText();
            Integer productId = jsonObject.get("productId").asInt();

            productEntity.setProductDetailEntities(productDetailEntities);
            productEntity.setCatalogEntity(catalogEntity);
            productEntity.setProductName(productName);
            productEntity.setPrice(price);
            productEntity.setProductDescription(decription);
            productEntity.setProductImage(productImage);
            productEntity.setReserve(reserve);
            productEntity.setProductId(productId);

            productService.updateProduct(productEntity);


        } catch (IOException e) {
            e.printStackTrace();
        }




    }




}
