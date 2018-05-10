package resource;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public abstract class AbstractResource {

    ServerBean getServerInfo(String fileName) {
        ServerBean serverIn = new ServerBean();

        Yaml yaml = new Yaml();

        URL url = this.getClass().getClassLoader().getResource(fileName);
        File file = new File(url.getPath());
        FileInputStream fis = null;
        if (!file.exists()){
            return null;
        }
        try {
            if (url == null){
                return null;
            }
            fis = new FileInputStream(url.getFile());
        } catch (FileNotFoundException e) {
            System.out.println("getServerInfo" + "  file.error");
        }
        Object object = yaml.load(fis);
        System.out.println(object);
        return serverIn;
    }

}
