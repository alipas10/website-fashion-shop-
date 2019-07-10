package Utils;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderUtil implements ResourceLoaderAware {
    private static ResourceLoader resourceLoader;
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Resource getResource(String path) {
        return resourceLoader.getResource(path);
    }
}
