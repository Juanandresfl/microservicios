package com.pragma.gateway.swaggerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider{

    private static final String SWAGGER2URL = "/v2/api-docs";

    @Autowired
    private RouteLocator routeLocator;

    @Value("servicio-gateway")
    private String self;

    @Value("servicio-admin")
    private String admin;


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routeHosts = new ArrayList<>();
        routeLocator.getRoutes()
                .filter(route -> route.getUri().getHost() != null)
                .filter(route -> Objects.equals(route.getUri().getScheme(), "lb"))
                .filter(route -> !self.equalsIgnoreCase(route.getUri().getHost()))
                .filter(route -> !admin.equalsIgnoreCase(route.getUri().getHost()))
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));

        Set<String> dealed = new HashSet<>();
        routeHosts.forEach(instance -> {
            String url = "/" + instance.toLowerCase() + SWAGGER2URL;
            if (!dealed.contains(url)) {
                dealed.add(url);
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setUrl(url);
                swaggerResource.setName(instance);
                swaggerResource.setSwaggerVersion("2.9.2");
                resources.add(swaggerResource);
            }
        });
        return resources;
    }

}
