package chap03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppCtx1.class, AppCtx2.class})// AppCtx1과 AppCtx를 함께 쓰는 것 과 같음
public class AppConfImport {

}
