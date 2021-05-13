package com.ss.contract.solidity;

import com.ss.contract.config.FiscoBcos;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.ABICodecException;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.transaction.codec.decode.TransactionDecoderService;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.fisco.bcos.sdk.transaction.model.exception.TransactionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManageTest {

    @Autowired
    private FiscoBcos fiscoBcos;

    @Test
    public void testClient() throws ContractException, ABICodecException, TransactionException, IOException {

        BcosSDK bcosSDK = fiscoBcos.getBcosSDK();
        Client client = bcosSDK.getClient(Integer.valueOf(1));

        // 获取区块号
        BlockNumber blockNumber = client.getBlockNumber();
        log.info("blockNumber: {}", blockNumber);

        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();

        // 部署合约
        UserManage userManage = UserManage.deploy(client, cryptoKeyPair);
        String contractAddress = userManage.getContractAddress();
        log.info("contractAddress: {}", contractAddress);

        // 调用合约test方法
        String test = userManage.test();
        log.info("test: {}", test);

        // 调用合约add方法，并获得交易回执
        TransactionReceipt receipt = userManage.add("Alice", "nv");
        log.info("TransactionReceipt: {}", receipt.toString());
        // 获取当前群组对应的密码套件接口
        CryptoSuite cryptoSuite = client.getCryptoSuite();
        // 构造TransactionDecoderService实例，传入是否密钥类型参数。
        TransactionDecoderInterface decoder = new TransactionDecoderService(cryptoSuite);
        // 解码交易回执
        TransactionResponse transactionResponse = decoder.decodeReceiptWithValues(UserManage.ABI, "add", receipt);
        // 交易的output信息
        log.info("TransactionResponse: {}", transactionResponse.getReturnObject());
        BigInteger id = (BigInteger) transactionResponse.getReturnObject().get(0);

        Tuple3<BigInteger, String, String> tuple3 = userManage.get(id);
        log.info("id: {}, name: {}, sex: {}", tuple3.getValue1(), tuple3.getValue2(), tuple3.getValue3());
    }
}