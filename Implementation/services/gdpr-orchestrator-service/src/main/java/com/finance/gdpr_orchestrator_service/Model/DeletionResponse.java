package com.finance.gdpr_orchestrator_service.Model;

public class DeletionResponse {
    private String serviceName;
    private boolean success;
    private String message;

    public DeletionResponse() {
    }

    public DeletionResponse(String serviceName, boolean success, String message) {
        this.serviceName = serviceName;
        this.success = success;
        this.message = message;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}