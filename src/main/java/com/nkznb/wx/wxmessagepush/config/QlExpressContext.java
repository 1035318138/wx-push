package com.nkznb.wx.wxmessagepush.config;

import com.ql.util.express.IExpressContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
* @author efun
* @date 2020/09/01
*/
@Data
@EqualsAndHashCode(callSuper=true)
public class QlExpressContext extends HashMap<String, Object> implements IExpressContext<String, Object> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private transient ApplicationContext context;

    public QlExpressContext(Map<String, Object> aProperties, ApplicationContext aContext) {
        super(aProperties);
        this.context = aContext;
    }

    /**
     * 根据key从容器里面获取对象
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {
        Object object = super.get(key);
        try {
            if (object == null && this.context != null && this.context.containsBean((String)key)) {
                object = this.context.getBean((String)key);
            }
        } catch (Exception e) {
            throw new RuntimeException("Get Expression Container Object failed.", e);
        }
        return object;
    }

    /**
     * 把key-value放到容器里面去
     *
     * @param key
     * @param value
     */
    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }

}
