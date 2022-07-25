package com.hx.mdesign.garbage;

import java.util.List;

/**
 * @Author Hx
 * @Date 2022/3/8 9:43
 */
public class Garbage_info {
    private String cate_name;
    private String city_id;
    private String city_name;
    private int confidence;
    private String garbage_name;
    private String ps;

    @Override
    public String toString() {
        return "Garbage_info{" +
                "cate_name='" + cate_name + '\'' +
                ", city_id='" + city_id + '\'' +
                ", city_name='" + city_name + '\'' +
                ", confidence=" + confidence +
                ", garbage_name='" + garbage_name + '\'' +
                ", ps='" + ps + '\'' +
                '}';
    }

    public String getCate_name() {
        return this.cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getCity_id() {
        return this.city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return this.city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getConfidence() {
        return this.confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getGarbage_name() {
        return this.garbage_name;
    }

    public void setGarbage_name(String garbage_name) {
        this.garbage_name = garbage_name;
    }

    public String getPs() {
        return this.ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    class Root {
        private String code;
        private boolean charge;
        private int remain;
        private String msg;
        private Result result;

        @Override
        public String toString() {
            return "Root{" +
                    "code='" + code + '\'' +
                    ", charge=" + charge +
                    ", remain=" + remain +
                    ", msg='" + msg + '\'' +
                    ", result=" + result +
                    '}';
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean getCharge() {
            return this.charge;
        }

        public void setCharge(boolean charge) {
            this.charge = charge;
        }

        public int getRemain() {
            return this.remain;
        }

        public void setRemain(int remain) {
            this.remain = remain;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Result getResult() {
            return this.result;
        }

        public void setResult(Result result) {
            this.result = result;
        }
    }

    class Result {
        private List<Garbage_info> garbage_info;
        private String message;
        private int status;

        @Override
        public String toString() {
            return "Result{" +
                    "garbage_info=" + garbage_info +
                    ", message='" + message + '\'' +
                    ", status=" + status +
                    '}';
        }

        public List<Garbage_info> getGarbage_info() {
            return this.garbage_info;
        }

        public void setGarbage_info(List<Garbage_info> garbage_info) {
            this.garbage_info = garbage_info;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}





