package Controller;

import Entity.CatalogEntity;
import Entity.ProductEntity;
import Service.CatalogService;
import Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("staff")
public class HomeController {

    @Autowired
    ProductService productService;

    @Autowired
    CatalogService catalogService;

    @GetMapping
    @Transactional
    public String index(ModelMap modelMap) {
//        if (httpSession.getAttribute("staff") != null) {
//            return
//        }
//        Session session = sessionFactory.getCurrentSession();
//        StringBuilder sql = new StringBuilder(" FROM Test");
//        List<TestEntity> list = session.createQuery(sql.toString()).getResultList();
//        for (TestEntity item : list) {
//            System.out.println(item.getContentTest()+"----"+item.getDescription());
//        }
        List<CatalogEntity> catalogEntities = catalogService.getListAll();
        modelMap.addAttribute("listCatalog",catalogEntities);
        List<ProductEntity> list = productService.getListAllAndLimit(0);

        modelMap.addAttribute("listproduct",list);

        return "index";
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {

//        ApplicationContext context = new ClassPathXmlApplicationContext("IoC.xml");
////        TestEntity test = (TestEntity) context.getBean("testEntity");
////        System.out.println(test.getTesst2().getDescription());
//
//        ResourceLoaderUtil resourceLoaderUtil = (ResourceLoaderUtil) context.getBean("ResourceLoaderUtil");
//
//        Resource resource = resourceLoaderUtil.getResource("classpath:data.txt");
//        try {
//            InputStream inputStream = resource.getInputStream();
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedInputStream = new BufferedReader(inputStreamReader);
//            String line ="";
//            StringBuilder stringBuilder = new StringBuilder();
//            while ((line = bufferedInputStream.readLine()) != null) {
//                stringBuilder.append(line+"\n");
//            }
//            bufferedInputStream.close();
//            inputStreamReader.close();
//            inputStream.close();
//            System.out.println(stringBuilder.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        return "hello";
    }
}
