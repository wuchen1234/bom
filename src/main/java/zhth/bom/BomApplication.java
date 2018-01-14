package zhth.bom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"zhth.bom"})
@ServletComponentScan("zhth.bom.management.bom.handlerinterceptor")
public class BomApplication extends SpringBootServletInitializer {
	/**
	 * 主程序
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BomApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BomApplication.class);
	}
}
