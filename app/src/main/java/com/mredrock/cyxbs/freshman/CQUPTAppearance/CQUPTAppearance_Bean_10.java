package com.mredrock.cyxbs.freshman.CQUPTAppearance;

import java.util.List;

/**
 * Created by 郝书逸 on 2018/8/15.
 */

public class CQUPTAppearance_Bean_10 {
    /**
     * array : [{"content":"校运动会于每年的四月份举办，通常会由每个学院轮流主持策划。这其中不仅包括体育运动项目之间的竞争，还能欣赏到由各个学院啦啦队，开幕式方阵等团体带来的极致演出。作为一个全校共庆的体育盛事，每个学院都会提前进行加油口号的排列，于竞技时加油助威，喇叭声鸣，彩旗挥舞，好不壮观。","id":1,"name":"校运动会","picture":["/picture/d76370f540e04df78e3214cb97019d0a.jpeg","/picture/dbd8cb64b15645c09b8deb1ae1b2150b.jpeg","/picture/eb2273b00f40438ca9a7512a705110c5.jpeg"],"property":""},{"content":"拉拉队大比拼是一项与校运会同期开展、由红岩网校工作站策划和举办的活动。举办方式通常是由线上和线下结合，并且每年会根据学院具体状况制定出不同的投票模式。由各个学院推选出来的拉拉队通过照片/训练视频/运动会现场表演的方式进行拉票，全校学生均可参与投票，最终的胜利队伍将会获得丰富奖品。","id":2,"name":"啦啦队比拼","picture":["/picture/c26c3b33d5c44d9fa251b11d14f53b5f.jpeg","/picture/4001128c14c54783b736d2bdf3c46c16.jpeg","/picture/0f7119094eee4a2189cec41872b7cdd1.jpeg"],"property":""},{"content":"草坪音乐会是由重庆邮电大学学生会举办的一项文艺活动，举办频次不定，主题丰富多样，譬如摇滚专场，毕业季等等，举办地点位于春华秋实广场之上，因其颇高的演出质量和感染力极强的现场表演，为同学们所深深喜爱。","id":3,"name":"草坪音乐会","picture":["/picture/806ad129f8af4035b4fdead839fd18c0.jpg","/picture/a1d910c7e651474bb8ed2c26ab6f3c41.jpg","/picture/c24fae28c0c64725bfcfdc969cbbcbd5.jpg","/picture/a8a391e1d6ea478e92ab5a5e093b782c.jpg"],"property":""},{"content":"\u201c校园十大歌手赛\u201d是由重庆邮电大学学生会承办，自1993年起举办的一项传统文娱活动，举办频率为两年一届，自开展以来一直以来深受广大师生欢迎，为广大学生展示青春才华，增进沟通交流搭建了平台。每届十大歌手举办时，不论从最初的线下海选还是到最终的决赛汇演，都为同学们所津津乐道，大家饱满的参与积极度为我们带来了一届又一届令人赞叹的歌声。","id":4,"name":"十大歌手","picture":["/picture/9260857053f74ca18bfcd57b9a47609e.jpg","/picture/130c1e4896394b18987d7c46941827cf.jpg","/picture/a9897eb1bbe54db4b033181ffe4517ef.jpg","/picture/973e4cd947bc4bc4b2d5811811b1c662.jpg"],"property":""}]
     * index : 大型活动
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
         * content : 校运动会于每年的四月份举办，通常会由每个学院轮流主持策划。这其中不仅包括体育运动项目之间的竞争，还能欣赏到由各个学院啦啦队，开幕式方阵等团体带来的极致演出。作为一个全校共庆的体育盛事，每个学院都会提前进行加油口号的排列，于竞技时加油助威，喇叭声鸣，彩旗挥舞，好不壮观。
         * id : 1
         * name : 校运动会
         * picture : ["/picture/d76370f540e04df78e3214cb97019d0a.jpeg","/picture/dbd8cb64b15645c09b8deb1ae1b2150b.jpeg","/picture/eb2273b00f40438ca9a7512a705110c5.jpeg"]
         * property :
         */

        private String content;
        private int id;
        private String name;
        private String property;
        private List<String> picture;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public List<String> getPicture() {
            return picture;
        }

        public void setPicture(List<String> picture) {
            this.picture = picture;
        }
    }
}
