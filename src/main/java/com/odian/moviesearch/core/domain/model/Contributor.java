package com.odian.moviesearch.core.domain.model;

import lombok.Data;

import java.util.Objects;


//todo: tests!!!
@Data
public class Contributor {
    private Film film;
    private Person person;
    private ContributorType contributorType;
    private Integer billingOrder;

    public Contributor (Film film, Person person, ContributorType contributorType, Integer billingOrder) {
        if (billingOrder != null && !Objects.equals(contributorType, ContributorType.ACTOR))
            throw new IllegalArgumentException("Only actors can have billing order");
        this.film = film;
        this.person = person;
        this.contributorType = contributorType;
        this.billingOrder = billingOrder;
    }

    public void setBillingOrder (Integer billingOrder) {
        if (!Objects.equals(contributorType, ContributorType.ACTOR))
            throw new IllegalArgumentException("Only actors can have billing order");
        this.billingOrder = billingOrder;
    }
    public void setContributionType (ContributorType type) {
        if (type != ContributorType.ACTOR && !Objects.isNull(billingOrder))
            throw new IllegalArgumentException("Only actors can have billing order");
    }
}
