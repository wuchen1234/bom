package zhth.bom.management.bom.util.exception;

import zhth.bom.management.bom.util.common.IMBomResponseCode;

public class ServiceException extends RuntimeException {

    private IMBomResponseCode bomResponseCode;

    public ServiceException(Exception e){
        super(e.getMessage());
        if(e instanceof  ServiceException){
            this.bomResponseCode=((ServiceException) e).bomResponseCode;
        }else{
            this.bomResponseCode=IMBomResponseCode.EXCEPTION;
        }
    }

    public ServiceException(IMBomResponseCode responseCode){
        super(responseCode.getMessage());
        this.bomResponseCode=responseCode;
    }
    

    public IMBomResponseCode getBomResponseCode() {
        return bomResponseCode;
    }

    public void setBomResponseCode(IMBomResponseCode bomResponseCode) {
        this.bomResponseCode = bomResponseCode;
    }
}
