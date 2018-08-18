package com.mredrock.cyxbs.freshman.CampusStrategy.DataReveal.DiffcultColumn;

import java.util.List;

public class DiffColumnBean {

    /**
     * array : [{"below_amount":138,"id":1,"subject_name":"大学物理B"},{"below_amount":65,"id":2,"subject_name":"高等数学A"},{"below_amount":31,"id":3,"subject_name":"工程数学基础 (2)"}]
     * name : 自动化学院
     */

    private String name;
    private List<ArrayBean> array;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        /**
         * below_amount : 138
         * id : 1
         * subject_name : 大学物理B
         */

        private int below_amount;
        private int id;
        private String subject_name;

        public int getBelow_amount() {
            return below_amount;
        }

        public void setBelow_amount(int below_amount) {
            this.below_amount = below_amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject_name() {
            return subject_name;
        }

        public void setSubject_name(String subject_name) {
            this.subject_name = subject_name;
        }
    }
}
