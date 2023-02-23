package com.contact.backend.contactsbe.dto;

public class ResponseMessageDto {
   private String message;

    public ResponseMessageDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private  Boolean status ;


}
