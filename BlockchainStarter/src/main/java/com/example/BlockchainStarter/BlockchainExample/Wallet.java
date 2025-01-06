package com.example.BlockchainStarter.BlockchainExample;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {
    public PublicKey publicKey;
    public PrivateKey privateKey;
    public HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public Wallet(){
        generateKeyPair();
    }
    private void generateKeyPair(){
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyGen.initialize(ecSpec, random);
            KeyPair keypair = keyGen.generateKeyPair();
            privateKey = keypair.getPrivate();
            publicKey = keypair.getPublic();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    //returns the balance and stores the UTXO's owned by this wallet
    public float getBalance(){
        float total = 0;
        for(Map.Entry<String, TransactionOutput> item:Blockchain.UTXOs.entrySet()){
            TransactionOutput UTXO = item.getValue();
            if(UTXO.isMine(publicKey)){
                UTXOs.put(UTXO.id, UTXO);
                total += UTXO.value;
            }
        }
        return total;
    }
    public Transaction sendFunds(PublicKey recipent, float value){
        if(getBalance() < value){
            System.out.println("Not enough fund for transaction!");
            return null;
        }
        //create array list of inputs
        ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
        float total = 0;
        for(Map.Entry<String, TransactionOutput> item: UTXOs.entrySet()){
            TransactionOutput utxo = item.getValue();
            total += utxo.value;
            inputs.add(new TransactionInput(utxo.id));
            if(total > value) break;

        }
        Transaction transaction = new Transaction(publicKey, recipent, value, inputs);
        transaction.generateSignature(privateKey);

        for(TransactionInput i: inputs){
            UTXOs.remove(i.getTransactionOutputId());
        }
        return transaction;
    }
}
