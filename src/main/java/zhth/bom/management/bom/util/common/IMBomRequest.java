package zhth.bom.management.bom.util.common;

import org.hibernate.validator.constraints.NotBlank;

public class IMBomRequest <T>{


        @NotBlank(message = "data参数不能为空", groups = {data.class})
        private T data;

        public IMBomRequest() {
        }

        public IMBomRequest(T data) {
            this.data = data;
        }


        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public static <E> IMBomRequest<E> build(E data) {
            return new IMBomRequest<>(data);
        }
        public interface data{

        }
}
