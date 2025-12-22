package com.chefathands.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {
    
    // Mock: Always returns the same notifications
    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<String> getUserNotifications(
            @PathVariable String userId,
            @RequestParam(required = false) Boolean unreadOnly) {
        String mockResponse = """
            [
                {
                    "id": "notif-1",
                    "userId": "user123",
                    "type": "NEW_RECOMMENDATION",
                    "title": "New Recipe Recommendations",
                    "message": "Check out 5 new recipes based on your preferences!",
                    "relatedResourceId": "recommendation-123",
                    "read": false,
                    "createdAt": "2025-12-20T10:00:00",
                    "readAt": null
                },
                {
                    "id": "notif-2",
                    "userId": "user123",
                    "type": "SYSTEM_MESSAGE",
                    "title": "Welcome to ChefAtHands",
                    "message": "Start exploring delicious recipes today!",
                    "relatedResourceId": null,
                    "read": true,
                    "createdAt": "2025-12-15T09:00:00",
                    "readAt": "2025-12-15T10:30:00"
                },
                {
                    "id": "notif-3",
                    "userId": "user123",
                    "type": "INGREDIENT_ALERT",
                    "title": "Ingredient Expiring Soon",
                    "message": "Your milk expires in 2 days",
                    "relatedResourceId": "ingredient-456",
                    "read": false,
                    "createdAt": "2025-12-21T08:00:00",
                    "readAt": null
                }
            ]
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns success
    @PostMapping("/notifications")
    public ResponseEntity<String> createNotification(@RequestBody String body) {
        String mockResponse = """
            {
                "id": "notif-new",
                "userId": "user123",
                "type": "INFO",
                "title": "Mock Notification",
                "message": "This is a mock notification",
                "relatedResourceId": null,
                "read": false,
                "createdAt": "2025-12-21T12:00:00",
                "readAt": null
            }
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns notification marked as read
    @PostMapping("/users/{userId}/notifications/{notificationId}/read")
    public ResponseEntity<String> markAsRead(@PathVariable String userId, @PathVariable String notificationId) {
        String mockResponse = """
            {
                "id": "notif-1",
                "userId": "user123",
                "type": "NEW_RECOMMENDATION",
                "title": "New Recipe Recommendations",
                "message": "Check out 5 new recipes based on your preferences!",
                "relatedResourceId": "recommendation-123",
                "read": true,
                "createdAt": "2025-12-20T10:00:00",
                "readAt": "2025-12-21T12:00:00"
            }
            """;
        return ResponseEntity.ok(mockResponse);
    }
    
    // Mock: Always returns success
    @PostMapping("/users/{userId}/notifications/read-all")
    public ResponseEntity<Void> markAllAsRead(@PathVariable String userId) {
        return ResponseEntity.noContent().build();
    }
    
    // Mock: Always returns success
    @DeleteMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String userId, @PathVariable String notificationId) {
        return ResponseEntity.noContent().build();
    }
    
    // Mock: Always returns 2 unread notifications
    @GetMapping("/users/{userId}/notifications/unread-count")
    public ResponseEntity<String> getUnreadCount(@PathVariable String userId) {
        return ResponseEntity.ok("2");
    }
}
