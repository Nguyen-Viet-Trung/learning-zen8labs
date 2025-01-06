package com.example.BlockchainStarter.BlockchainExample;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static HashMap<String, TransactionOutput> UTXOs = new HashMap<String, TransactionOutput>();

    public static int difficulty = 5;
    public static float minimumTransaction = 0.1f;
    public static Wallet walletA;
    public static Wallet walletB;

    public static Transaction genesisTransaction;

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());
        walletA = new Wallet();
        walletB = new Wallet();
        Wallet coinbase = new Wallet();

        genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f , null);
        genesisTransaction.generateSignature(coinbase.privateKey);
        genesisTransaction.transactionId = "0";
        genesisTransaction.outputs.add(new TransactionOutput(genesisTransaction.recipient, genesisTransaction.value, genesisTransaction.transactionId));
        UTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        System.out.println("Creating and Mining Genesis block... ");
        Block genesis = new Block("0");
        genesis.addTransaction(genesisTransaction);
        addBlock(genesis);

        //testing with instances
        Block block1 = new Block(genesis.getHash());
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("\nWallet A is attemping to send funds (40) to walletB");
        block1.addTransaction(walletA.sendFunds(walletB.publicKey, 40f));
        addBlock(block1);
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("\nWalletB's balance is: " + walletB.getBalance());

        Block block2 = new Block(block1.getHash());
        System.out.println("\nWalletA Attempting to send more funds (1000) than it has...");
        block2.addTransaction(walletA.sendFunds(walletB.publicKey, 1000f));
        addBlock(block2);
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("WalletB's balance is: " + walletB.getBalance());

        Block block3 = new Block(block2.getHash());
        System.out.println("\nWalletB is Attempting to send funds (20) to WalletA...");
        block3.addTransaction(walletB.sendFunds( walletA.publicKey, 20));
        addBlock(block3);
        System.out.println("\nWalletA's balance is: " + walletA.getBalance());
        System.out.println("WalletB's balance is: " + walletB.getBalance());

        isChainValid();
    }
    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        HashMap<String, TransactionOutput> tempUTXOs = new HashMap<String,TransactionOutput>();
        tempUTXOs.put(genesisTransaction.outputs.get(0).id, genesisTransaction.outputs.get(0));

        for(int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered and calculated hash
            if(!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("#Current Hashes not equal");
                return false;
            }
            //compare previous and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous hash not equal");
            }
            //check if hash is solved
            if(!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
            TransactionOutput tempOutput;
            for(int a =0; a< currentBlock.getTransactions().size();a++){
                Transaction currentTransaction = currentBlock.getTransactions().get(a);
                if(!currentTransaction.verifySignature()){
                    System.out.println("Signature on Transaction invalid!");
                    return false;
                }
                if(currentTransaction.getInputsValue() != currentTransaction.getOutputsValue()){
                    System.out.println("Input and output value not equal");
                    return false;
                }
                for(TransactionInput input: currentTransaction.inputs){
                    tempOutput = tempUTXOs.get(input.getTransactionOutputId());

                    if(tempOutput == null){
                        System.out.println("Input on Transaction is missing");
                        return false;
                    }

                    if(input.getUTXO().value != tempOutput.value){
                        System.out.println("Input transaction value is invalid");
                        return false;
                    }
                    tempUTXOs.remove(input.getTransactionOutputId());
                }
                for(TransactionOutput output: currentTransaction.outputs){
                    tempUTXOs.put(output.id,output);
                }
                if(currentTransaction.outputs.get(0).recipent  != currentTransaction.recipient){
                    System.out.println("Transaction output recipent is not who it should be");
                    return false;
                }
                if (currentTransaction.outputs.get(1).recipent != currentTransaction.sender) {
                    System.out.println("Transaction output 'change' is not the sender");
                    return false;
                }
            }
        }
       System.out.println("Blockchain is invalid");
       return true;     
    }
    public static void addBlock(Block newBlock){
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }   
}
