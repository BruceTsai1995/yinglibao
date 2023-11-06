package com.bjpowernode.front.view;

import com.bjpowernode.common.enums.RCode;

import java.util.List;

/*统一的应答结果类，也就是说controller类返回值类型都是RespResult*/
public class RespResult {
    //应答码，自定义的数字
    private int code;

    //应答码code的文字说明，一般作为提示，给用户看的
    private String msg;

    //单个数据
    private Object data;

    //存放集合数据
    private List list;

    //分页
    private PageInfo page;

    //表示成功的RespResult对象
    public static RespResult ok(){
        RespResult result = new RespResult();
        result.setRCode(RCode.SUCC);
        return result;
    }

    //表示失败的RespResult对象
    public static RespResult fail(){
        RespResult result = new RespResult();
        result.setRCode(RCode.UNKNOW);
        return result;
    }

    public void setRCode(RCode rcode){
        this.code = rcode.getCode();
        this.msg = rcode.getText();

    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
