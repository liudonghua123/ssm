package ssm.codegen;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.service.CodeGeneratorService;
import ssm.codegen.service.impl.CodeGeneratorServiceImpl;

/**
 * @author jinqinghua@gmail.com
 * @version 2013-11-07
 */
public class CodeGenerator {

	public static void main(String[] args) throws IOException {
		final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("production"); // setting profile
		ctx.load("classpath:spring-context.xml");
		ctx.refresh();

		String[] beanNames = ctx.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			logger.debug("...................{}", beanName);
		}

		CodeGeneratorService codeGenerator = ctx.getBean("codeGeneratorServiceImpl", CodeGeneratorServiceImpl.class);
		CodeGeneratorConfig cfg = ctx.getBean("codeGeneratorConfig", CodeGeneratorConfig.class);

		Assert.notNull(codeGenerator);
		Assert.notNull(ctx);

		logger.debug("begin generate...");
		codeGenerator.generateDomainFiles();
		codeGenerator.generateDaoFiles();
		codeGenerator.generateDaoSqlSessionImplFiles();
		codeGenerator.generateDaoMyBatisMapperFiles();
		codeGenerator.generateMyBatisConfigFile(true);
		// codeGenerator.generateDaoJdbcImplFiles();
		codeGenerator.generateServiceFiles();
		codeGenerator.generateServiceImplFiles();
		codeGenerator.generateFacadeFile(true);
		codeGenerator.generateFacadeImplFile(true);
		logger.debug("end generate...");

		((AbstractApplicationContext) ctx).close(); // close ApplicationContext

		Runtime.getRuntime().exec("cmd.exe /c start " + cfg.getProjectPathName()); // open the folder
	}

}
