package com.contact.backend.contactsbe.dto;

public class ResponseMessageDto {
   private String message;
    private  Boolean status ;
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




}
