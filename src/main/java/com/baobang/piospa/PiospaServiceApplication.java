package com.baobang.piospa;



import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PiospaServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(PiospaServiceApplication.class, args);
	}
	
	@Bean
	public Docket userAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.baobang.piospa.controller.api"))
			.paths(PathSelectors.regex(".*"))
			.build()
			.apiInfo(metaData());
	}
	
	
	private ApiInfo metaData() {
	    return new ApiInfoBuilder()
	        .title("PioSpa RESTfull API")
	        .description("Using Spring Boot and Springfox for Swagger")
	        .contact(new Contact("Nguyễn Bảo Bằng", "", "baobangb5@gmail.com"))
	        .version("1.0.0")
	        .build();
	}
	
	// notify
	
//	   static final String topicExchangeName = "spring-boot-exchange";
//
//	    static final String queueName = "spring-boot";
//
//	    @Bean
//	    Queue queue() {
//	        return new Queue(queueName, false);
//	    }
//
//	    @Bean
//	    TopicExchange exchange() {
//	        return new TopicExchange(topicExchangeName);
//	    }
//
//	    @Bean
//	    Binding binding(Queue queue, TopicExchange exchange) {
//	        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//	    }
//
//	    @Bean
//	    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//	            MessageListenerAdapter listenerAdapter) {
//	        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	        container.setConnectionFactory(connectionFactory);
//	        container.setQueueNames(queueName);
//	        container.setMessageListener(listenerAdapter);
//	        return container;
//	    }
//
//	    @Bean
//	    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//	        return new MessageListenerAdapter(receiver, "receiveMessage");
//	}
}
