package com.nkznb.wx.wxmessagepush;

import com.nkznb.wx.wxmessagepush.application.ApplicationContextUtil;
import com.nkznb.wx.wxmessagepush.application.RuleConditionDTO;
import com.nkznb.wx.wxmessagepush.config.SpringExpressRunner;
import com.nkznb.wx.wxmessagepush.controller.WechatController;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.InstructionSetContext;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Can.Ru
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EasyRuleTest {



    @Test
    public void rule1() {
        //规则条件
        String condition = "name contains(\"张\")";
        String condition2 = "age > 10 && age <= 20";
        //规则引擎
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        //具体规则，当满足condition，然后输出对应的success
        Rule rule = new MVELRule().when(condition).then("System.out.println(\"name success\")").priority(2);
        Rule rule2 = new MVELRule().when(condition2).then("System.out.println(\"age success\")").priority(1);

        rules.register(rule);
        rules.register(rule2);
        //匹配规则的事实
        Facts facts = new Facts();
        facts.put("name", "张");
        facts.put("age", "11");

        rulesEngine.fire(rules, facts);
    }

    @Autowired
    private WechatController wechatController;

    @Test
    public void rule2() {

    }


    @Autowired
    SpringExpressRunner springExpressRunner;
    /**
     * for (i=0;i<1;i++){
     * if (#match.id1) {
     * myService.getAge(#user);
     * break;
     * }
     * }
     * @throws Exception
     */

    @Test
    public void tes10000() throws Exception {
        Boolean aBoolean = wechatController.easyRule1();
        ExpressRunner runner = new ExpressRunner();
        String express = "wechatController.easyRule1()";
        int num = 10;
        springExpressRunner.execute(express, new HashMap<>());
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            springExpressRunner.execute(express, new HashMap<>());
        }
        System.out.println("执行" + num + "次\"" + express + "\" 耗时："
                + (System.currentTimeMillis() - start));

    }

    @Test
    public void tes2() throws Exception {
        Boolean aBoolean = wechatController.easyRule3(1,2);
        HashMap<String, Object> map = new HashMap<>();
        map.put("#a",10);
        map.put("#b",20);
        String express = "wechatController.easyRule3(#a,#b)";
        Object execute = springExpressRunner.execute(express, map);
        System.out.println(execute);


    }



    @Test
    public void testDoQueryBehaviorData() throws Exception {
        //String valueBegin, String valueEnd, String demarcationType,String type,String jsonParam


        String express = "wechatController.doQueryBehaviorData(valueBegin,valueEnd,demarcationType,type,jsonParam)";
        HashMap<String, Object> map = new HashMap<>();
        map.put("valueBegin",null);
        map.put("valueEnd",null);
        map.put("demarcationType",null);
        map.put("type","'IndicatorBehavior'");
        map.put("jsonParam","'{\"measure\":[{\"dateParams\":{\"timeType\":\"static\",\"startType\":\"static\",\"startTime\":\"2022-09-29 00:00:00\",\"startDays\":1,\"endTime\":\"2022-09-30 23:59:59\",\"endDays\":1},\"eventCode\":\"1432682982\",\"eventRule\":[{\"logic\":\"and\",\"attributeCode\":\"Distributionofthenumber\",\"valueType\":1,\"operator\":\"greater\",\"value\":[\"19\"],\"metricOperator\":\"sum\"}],\"functionType\":1}]}'");
        Object execute = springExpressRunner.execute(express, map);
        System.out.println(execute);


    }

    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationContextUtil applicationContextUtil;

    @Test
    public void testBean() throws Exception {


        //规则条件
        String condition = "@wechatController.easyRule3(a,b) && true";
        HashMap<String, Object> ruleResultMap = new HashMap<>();


        //规则引擎
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        //具体规则，当满足condition，然后输出对应的success
        Rule rule = new MVELRule().when(condition).then("System.out.println(\"name success\")").priority(2);

        rules.register(rule);
        //匹配规则的事实
        Facts facts = new Facts();
        facts.put("name", "张");
        facts.put("age", "11");
        facts.put("a",10);
        facts.put("b",20);

        rulesEngine.fire(rules, facts);



    }

    @Test
    public void test00x(){

        String condition = "easyRule3 || true && easyRule1";

        HashMap<String, Object> map = new HashMap<>();
        map.put("a",10);
        map.put("b",20);
        RuleConditionDTO.Condition easyRule3 = RuleConditionDTO.Condition.builder().method("easyRule3").params(map).build();

        RuleConditionDTO.Condition easyRule1 = RuleConditionDTO.Condition.builder().method("easyRule1").params(new HashMap<String, Object>()).build();
        ArrayList<RuleConditionDTO.Condition> list = new ArrayList<>();
        list.add(easyRule1);
        list.add(easyRule3);
        RuleConditionDTO ruleList = RuleConditionDTO.builder().conditionList(list).build();
        String c = pc(condition,ruleList);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        //具体规则，当满足condition，然后输出对应的success
        Rule rule = new MVELRule().when(c).then("System.out.println(\"name success\")").priority(2);

        rules.register(rule);
        //匹配规则的事实
        Facts facts = new Facts();
        facts.put("name", "张");
        facts.put("age", "11");
        facts.put("a",10);
        facts.put("b",20);

        rulesEngine.fire(rules, facts);
    }

    private String pc(String condition, RuleConditionDTO ruleList) {

        for (RuleConditionDTO.Condition a : ruleList.getConditionList()) {
            String method = ApplicationContextUtil.methodMap.get(a.getMethod());
            Object execute = springExpressRunner.execute(RuleConditionDTO.service + "." + method, a.getParams());
            System.out.println(execute);
            condition = condition.replaceAll(a.getMethod(), execute.toString());
        }
        return condition;
    }
    @Test
    public void asa() throws Exception {
        String c ="true && false || true";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        ExpressRunner runner = new ExpressRunner();
        Object execute = runner.execute(c, context, null, true, false);
        System.out.println(execute);
    }


}
