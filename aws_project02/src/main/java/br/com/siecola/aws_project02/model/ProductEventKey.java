package br.com.siecola.aws_project02.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEventKey {

    public ProductEventKey() {}

    private String pk;
    private String sk;

    @DynamoDBHashKey(attributeName = "pk")
    public String getPk() {
        return pk;
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return sk;
    }
}
