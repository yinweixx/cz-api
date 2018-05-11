package com.cn.cz.cloud.tools.component;

import com.cn.cz.cloud.tools.wsrs.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author ywaz
 * @date 5/11/18 11:18
 */
public class SystemProvider {
    private static Logger LOGGER = LoggerFactory.getLogger(SystemProvider.class);

    public static String getProviderNames(String packageName, boolean childPackage) {
        //api包路径
        List<String> packageNames = FileUtil.getClassName(packageName, childPackage);
        LOGGER.debug("List<String> packageNames:[{}]", packageNames);
        if (packageNames != null && packageNames.size() > 0) {
            LOGGER.debug("packageNames size:[{}]", packageNames.size());
        } else {
            LOGGER.debug("packageNames size:", 0);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int number = 1;
        for (String name : packageNames) {
            stringBuilder.append(name).append("\n");
            LOGGER.debug("number:[{}]------ClassName:[{}]", number++, name);
        }
        return stringBuilder.toString();
    }
}
