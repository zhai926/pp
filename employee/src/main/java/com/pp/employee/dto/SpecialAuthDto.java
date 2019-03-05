package com.pp.employee.dto;

import java.io.Serializable;

public class SpecialAuthDto implements Serializable {

    private static final long serialVersionUID = -9072896274036182671L;
    /**
     * 查看所有预约订单
     */
    private boolean preOrderAll;

    private boolean menuAll;

    public boolean getPreOrderAll() {
        return preOrderAll;
    }

    public void setPreOrderAll(boolean preOrderAll) {
        this.preOrderAll = preOrderAll;
    }

    public boolean isMenuAll() {
        return menuAll;
    }

    public void setMenuAll(boolean menuAll) {
        this.menuAll = menuAll;
    }
}
