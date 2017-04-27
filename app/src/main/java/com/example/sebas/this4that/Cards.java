package com.example.sebas.this4that;

/**
 * Created by sebas on 4/25/2017.
 */

public class Cards {
    private String cardImageUrl;
    private String cardTitle;
    private String cardDescription;

    public Cards(){

    }

    public Cards(String cardImageUrl, String cardTitle, String cardDescription) {
        this.cardImageUrl = cardImageUrl;
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }
}
