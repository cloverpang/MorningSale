package com.cloversystem.domain;

import java.io.Serializable;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cpang
 * Date: 04/10/2013
 * Time: 7:53:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class Role implements Serializable {

    private String name;

    private String desciption;

    public Role() {
    }

    public Role(String name, String desciption) {
        this.name = name;
        this.desciption = desciption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}
