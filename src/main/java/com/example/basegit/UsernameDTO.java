package com.example.basegit;

import java.util.Objects;

public class UsernameDTO {
    private Integer id;
    private String type;

    public UsernameDTO() {
    }

    public UsernameDTO(Integer id, String type)  {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsernameDTO)) return false;
        UsernameDTO that = (UsernameDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getType(), that.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType());
    }
}
