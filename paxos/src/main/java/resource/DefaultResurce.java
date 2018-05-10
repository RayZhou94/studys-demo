package resource;

import java.io.File;

public class DefaultResurce extends AbstractResource {

    public static void main(String[] args) {

        DefaultResurce defaultResurce = new DefaultResurce();
        defaultResurce.getServerInfo("server1.yaml");
    }
}
