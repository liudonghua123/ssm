package ssm.codegen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;
import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.service.CodeGeneratorService;
import ssm.codegen.service.impl.CodeGeneratorServiceImpl;

import java.io.IOException;

/**
 * @author jinqinghua@gmail.com
 * @version 2013年11月29日 下午9:24:07
 */
public class CodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
    private static final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    private static String profile = "production";

    public static void main(String[] args) throws IOException {
        if (null != args && args.length > 0) {
            profile = args[0];
        }
        ctx.getEnvironment().setActiveProfiles(profile);
        ctx.load("classpath:applicationContext.xml");
        ctx.refresh();

        String[] beanNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            logger.debug("...................{}", beanName);
        }

        CodeGeneratorService codeGenerator = ctx.getBean("codeGeneratorServiceImpl", CodeGeneratorServiceImpl.class);
        CodeGeneratorConfig cfg = ctx.getBean("codeGeneratorConfig", CodeGeneratorConfig.class);

        Assert.notNull(codeGenerator, "CodeGeneratorService should not be null.");
        Assert.notNull(ctx, "ApplicationContext should not be null.");

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

        ctx.close(); // close ApplicationContext

        Runtime.getRuntime().exec("cmd.exe /c start " + cfg.getProjectPathName()); // open the folder
    }

}
