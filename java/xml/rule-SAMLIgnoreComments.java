// License: LGPL-3.0 License (c) find-sec-bugs
package xml;

import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.parse.ParserPool;
import org.springframework.context.annotation.Bean;

public class SAMLIgnoreComments {
  @Bean
  ParserPool parserPool() {
    BasicParserPool pool = new BasicParserPool();
    // ruleid: java_xml_rule-SAMLIgnoreComments
    pool.setIgnoreComments(false);
    return pool;
  }

  @Bean
  void parserPool2() {
    boolean shouldIgnore = false;
    BasicParserPool pool = new BasicParserPool();
    // ruleid: java_xml_rule-SAMLIgnoreComments
    pool.setIgnoreComments(shouldIgnore);
  }
}
