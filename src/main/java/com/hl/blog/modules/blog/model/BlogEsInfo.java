package com.hl.blog.modules.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Document(indexName = "blog-es-info", shards = 1, replicas = 1)
public class BlogEsInfo {

    @Id
    private Integer blogInfoId;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String summary;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Integer)
    private Integer type;

    @Field(type = FieldType.Text)
    private String typeName;

    @Field(type = FieldType.Integer)
    private Integer views;

    @Field(type = FieldType.Integer)
    private Integer tags;

    @Field(type = FieldType.Text)
    private String tagsName;

    @Field(type = FieldType.Text)
    private String comments;

    @Field(type = FieldType.Text)
    private String pictureUrl;

    @Field(type = FieldType.Boolean)
    private Boolean isRecommend;

    @Field(type = FieldType.Boolean)
    private Boolean isReprint;

    @Field(type = FieldType.Boolean)
    private Boolean isAppreciation;

    @Field(type = FieldType.Boolean)
    private Boolean isComment;

    @Field(type = FieldType.Integer)
    private Integer property;

    @Field(type = FieldType.Integer)
    private Integer state;

    @Field(type = FieldType.Integer)
    private Integer prevId;

    @Field(type = FieldType.Integer)
    private Integer nextId;

    @Field(type = FieldType.Text, index = false)
    private Date addTime;

    @Field(type = FieldType.Text, index = false)
    private Date updateTime;

    @Field(type = FieldType.Boolean)
    private Boolean deleted;

}
