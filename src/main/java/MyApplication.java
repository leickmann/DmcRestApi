
import services.LabelGeneratorRessource;
import services.ProductResource;
import services.SupplierResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> h = new HashSet<>();
        h.add( HelloWorld.class );
        h.add(SupplierResource.class);
        h.add(ProductResource.class);
        h.add(LabelGeneratorRessource.class);
        return h;
    }
}