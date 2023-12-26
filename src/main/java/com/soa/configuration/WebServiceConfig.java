/**
 * 
 */
package com.soa.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;

/**
 * 
 */
@EnableWs
@Configuration
public class WebServiceConfig {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/crear/*");
    }

    @Bean
    public SimpleXsdSchema servicioTelefonicoSchema() {
        return new SimpleXsdSchema(new ClassPathResource("servicioTelefonico.xsd"));
    }

    @Bean(name = "servicioTelefonico")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema 
            servicioTelefonicoSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CrearReportePort");
        wsdl11Definition.setLocationUri("/crear");
        wsdl11Definition.setTargetNamespace("http://soa.com");
        wsdl11Definition.setSchema(servicioTelefonicoSchema);
        return wsdl11Definition;
    }
}