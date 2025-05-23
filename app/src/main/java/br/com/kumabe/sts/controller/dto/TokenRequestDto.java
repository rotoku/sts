package br.com.kumabe.sts.controller.dto;

import java.util.Objects;

public class TokenRequestDto {
    private String grant_type;
    private String client_id;
    private String client_secret;

    public TokenRequestDto() {
    }

    public TokenRequestDto(String grant_type, String client_id, String client_secret) {
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TokenRequestDto that = (TokenRequestDto) o;
        return Objects.equals(grant_type, that.grant_type) && Objects.equals(client_id, that.client_id) && Objects.equals(client_secret, that.client_secret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grant_type, client_id, client_secret);
    }

    @Override
    public String toString() {
        return "TokenRequestDto{" +
                "client_id='" + client_id + '\'' +
                ", grant_type='" + grant_type + '\'' +
                ", client_secret='" + client_secret + '\'' +
                '}';
    }

}