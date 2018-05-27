package com.cn.cz.cloud.common.action;

public class Action {

    private void render(Object text,
                        String contentType) {
    }

    protected String query(QueryCmd cmd, String title, String path) {
        try {
            cmd.execute();
            return path;
        } catch (Exception var6) {
            return "";
        } catch (Throwable var7) {
            return "redirect:";
        }
    }

    protected String view( QueryCmd cmd, String title, String path) {
        return this.query(cmd, title, path);
    }

    protected void ajaxSimpleQuery(QueryCmd cmd){
        cmd.execute();;
    }
}
