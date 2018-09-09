package learndynamicprop;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyPropertyConfigurer/* extends PropertyPlaceholderConfigurer */ {

    private Resource[] locations;

    private List<File> files = new ArrayList<>(10);

    public void setLocations(Resource... locas) {
        locations = locas;
        try {
            registerFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    static {
//        try {
//            getFiles();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void registerFiles() throws IOException {
        getFiles();
        for(File file : files) {
            PropertyUtils.regisiterProp(file);
        }
    }

    private void getFiles() throws IOException {
        for(Resource resource : locations) {
            File f = resource.getFile();
            if(f.exists()) {
                files.add(f);
            }
        }
    }

    public void printFile() {
        for(File f : files) {
            System.out.println(f.getName());
        }
    }

}
