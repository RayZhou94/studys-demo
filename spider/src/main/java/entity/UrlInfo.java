package entity;

import lombok.Data;

@Data
public class UrlInfo {

    private Long id;

    private Long pid;

    private String url;

    private String text;

    private Boolean isImg;

    /**
     * 路径深度
     */
    private Integer height;

    /**
     * 描述
     */
    private String description;
}
