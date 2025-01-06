package com.example.BlockchainStarter.BlockchainExample;

import java.security.PublicKey;

public class TransactionOutput {
    public String id;
    public PublicKey recipent;
    public float value;
    public String parentTransactionId;

    public TransactionOutput(PublicKey recipent, float value, String parentTransactionId){
        this.recipent = recipent;
        this.value = value;
        this.parentTransactionId = parentTransactionId;
        this.id = StringUtil.applySha256(StringUtil.getStringFromKey(recipent)+ Float.toString(value)+parentTransactionId);
    }
    public boolean isMine(PublicKey publicKey){
        return (publicKey == recipent);
    }
}
