package com.nkznb.wx.wxmessagepush.config;

import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
* @author efun
* @date 2020/09/01
*/
@Service
@Slf4j
public class SpringExpressRunner implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private ExpressRunner runner;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
        if (runner == null) {
            runner = new ExpressRunner();
            log.info("runner = new ExpressRunner()");
        }
    }

    public Object execute(String text, Map<String, Object> context) {
        IExpressContext<String, Object> expressContext = new QlExpressContext(context, this.applicationContext);
        try {
            return runner.execute(text, expressContext, null, true, false);
        } catch (Exception e) {
            log.error("run qlExpress error, expression:");
            log.error("{}", text);
            log.error(e.getMessage() , e);
        }
        return null;
    }

}
