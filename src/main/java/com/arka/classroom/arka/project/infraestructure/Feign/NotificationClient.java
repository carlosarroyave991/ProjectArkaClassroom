package com.arka.classroom.arka.project.infraestructure.Feign;

import com.arka.classroom.arka.project.infraestructure.Request.NotificacionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8081")
public interface NotificationClient {

    @PostMapping("/api/notifications/send")
    void sendNotification(@RequestBody NotificacionRequest notificacionRequest);
}
