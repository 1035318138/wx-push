package com.nkznb.wx.wxmessagepush.application;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * @author Can.Ru
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false) //NOPMD
public class RuleConditionDTO {
    public static String service ="wechatController";



    private String ruleCode;





    private List<Condition> conditionList;
    @Builder
    @Data
    public static class Condition{

        //默认false(符合) true为取反(不符合)
        private Boolean negation = false;

        private String method;

        private HashMap<String, Object> params;

    }
}
