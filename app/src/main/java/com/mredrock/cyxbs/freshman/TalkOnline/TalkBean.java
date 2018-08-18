package com.mredrock.cyxbs.freshman.TalkOnline;

import java.util.List;

public class TalkBean {

    /**
     * array : [{"name":"湖南","array1":[{"code":"204491110","name":"湖南老乡群"}]}]
     * index : 老乡群
     */

    private String index;
    private List<ArrayBean> array;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    public static class ArrayBean {
        /**
         * name : 湖南
         * array1 : [{"code":"204491110","name":"湖南老乡群"}]
         */

        private String name;
        private List<Array1Bean> array1;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Array1Bean> getArray1() {
            return array1;
        }

        public void setArray1(List<Array1Bean> array1) {
            this.array1 = array1;
        }

        public static class Array1Bean {
            /**
             * code : 204491110
             * name : 湖南老乡群
             */

            private String code;
            private String name;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
