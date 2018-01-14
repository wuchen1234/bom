package zhth.bom.management.bom.util.common;

import zhth.bom.management.bom.util.exception.ServiceException;

public class IMBomResponse<T> {
    private  T data;
    private String message;
    private String code;
    public IMBomResponse(T data) {
        this(IMBomResponseCode.SUCCESS);
        this.data = data;
    }
    public IMBomResponse(IMBomResponseCode IMbomResponseCode, T data) {
        this(IMbomResponseCode);
        this.data = data;
    }

    public IMBomResponse(IMBomResponseCode IMbomResponseCode) {
        this.code = IMbomResponseCode.getCode();
        this.message = IMbomResponseCode.getMessage();
    }

    public IMBomResponse(Exception exception) {
        if (exception instanceof ServiceException) {
            this.code = ((ServiceException) exception).getBomResponseCode().getCode();
            this.message = exception.getMessage();
        } else {
            this.code = IMBomResponseCode.EXCEPTION.getCode();
            this.message = IMBomResponseCode.EXCEPTION.getMessage() + exception.getMessage();
        }
    }

    public static IMBomResponse build() {
        return new IMBomResponse<>(IMBomResponseCode.SUCCESS);
    }

    public static <E> IMBomResponse<E> build(E data) {
        return new IMBomResponse<>(data);
    }
    public static <E> IMBomResponse<E> build(Exception e){
        return new IMBomResponse<>(e);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
