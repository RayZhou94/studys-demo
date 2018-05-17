package client.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private String message;

    private Long messageId;

    private ClientInfo clientInfo;

}
