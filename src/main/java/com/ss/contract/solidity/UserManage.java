package com.ss.contract.solidity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class UserManage extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061114f806100206000396000f3fe608060405260043610610067576000357c0100000000000000000000000000000000000000000000000000000000900480632082f0c51461006c5780634fe8f283146102b75780637feaffaf1461050257806380f780cc14610629578063d6389ad514610664575b600080fd5b34801561007857600080fd5b506101c96004803603604081101561008f57600080fd5b81019080803590602001906401000000008111156100ac57600080fd5b8201836020820111156100be57600080fd5b803590602001918460018302840111640100000000831117156100e057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561014357600080fd5b82018360208201111561015557600080fd5b8035906020019184600183028401116401000000008311171561017757600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506106f4565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156102135780820151818401526020810190506101f8565b50505050905090810190601f1680156102405780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561027957808201518184015260208101905061025e565b50505050905090810190601f1680156102a65780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b3480156102c357600080fd5b50610414600480360360408110156102da57600080fd5b81019080803590602001906401000000008111156102f757600080fd5b82018360208201111561030957600080fd5b8035906020019184600183028401116401000000008311171561032b57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561038e57600080fd5b8201836020820111156103a057600080fd5b803590602001918460018302840111640100000000831117156103c257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506109e1565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561045e578082015181840152602081019050610443565b50505050905090810190601f16801561048b5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156104c45780820151818401526020810190506104a9565b50505050905090810190601f1680156104f15780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561050e57600080fd5b5061053b6004803603602081101561052557600080fd5b8101908080359060200190929190505050610ca1565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561058557808201518184015260208101905061056a565b50505050905090810190601f1680156105b25780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156105eb5780820151818401526020810190506105d0565b50505050905090810190601f1680156106185780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561063557600080fd5b506106626004803603602081101561064c57600080fd5b8101908080359060200190929190505050610e2b565b005b34801561067057600080fd5b50610679610f76565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156106b957808201518184015260208101905061069e565b50505050905090810190601f1680156106e65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000606080600061071a3373ffffffffffffffffffffffffffffffffffffffff16610fb3565b905080151515610792576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f5573657220616c7265616479206578697374000000000000000000000000000081525060200191505060405180910390fd5b60003373ffffffffffffffffffffffffffffffffffffffff1690506107b5611014565b606060405190810160405280838152602001898152602001888152509050600182908060018154018082558091505090600182039060005260206000200160009091929091909150555080600080848152602001908152602001600020600082015181600001556020820151816001019080519060200190610838929190611036565b506040820151816002019080519060200190610855929190611036565b5090505060008083815260200190815260200160002060000154600080848152602001908152602001600020600101600080858152602001908152602001600020600201818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561092e5780601f106109035761010080835404028352916020019161092e565b820191906000526020600020905b81548152906001019060200180831161091157829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109ca5780601f1061099f576101008083540402835291602001916109ca565b820191906000526020600020905b8154815290600101906020018083116109ad57829003601f168201915b505050505090509550955095505050509250925092565b60006060806000610a073373ffffffffffffffffffffffffffffffffffffffff16610fb3565b9050801515610a7e576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f55736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b60003373ffffffffffffffffffffffffffffffffffffffff169050610aa1611014565b60606040519081016040528083815260200189815260200188815250905080600080848152602001908152602001600020600082015181600001556020820151816001019080519060200190610af8929190611036565b506040820151816002019080519060200190610b15929190611036565b5090505060008083815260200190815260200160002060000154600080848152602001908152602001600020600101600080858152602001908152602001600020600201818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bee5780601f10610bc357610100808354040283529160200191610bee565b820191906000526020600020905b815481529060010190602001808311610bd157829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c8a5780601f10610c5f57610100808354040283529160200191610c8a565b820191906000526020600020905b815481529060010190602001808311610c6d57829003601f168201915b505050505090509550955095505050509250925092565b600060608060008085815260200190815260200160002060000154600080868152602001908152602001600020600101600080878152602001908152602001600020600201818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d7b5780601f10610d5057610100808354040283529160200191610d7b565b820191906000526020600020905b815481529060010190602001808311610d5e57829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e175780601f10610dec57610100808354040283529160200191610e17565b820191906000526020600020905b815481529060010190602001808311610dfa57829003601f168201915b505050505090509250925092509193909250565b6000809050600080905060008090505b6001805490508160ff161015610e9e5760018160ff16815481101515610e5d57fe5b90600052602060002001543373ffffffffffffffffffffffffffffffffffffffff161415610e915760019150809250610e9e565b8080600101915050610e3b565b50801515610f14576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f55736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b60018260ff16815481101515610f2657fe5b9060005260206000200160009055600080848152602001908152602001600020600080820160009055600182016000610f5f91906110b6565b600282016000610f6f91906110b6565b5050505050565b60606040805190810160405280600b81526020017f68656c6c6f20776f726c64000000000000000000000000000000000000000000815250905090565b6000806000905060008090505b6001805490508160ff16101561100a5760018160ff16815481101515610fe2","57fe5b9060005260206000200154841415610ffd576001915061100a565b8080600101915050610fc0565b5080915050919050565b6060604051908101604052806000815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061107757805160ff19168380011785556110a5565b828001600101855582156110a5579182015b828111156110a4578251825591602001919060010190611089565b5b5090506110b291906110fe565b5090565b50805460018160011615610100020316600290046000825580601f106110dc57506110fb565b601f0160209004906000526020600020908101906110fa91906110fe565b5b50565b61112091905b8082111561111c576000816000905550600101611104565b5090565b9056fea165627a7a72305820c5c3023290bbe6071bfd4bd24513b2f85c6bbb20e25419edb77125c6992236260029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5061114f806100206000396000f3fe608060405260043610610067576000357c0100000000000000000000000000000000000000000000000000000000900480632082f0c51461006c5780634fe8f283146102b75780637feaffaf1461050257806380f780cc14610629578063d6389ad514610664575b600080fd5b34801561007857600080fd5b506101c96004803603604081101561008f57600080fd5b81019080803590602001906401000000008111156100ac57600080fd5b8201836020820111156100be57600080fd5b803590602001918460018302840111640100000000831117156100e057600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561014357600080fd5b82018360208201111561015557600080fd5b8035906020019184600183028401116401000000008311171561017757600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506106f4565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b838110156102135780820151818401526020810190506101f8565b50505050905090810190601f1680156102405780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b8381101561027957808201518184015260208101905061025e565b50505050905090810190601f1680156102a65780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b3480156102c357600080fd5b50610414600480360360408110156102da57600080fd5b81019080803590602001906401000000008111156102f757600080fd5b82018360208201111561030957600080fd5b8035906020019184600183028401116401000000008311171561032b57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019064010000000081111561038e57600080fd5b8201836020820111156103a057600080fd5b803590602001918460018302840111640100000000831117156103c257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192905050506109e1565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561045e578082015181840152602081019050610443565b50505050905090810190601f16801561048b5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156104c45780820151818401526020810190506104a9565b50505050905090810190601f1680156104f15780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561050e57600080fd5b5061053b6004803603602081101561052557600080fd5b8101908080359060200190929190505050610ca1565b604051808481526020018060200180602001838103835285818151815260200191508051906020019080838360005b8381101561058557808201518184015260208101905061056a565b50505050905090810190601f1680156105b25780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156105eb5780820151818401526020810190506105d0565b50505050905090810190601f1680156106185780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34801561063557600080fd5b506106626004803603602081101561064c57600080fd5b8101908080359060200190929190505050610e2b565b005b34801561067057600080fd5b50610679610f76565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156106b957808201518184015260208101905061069e565b50505050905090810190601f1680156106e65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000606080600061071a3373ffffffffffffffffffffffffffffffffffffffff16610fb3565b905080151515610792576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f5573657220616c7265616479206578697374000000000000000000000000000081525060200191505060405180910390fd5b60003373ffffffffffffffffffffffffffffffffffffffff1690506107b5611014565b606060405190810160405280838152602001898152602001888152509050600182908060018154018082558091505090600182039060005260206000200160009091929091909150555080600080848152602001908152602001600020600082015181600001556020820151816001019080519060200190610838929190611036565b506040820151816002019080519060200190610855929190611036565b5090505060008083815260200190815260200160002060000154600080848152602001908152602001600020600101600080858152602001908152602001600020600201818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561092e5780601f106109035761010080835404028352916020019161092e565b820191906000526020600020905b81548152906001019060200180831161091157829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109ca5780601f1061099f576101008083540402835291602001916109ca565b820191906000526020600020905b8154815290600101906020018083116109ad57829003601f168201915b505050505090509550955095505050509250925092565b60006060806000610a073373ffffffffffffffffffffffffffffffffffffffff16610fb3565b9050801515610a7e576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f55736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b60003373ffffffffffffffffffffffffffffffffffffffff169050610aa1611014565b60606040519081016040528083815260200189815260200188815250905080600080848152602001908152602001600020600082015181600001556020820151816001019080519060200190610af8929190611036565b506040820151816002019080519060200190610b15929190611036565b5090505060008083815260200190815260200160002060000154600080848152602001908152602001600020600101600080858152602001908152602001600020600201818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610bee5780601f10610bc357610100808354040283529160200191610bee565b820191906000526020600020905b815481529060010190602001808311610bd157829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c8a5780601f10610c5f57610100808354040283529160200191610c8a565b820191906000526020600020905b815481529060010190602001808311610c6d57829003601f168201915b505050505090509550955095505050509250925092565b600060608060008085815260200190815260200160002060000154600080868152602001908152602001600020600101600080878152602001908152602001600020600201818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610d7b5780601f10610d5057610100808354040283529160200191610d7b565b820191906000526020600020905b815481529060010190602001808311610d5e57829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610e175780601f10610dec57610100808354040283529160200191610e17565b820191906000526020600020905b815481529060010190602001808311610dfa57829003601f168201915b505050505090509250925092509193909250565b6000809050600080905060008090505b6001805490508160ff161015610e9e5760018160ff16815481101515610e5d57fe5b90600052602060002001543373ffffffffffffffffffffffffffffffffffffffff161415610e915760019150809250610e9e565b8080600101915050610e3b565b50801515610f14576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f55736572206e6f7420657869737400000000000000000000000000000000000081525060200191505060405180910390fd5b60018260ff16815481101515610f2657fe5b9060005260206000200160009055600080848152602001908152602001600020600080820160009055600182016000610f5f91906110b6565b600282016000610f6f91906110b6565b5050505050565b60606040805190810160405280600b81526020017f68656c6c6f20776f726c64000000000000000000000000000000000000000000815250905090565b6000806000905060008090505b6001805490508160ff16101561100a5760018160ff16815481101515610fe2","57fe5b9060005260206000200154841415610ffd576001915061100a565b8080600101915050610fc0565b5080915050919050565b6060604051908101604052806000815260200160608152602001606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061107757805160ff19168380011785556110a5565b828001600101855582156110a5579182015b828111156110a4578251825591602001919060010190611089565b5b5090506110b291906110fe565b5090565b50805460018160011615610100020316600290046000825580601f106110dc57506110fb565b601f0160209004906000526020600020908101906110fa91906110fe565b5b50565b61112091905b8082111561111c576000816000905550600101611104565b5090565b9056fea165627a7a72305820c5c3023290bbe6071bfd4bd24513b2f85c6bbb20e25419edb77125c6992236260029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"name\":\"add\",\"type\":\"function\",\"constant\":false,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"nonpayable\",\"inputs\":[{\"name\":\"name\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"sex\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"},{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"methodSignatureAsString\":\"add(string,string)\"},{\"name\":\"update\",\"type\":\"function\",\"constant\":false,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"nonpayable\",\"inputs\":[{\"name\":\"name\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"sex\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"},{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"methodSignatureAsString\":\"update(string,string)\"},{\"name\":\"get\",\"type\":\"function\",\"constant\":true,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"view\",\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"}],\"outputs\":[{\"name\":\"\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"},{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"},{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"methodSignatureAsString\":\"get(uint256)\"},{\"name\":\"del\",\"type\":\"function\",\"constant\":false,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"nonpayable\",\"inputs\":[{\"name\":\"id\",\"type\":\"uint256\",\"indexed\":false,\"components\":null,\"typeAsString\":\"uint256\"}],\"outputs\":[],\"methodSignatureAsString\":\"del(uint256)\"},{\"name\":\"test\",\"type\":\"function\",\"constant\":true,\"payable\":false,\"anonymous\":false,\"stateMutability\":\"pure\",\"inputs\":[],\"outputs\":[{\"name\":\"\",\"type\":\"string\",\"indexed\":false,\"components\":null,\"typeAsString\":\"string\"}],\"methodSignatureAsString\":\"test()\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADD = "add";

    public static final String FUNC_UPDATE = "update";

    public static final String FUNC_GET = "get";

    public static final String FUNC_DEL = "del";

    public static final String FUNC_TEST = "test";

    protected UserManage(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt add(String name, String sex) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(sex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void add(String name, String sex, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(sex)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForAdd(String name, String sex) {
        final Function function = new Function(
                FUNC_ADD, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(sex)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getAddInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple3<BigInteger, String, String> getAddOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public TransactionReceipt update(String name, String sex) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(sex)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void update(String name, String sex, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(sex)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUpdate(String name, String sex) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(sex)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<String, String> getUpdateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple3<BigInteger, String, String> getUpdateOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_UPDATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public Tuple3<BigInteger, String, String> get(BigInteger id) throws ContractException {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple3<BigInteger, String, String>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue());
    }

    public TransactionReceipt del(BigInteger id) {
        final Function function = new Function(
                FUNC_DEL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void del(BigInteger id, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_DEL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForDel(BigInteger id) {
        final Function function = new Function(
                FUNC_DEL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint256(id)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getDelInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_DEL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public String test() throws ContractException {
        final Function function = new Function(FUNC_TEST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public static UserManage load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new UserManage(contractAddress, client, credential);
    }

    public static UserManage deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(UserManage.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}