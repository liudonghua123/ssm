package ssm.codegen;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ssm.codegen.config.CodeGeneratorConfig;
import ssm.codegen.service.CodeGeneratorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-context.xml")
@ActiveProfiles("test")
public class CodeGeneratorTest {

	@Resource
	CodeGeneratorService codeGenerator;

	@Resource
	CodeGeneratorConfig cfg;

	@Test
	@Ignore
	public void test() throws IOException {
		final Logger logger = LoggerFactory.getLogger(this.getClass());

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

		Runtime.getRuntime().exec("cmd.exe /c start " + cfg.getProjectSourcePath()); // open the folder
	}

}
