/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package reactor.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleLoggerTest {

	private static final RuntimeException CAUSE = new IllegalStateException("cause");

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	private Logger logger;

	@Before
	public void setUp() {
		logger = new Loggers.ConsoleLogger("test", new PrintStream(outContent), new PrintStream(errContent));
	}

	@After
	public void cleanUp() {
		outContent.reset();
		errContent.reset();
	}

	@Test
	public void isTraceEnabled() throws Exception {
		assertThat(logger.isTraceEnabled()).isTrue();
	}

	@Test
	public void trace() throws Exception {
		logger.trace("message");

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString()).isEqualTo("[TRACE] message\n");
	}

	@Test
	public void trace1() throws Exception {
		logger.trace("message {} {} format", "with", 1);

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString()).isEqualTo("[TRACE] message with 1 format\n");
	}

	@Test
	public void trace2() throws Exception {
		logger.trace("with cause", CAUSE);

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString())
				.startsWith("[TRACE] with cause - java.lang.IllegalStateException: cause" +
				"\njava.lang.IllegalStateException: cause\n" +
				"\tat reactor.util.ConsoleLoggerTest");
	}

	@Test
	public void isDebugEnabled() throws Exception {
		assertThat(logger.isDebugEnabled()).isTrue();
	}

	@Test
	public void debug() throws Exception {
		logger.debug("message");

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString()).isEqualTo("[DEBUG] message\n");
	}

	@Test
	public void debug1() throws Exception {
		logger.debug("message {} {} format", "with", 1);

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString()).isEqualTo("[DEBUG] message with 1 format\n");
	}

	@Test
	public void debug2() throws Exception {
		logger.debug("with cause", CAUSE);

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString())
				.startsWith("[DEBUG] with cause - java.lang.IllegalStateException: cause" +
						"\njava.lang.IllegalStateException: cause\n" +
						"\tat reactor.util.ConsoleLoggerTest");
	}

	@Test
	public void isInfoEnabled() throws Exception {
		assertThat(logger.isInfoEnabled()).isTrue();
	}

	@Test
	public void info() throws Exception {
		logger.info("message");

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString()).isEqualTo("[INFO] message\n");
	}

	@Test
	public void info1() throws Exception {
		logger.info("message {} {} format", "with", 1);

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString()).isEqualTo("[INFO] message with 1 format\n");
	}

	@Test
	public void info2() throws Exception {
		logger.info("with cause", CAUSE);

		assertThat(errContent.size()).isZero();
		assertThat(outContent.toString())
				.startsWith("[INFO] with cause - java.lang.IllegalStateException: cause" +
						"\njava.lang.IllegalStateException: cause\n" +
						"\tat reactor.util.ConsoleLoggerTest");
	}

	@Test
	public void isWarnEnabled() throws Exception {
		assertThat(logger.isWarnEnabled()).isTrue();
	}

	@Test
	public void warn() throws Exception {
		logger.warn("message");

		assertThat(outContent.size()).isZero();
		assertThat(errContent.toString()).isEqualTo("[WARN] message\n");
	}

	@Test
	public void warn1() throws Exception {
		logger.warn("message {} {} format", "with", 1);

		assertThat(outContent.size()).isZero();
		assertThat(errContent.toString()).isEqualTo("[WARN] message with 1 format\n");
	}

	@Test
	public void warn2() throws Exception {
		logger.warn("with cause", CAUSE);


		assertThat(outContent.size()).isZero();
		assertThat(errContent.toString())
				.startsWith("[WARN] with cause - java.lang.IllegalStateException: cause" +
						"\njava.lang.IllegalStateException: cause\n" +
						"\tat reactor.util.ConsoleLoggerTest");
	}

	@Test
	public void isErrorEnabled() throws Exception {
		assertThat(logger.isErrorEnabled()).isTrue();
	}

	@Test
	public void error() throws Exception {
		logger.error("message");

		assertThat(outContent.size()).isZero();
		assertThat(errContent.toString()).isEqualTo("[ERROR] message\n");
	}

	@Test
	public void error1() throws Exception {
		logger.error("message {} {} format", "with", 1);

		assertThat(outContent.size()).isZero();
		assertThat(errContent.toString()).isEqualTo("[ERROR] message with 1 format\n");
	}

	@Test
	public void error2() throws Exception {
		logger.error("with cause", CAUSE);

		assertThat(outContent.size()).isZero();
		assertThat(errContent.toString())
				.startsWith("[ERROR] with cause - java.lang.IllegalStateException: cause" +
						"\njava.lang.IllegalStateException: cause\n" +
						"\tat reactor.util.ConsoleLoggerTest");
	}

}