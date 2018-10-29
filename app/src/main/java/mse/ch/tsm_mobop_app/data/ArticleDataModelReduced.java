package mse.ch.tsm_mobop_app.data;

import java.io.Serializable;

/**
 *  Data model for a single article in the database WITHOUT UID.
 *  DO NOT USE THIS CLASS FOR HANDLING DATA. This class is only intended for the read and
 *  write operations to the database.
 */
class ArticleDataModelReduced implements Serializable {
    private String name;
    private String description;
    private QuantityType quantityType;
    private double pricePerQty;

    public static ArticleDataModelReduced fromArticleDataModel(ArticleDataModel articleDataModel){
        return new ArticleDataModelReduced(articleDataModel.getName(), articleDataModel.getDescription(),
                articleDataModel.getQuantityType(), articleDataModel.getPricePerQty());
    }

    public static ArticleDataModel toArticleDataModel(ArticleDataModelReduced reducedModel, int uid){
        return new ArticleDataModel(reducedModel.getName(), reducedModel.getDescription(),
                reducedModel.getQuantityType(), reducedModel.getPricePerQty(), uid);
    }

    public ArticleDataModelReduced(String name, String description, QuantityType quantityType, double pricePerQty) {
        this.name = name;
        this.description = description;
        this.quantityType = quantityType;
        this.pricePerQty = pricePerQty;
    }

    public ArticleDataModelReduced() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

    public double getPricePerQty() {
        return pricePerQty;
    }

    public void setPricePerQty(double pricePerQty) {
        this.pricePerQty = pricePerQty;
    }
}
