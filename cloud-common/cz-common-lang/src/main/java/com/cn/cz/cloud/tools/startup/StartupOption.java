package com.cn.cz.cloud.tools.startup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ywaz
 * @date 5/11/18 10:28
 */
public class StartupOption {
    public static String WORK_DIR = "work.dir";
    public static String SERVER_BIND = "server.bind";
    public static String VOLUME_IMAGE = "volume.image";
    public static String NET_BR_VM = "net.brigade.vm";
    public static String NET_QOS_VM = "net.qos.vm";
    public static String STARTUP_TYPE = "startup.type";

    private static StartupOption option;
    private OptionProcess process;
    private Map<String, String> options;

    private StartupOption() {
        options = new HashMap();
    }

    public static StartupOption getOption() {
        synchronized (StartupOption.class) {
            if (option == null) {
                option = new StartupOption();
            }
        }
        return option;
    }

    public void init(String[] args) {
        process = new OptionProcess(args);
        options = process.getOptions();
    }

    public String getValue(String name) {
        return process.getOptions().get(name);
    }

    public boolean exist(String opt) {
        for (String _arg : process.getOptList()) {
            if(opt.equals(_arg))
                return true;
        }
        return false;
    }

    public List<String> getOpts() {
        return process.getOptList();
    }
    public void printArgs() {
        for (Map.Entry<String, String> arg : options.entrySet()) {
            System.out.println("Param:" + arg.getKey() + "    Value:" + arg.getValue());
        }
        for (String _arg : process.getOptList()) {
            System.out.println("Option:" + _arg);
        }
    }
}
