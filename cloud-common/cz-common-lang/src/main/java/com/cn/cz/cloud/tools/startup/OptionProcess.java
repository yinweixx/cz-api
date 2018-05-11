package com.cn.cz.cloud.tools.startup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author ywaz
 * @date 5/11/18 10:27
 */
public class OptionProcess {
    private Map<String,String> options;
    private LinkedList<String> optList;
    private final String[] args;
    public OptionProcess(String[] args) {
        this.args = args;
    }

    public Map<String,String> getOptions() {
        options = new HashMap();
        optList = new LinkedList<>();
        if(args == null || args.length == 0)
            return options;
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if(arg.substring(0, 1).equals("-") && !arg.substring(0, 2).equals("--")) {
                String key = arg;
                String value = args[i+1];
                if(value.startsWith("--")) {
                    throw new RuntimeException("参数 " + key.substring(1) + "没有设置值");
                } else {
                    options.put(key.substring(1), value);
                    i++;
                }
            } else if(arg.startsWith("--")) {
                if(!arg.contains("="))
                    throw new RuntimeException("参数 " + arg + "没有设置值");
                else {
                    String key = arg.substring(2, arg.indexOf('='));
                    String value = arg.substring(arg.indexOf('=') + 1);
                    options.put(key,value);
                }
            } else {
                optList.add(arg);
            }
        }
        return options;
    }

    public List<String> getOptList() {
        return optList;
    }
}
