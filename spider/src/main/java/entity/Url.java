package entity;

import lombok.Data;

@Data
public class Url {

    private Long id;

    private String url;

    private String text;

    /**
     * 路径深度
     */
    private Integer height;

    /**
     * 描述
     */
    private String description;
}
