package com.example.BlockchainStarter.BlockchainExample;

import java.util.ArrayList;
import java.util.Date;


public class Block {
    private String ger;
    private String previousHash;
    public String getHash() {
        return ger;
    }
    public String getPreviousHash() {
        return previousHash;
    }
    private long timeStamp;
    private int nonce;
    private String merkleRoot;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public String getMerkleRoot() {
        return merkleRoot;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public Block(String previousHash){
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.ger = calculateHash();
    }
    public String calculateHash(){
        String hash = StringUtil.applySha256(previousHash + merkleRoot + Long.toString(timeStamp) + Integer.toString(nonce));
        return hash;
    }
    public void mineBlock(int difficulty){
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        //create a string contains difficulty * "0"
        String target = new String( new char[difficulty]).replace('\0', '0');
        while(!ger.substring(0, difficulty).equals(target)){
            nonce++;
            ger = calculateHash();
        }
        System.out.println("Block Mined!!! : " + ger);
    }
    public boolean addTransaction(Transaction transaction){
        //check if exists
        if(transaction == null) return false;
        if(!previousHash.equals("0")){
            if(!transaction.processTransaction()){
                System.out.println("Transaction Failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
