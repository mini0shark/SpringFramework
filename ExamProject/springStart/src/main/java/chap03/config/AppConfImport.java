package chap03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppCtx1.class, AppCtx2.class})// AppCtx1�� AppCtx�� �Բ� ���� �� �� ����
public class AppConfImport {

}
