package com.barath.app;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.dsl.Ftp;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

/**
 * The Class FTPIntegrationFlow.
 */
@Configuration
public class FTPIntegrationFlow {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/** The resource loader. */
	@Autowired
	private ResourceLoader resourceLoader;

	/** The remote dir. */
	@Value("${ftp.remote.dir}")
	private String remoteDir;

	/**
	 * Ftp session factory.
	 *
	 * @param host     the host
	 * @param username the username
	 * @param password the password
	 * @return the session factory
	 */
	@Bean
	public SessionFactory<FTPFile> ftpSessionFactory(@Value("${ftp.host}") String host,
			@Value("${ftp.username}") String username, @Value("${ftp.password}") String password) {
		DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
		sf.setHost(host);
		sf.setUsername(username);
		sf.setPassword(password);
		return new CachingSessionFactory<>(sf);
	}

	/**
	 * Ftp outbound flow.
	 *
	 * @param ftpSessionFactory the ftp session factory
	 * @return the integration flow
	 */
	@Bean
	public IntegrationFlow ftpOutboundFlow(SessionFactory ftpSessionFactory) {

		if (logger.isInfoEnabled()) {
			logger.info(" defining integration flow start");
		}
		return IntegrationFlows.from("inputChannel")
				.handle(Ftp.outboundAdapter(ftpSessionFactory, FileExistsMode.REPLACE).useTemporaryFileName(false)
						.remoteDirectory(remoteDir))
				.get();

	}

	/**
	 * Input channel.
	 *
	 * @return the message channel
	 */
	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	/**
	 * Gets the resource.
	 *
	 * @param fileLocation the file location
	 * @return the resource
	 * @throws Exception the exception
	 */
	public File getResource(String fileLocation) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info(" load the resource with file location {}", fileLocation);
		}
		return resourceLoader.getResource(fileLocation).getFile();
	}

}
