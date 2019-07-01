package com.spring.aop;

/**
 * @author brandon
 * create on 2019-06-25
 * desc:
 */
public class Hourse implements Construction {

    private boolean permitted = false;

    @Override
    public void construct() {
        System.out.println("Hourse.construct");
    }

    @Override
    public void givePermission() {
        System.out.println("Hourse.givePermission");
        this.permitted = true;
    }

    @Override
    public boolean isPermitted() {
        return this.permitted;
    }
}
