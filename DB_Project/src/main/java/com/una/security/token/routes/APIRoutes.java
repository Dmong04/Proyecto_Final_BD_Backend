
package com.una.security.token.routes;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class APIRoutes {
    
    public final List<String> clientRoutes = List.of(
            "/coco_tours/api/v2/client/**",
            "/coco_tours/api/v2/extra/all",
            "/coco_tours/api/v2/passengers/**",
            "/coco_tours/api/v2/reservation/**",
            "/coco_tours/api/v2/tours/all",
            "/coco_tours/api/v2/user/**"
    );
    
    public final List<String> employeeRoutes = List.of(
            "/coco_tours/api/v2/**"
    );
    
    public final List<String> adminRoutes = List.of(
            "/coco_tours/api/v2/**"
    );
}
