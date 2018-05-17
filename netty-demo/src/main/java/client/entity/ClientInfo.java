package client.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientInfo implements Serializable {

    private Integer port;
}
