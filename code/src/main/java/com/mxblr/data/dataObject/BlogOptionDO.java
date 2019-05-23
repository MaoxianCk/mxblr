package com.mxblr.data.dataObject;

public class BlogOptionDO {
    private Integer optionId;

    private String optionKey;

    private String optionVal;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey == null ? null : optionKey.trim();
    }

    public String getOptionVal() {
        return optionVal;
    }

    public void setOptionVal(String optionVal) {
        this.optionVal = optionVal == null ? null : optionVal.trim();
    }
}