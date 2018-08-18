package com.mredrock.cyxbs.freshman.ArmyTraining;

import java.util.List;

/**
 * Created by 郝书逸 on 2018/8/12.
 */

public class ArmyTraining_Bean_Tip {

    /**
     * index : 军训小贴士
     * describe : [{"content":"一．       军队纪律","id":1,"name":"军训小贴士","property":"加粗"},{"content":"1.军训期间请假遵循一事一请假的原则，无特殊事由不得请假；\n2.学生在军训场上感到不适，可直接通过\u201c打报告\u201d的形式向教官请假，在训练场边暂时休息；\n3.请假半天以下的由军训排排长经商辅导员同意后批准；\n4.请假半天以上，3天以下的，由学生本人提出书面申请（请假条见附页），经辅导员签字后报校武装部审批，并由学生本人将审批后的申请交军训排排长实施；\n5.请假3天以上的需报请军训师领导同意；\n6.请假须外出的，须详细说明原因，并定期向辅导员报告动态；\n7.请假时间累计原则上不得超过军训时间的1/3（即5天）；\n8.如有其他特殊原因请假超过5天的，须专门报请军训师批准。","id":2,"name":"军训小贴士","property":"不加粗"},{"content":"二． 军训贴士","id":3,"name":"军训小贴士","property":"加粗"},{"content":"1.早饭建议多多补充蛋白质和维生素，水果尽量选用能提高防晒能力的，譬如番茄，西瓜，蓝莓柑橘等VC含量高的，避免芹菜，柠檬，白萝卜这种感光的蔬菜。\n2.军训基本处于脱水状态，建议补充些盐水平衡体内电解质避免皮肤抵抗力的下降。军训期间提供接水处，学生可自带水杯。\n3.夜间训练建议携带驱蚊水，可有效减少蚊子的叮咬。\n4.倘若皮肤晒伤，要加以保护措施避免反复晒伤，晒伤的地方进行冷敷处理或者浸泡在凉水里。可在晒伤处涂抹保湿乳液，不要使用霜，软膏等质地过于浓稠的护肤品，切记不要挤破水泡。\n5.军姿站立时间过久脚肿是由于静脉回流不通畅导致的，建议军训休息时间多多活动双脚，晚上按摩，泡脚时冷热水交替，睡觉的时候垫高腿。","id":4,"name":"军训小贴士","property":"不加粗"}]
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

    public static class DescribeBean {
        /**
         * content : 一．       军队纪律
         * id : 1
         * name : 军训小贴士
         * property : 加粗
         */

        private String content;
        private int id;
        private String name;
        private String property;

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
    }
}
