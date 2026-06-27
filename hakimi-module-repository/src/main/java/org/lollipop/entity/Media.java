package org.lollipop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lollipop
 * @since 2026-06-28
 */
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件大小(MB)
     */
    private Double fileSize;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public Media setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public Media setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Integer getFileType() {
        return fileType;
    }

    public Media setFileType(Integer fileType) {
        this.fileType = fileType;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public Media setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public Media setFileSize(Double fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Media setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Media setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public Media setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id = " + id +
                ", fileName = " + fileName +
                ", fileType = " + fileType +
                ", filePath = " + filePath +
                ", fileSize = " + fileSize +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", isDeleted = " + isDeleted +
                "}";
    }
}
