package com.xm.core.vo;

import java.util.List;

public class BatchRelTasksWithMenu {

    String menuId;

    List<String> taskIds;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<String> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds(List<String> taskIds) {
        this.taskIds = taskIds;
    }
}
