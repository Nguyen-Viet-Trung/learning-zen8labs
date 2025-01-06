package com.example.BlockchainStarter.BlockchainExample;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Array;
import java.util.ArrayList;

public class Transaction {
    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    public static int sequence = 0;
    
    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs){
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }
    // calculate transaction hash used as its ID
    private String calculateHash(){
        sequence++;
        return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value));
    }
    public void generateSignature(PrivateKey privateKey){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        signature = StringUtil.applyECDSASignature(privateKey, data);
    }
    public boolean verifySignature(){
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
        return StringUtil.verifyECDSASignature(sender, data, signature);
    }
    public boolean processTransaction(){
        if(!verifySignature()){
            System.out.println("#Transaction Signature failed to verify");
            return false;
        }
        for(TransactionInput input : inputs){
            input.setUTXO(Blockchain.UTXOs.get(input.getTransactionOutputId()));
        }
        if(getInputsValue() < Blockchain.minimumTransaction){
            System.out.println("#Transaction Inputs to small: " + this.value);
            return false;
        }
        // generate transaction outputs:
        float leftOver = getInputsValue() - value;
        transactionId = calculateHash();
        outputs.add(new TransactionOutput(this.recipient, value, transactionId));
        outputs.add(new TransactionOutput(this.sender, leftOver, transactionId));

        for(TransactionOutput o :outputs){
            Blockchain.UTXOs.put(o.id,o);
        }

        for(TransactionInput input : inputs){
            if(input.getUTXO() == null) continue;
            Blockchain.UTXOs.remove(input.getUTXO().id);

    }
    return true;
    }
    public float getInputsValue(){
        float total = 0;
        for(TransactionInput input : inputs){
            if(input.getUTXO() == null) continue;
            total += input.getUTXO().value;
        }
        return total;
    }
    public float getOutputsValue(){
        float total = 0;
        for(TransactionOutput output : outputs){
            total += output.value;
        }
        return total;
    }
};
