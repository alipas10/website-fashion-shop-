package Service;

import Dao.CatalogDao;
import Entity.CatalogEntity;
import Interface.CatalogInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService implements CatalogInterface {

    @Autowired
    CatalogDao catalogDao;

    public List<CatalogEntity> getListAll() {

        return catalogDao.getListAll();
    }
}
