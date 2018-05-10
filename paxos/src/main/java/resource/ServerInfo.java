package resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = -761707165862837883L;

    private String serverId;

    private String host;

    private String port;

}
