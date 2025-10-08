package com.una.security.token.routes;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class APIRoutes {
    public final List<String> adminRoutes = List.of(
            "/coco_tours/api/v2/admin/**",
            "/coco_tours/api/v2/client/**",
            "/coco_tours/api/v2/extra_details/**",
            "/coco_tours/api/v2/tour_details/**",
            "/coco_tours/api/v2/extra/**",
            "/coco_tours/api/v2/passengers/**",
            "/coco_tours/api/v2/reservation/**",
            "/coco_tours/api/v2/supplier/**",
            "/coco_tours/api/v2/tours/**",
            "/coco_tours/api/v2/user/**",
            "/coco_tours/api/v2/user/admin/**",
            "/coco_tours/api/v2/user/client/delete/update",
            "/coco_tours/api/v2/user/client/delete/delete"
    );

    public final List<String> clientRoutes = List.of(
            "/coco_tours/api/v2/client/**",
            "/coco_tours/api/v2/extra/all",
            "/coco_tours/api/v2/passengers/**",
            "/coco_tours/api/v2/reservation/**",
            "/coco_tours/api/v2/tours/all",
            "/coco_tours/api/v2/user/**"
    );
}
