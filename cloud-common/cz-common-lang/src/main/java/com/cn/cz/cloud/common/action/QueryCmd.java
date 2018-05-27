package com.cn.cz.cloud.common.action;

public class QueryCmd {

    public static final String TITLE_DUMMY = "";
    public static final QueryCmd QUERY_DUMMY = new QueryCmd() {
        public void execute() { }
    };

    public QueryCmd() {
    }

    public void execute() {
    }

    public Object query() {
        return null;
    }
}
