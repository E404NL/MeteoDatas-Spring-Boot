package fr.neriumprod.meteodatasspringapp.config.graphql;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.scalars.ExtendedScalars;
import graphql.schema.*;
import graphql.schema.idl.RuntimeWiring;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * This class is for add new objects to serialize or parse with graphql
 * Because some classes are not known for GraphQL
 */
@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return new RuntimeWiringConfigurer() {
            @Override
            public void configure(RuntimeWiring.@NonNull Builder builder) {
                builder.scalar(ExtendedScalars.DateTime)
                        .scalar(localDateTimeScalar())
                        .scalar(ExtendedScalars.Date);
            }
        };
    }

    /**
     *
     * @return a LocalDateTime serialized or parsed by input/output with GraphQL
     */
    public GraphQLScalarType localDateTimeScalar(){
        return GraphQLScalarType.newScalar()
                .name("LocalDateTime")
                .description("Java LocalDateTime scalar")
                .coercing(new Coercing<LocalDateTime, String>() {
                    @Override
                    public @Nullable String serialize(
                            @NonNull Object dataFetcherResult,
                            @NonNull GraphQLContext graphQLContext,
                            @NonNull Locale locale) throws CoercingSerializeException {
                        return ((LocalDateTime) dataFetcherResult).toString();
                    }

                    @Override
                    public @Nullable LocalDateTime parseValue(
                            @NonNull Object input,
                            @NonNull GraphQLContext graphQLContext,
                            @NonNull Locale locale) throws CoercingParseValueException {
                        return LocalDateTime.parse(input.toString());
                    }

                    @Override
                    public @Nullable LocalDateTime parseLiteral(
                            @NonNull Value<?> input,
                            @NonNull CoercedVariables variables,
                            @NonNull GraphQLContext graphQLContext,
                            @NonNull Locale locale) throws CoercingParseLiteralException {
                        if(input instanceof StringValue stringValue){
                            assert stringValue.getValue() != null;
                            return LocalDateTime.parse(stringValue.getValue());
                        }throw new CoercingParseLiteralException("Invalid LocalDateTime format");
                    }
                })
                .build();
    }
}
