package com.zot.autorun.moudules.apitest.dto.asserts;

public class AssertBase {

    private String path;
    private String relation;
    private Object value;

    @Override
    public String toString() {
        return "AssertBase{" +
                "path='" + path + '\'' +
                ", relation='" + relation + '\'' +
                ", value=" + value +
                '}';
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
