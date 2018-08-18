package com.mredrock.cyxbs.freshman.EssentialToRegister;


import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

public class EssentialDataBean  {

    /**
     * index :
     * describe : [{"id":1,"name":"","content":"","property":""},
     * {"id":1,"name":"","content":"","property":""}]
     */

    private String index;
    private List<DescribeBean> describe;



    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<DescribeBean> getDescribe() {
        return describe;
    }

    public void setDescribe(List<DescribeBean> describe) {
        this.describe = describe;
    }

    public static class DescribeBean extends LitePalSupport {
        /**
         * id : 1
         * name :
         * content :
         * property :
         */

        private int id;
        private String name;
        private String content;
        private String property;
        private boolean isChecked;
        private int posi;

        public int getPosi() {
            return posi;
        }

        public void setPosi(int posi) {
            this.posi = posi;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }
}
