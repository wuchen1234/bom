package zhth.bom.management.bom.util.common;

public enum IMBomResponseCode {
    SUCCESS("0000", "成功"),
    DATA_NOFOUND("2000", "查询的数据不存在"),
    INSERT_FAIL("3000", "数据写入错误"),
    BUSINESS_FAIL("4000", "业务错误:"),

    EXCEPTION("8000", "异常:"),
    DATA_EMPTY_EXCPTION("8001","数据异常:"),
    REQUEST_PARAMETER_EXCEPTION("8002", "请求参数异常:"),
    RPC_EXCEPTION("8010", "RPC异常:");

    private String code;
    private String message;

    IMBomResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static IMBomResponseCode codeOf(String code) {
        for (IMBomResponseCode value : values()) {
            if (value.getCode().equals(code))
                return value;
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


