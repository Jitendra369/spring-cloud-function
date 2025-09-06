package com.cloud.function.function;

import com.cloud.function.dto.RequestData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class WebFunction {

    @Bean
    public Supplier<String> sayHello() {
        return () -> "Hello world ";
    }

    @Bean
    public Consumer<RequestData> inputData() {
        return inputData -> {
            System.out.println(inputData.getName());
            System.out.println(inputData.getContact());
        };
    }

    @Bean
    public Function<RequestData, String> submitData() {
        return inputData -> {
            return "Data submitted name  :" + inputData.getName() + " contact " + inputData.getContact();
        };
    }

//    function to make string upper class , it can be use through function-catalog
    @Bean
    public Function<String , String > toUpperCase(){
        return String::toUpperCase;
    }

    @Bean
    public Function<String, Integer> getLength() {
        return String::length;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            FunctionCatalog functionCatalog = applicationContext.getBean(FunctionCatalog.class);
//            Function<String, String> uppperCaseFunction = functionCatalog.lookup(null); // getting null pointer exception , as function-name is null
            Function<String, String> uppperCaseFunction = functionCatalog.lookup("toUpperCase");
            System.out.println("Uppercase : " + uppperCaseFunction.apply("Vikas Dubey"));

            Function<String, Integer> lengthFunction =  functionCatalog.lookup("getLength");
            System.out.println("length is "+ lengthFunction.apply("Siddesh aparaj"));

            Function<String, Integer> upperClassFunction =  functionCatalog.lookup("upperCaseFunction");
            System.out.println("length is "+ upperClassFunction.apply("Siddesh aparaj"));
        };
    }

}

