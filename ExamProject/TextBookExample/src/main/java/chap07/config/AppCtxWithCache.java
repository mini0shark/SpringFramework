package chap07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import chap07.aspect.CacheAspect;
import chap07.aspect.ExeTimeAspect;
import chap07.calc.Calculator;
import chap07.calc.RecCalculator;

@Configuration
@EnableAspectJAutoProxy
public class AppCtxWithCache {
	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
