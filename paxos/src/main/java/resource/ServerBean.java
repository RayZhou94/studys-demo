package resource;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ServerBean implements Serializable {

    private static final long serialVersionUID = -8718346084467040812L;

    private String myId;

    private String logFile;

    private List<ServerInfo> serverInfos;
}
