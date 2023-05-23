package util.toolContract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple6;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class MyGood extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610beb806100206000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633a006bd2146100675780634b602673146101405780636aaf37e0146102ca578063c772891414610383575b600080fd5b34801561007357600080fd5b5061013e600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291905050506103c3565b005b34801561014c57600080fd5b5061016b6004803603810190808035906020019092919050505061065a565b604051808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001806020018681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184151515158152602001838103835288818151815260200191508051906020019080838360005b83811015610223578082015181840152602081019050610208565b50505050905090810190601f1680156102505780820380516001836020036101000a031916815260200191505b50838103825287818151815260200191508051906020019080838360005b8381101561028957808201518184015260208101905061026e565b50505050905090810190601f1680156102b65780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b3480156102d657600080fd5b5061038160048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610822565b005b6103c160048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610a15565b005b600060c0604051908101604052808673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018481526020018381526020013373ffffffffffffffffffffffffffffffffffffffff168152602001600115158152509080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010190805190602001906104b0929190610a9a565b5060408201518160020190805190602001906104cd929190610a9a565b506060820151816003015560808201518160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a08201518160040160146101000a81548160ff0219169083151502179055505050506001600080549050037f301d33d3d97b04f56f7da2a48b2dd7babec430567ef1b53e3840838b70fb98be8484604051808060200180602001838103835285818151815260200191508051906020019080838360005b838110156105b2578082015181840152602081019050610597565b50505050905090810190601f1680156105df5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156106185780820151818401526020810190506105fd565b50505050905090810190601f1680156106455780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a250505050565b60008181548110151561066957fe5b90600052602060002090600502016000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561073b5780601f106107105761010080835404028352916020019161073b565b820191906000526020600020905b81548152906001019060200180831161071e57829003601f168201915b505050505090806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107d95780601f106107ae576101008083540402835291602001916107d9565b820191906000526020600020905b8154815290600101906020018083116107bc57829003601f168201915b5050505050908060030154908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060040160149054906101000a900460ff16905086565b60008381548110151561083157fe5b906000526020600020906005020160040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561089c57600080fd5b816000848154811015156108ac57fe5b906000526020600020906005020160010190805190602001906108d0929190610b1a565b50806000848154811015156108e157fe5b90600052602060002090600502016002019080519060200190610905929190610b1a565b50827f301d33d3d97b04f56f7da2a48b2dd7babec430567ef1b53e3840838b70fb98be8383604051808060200180602001838103835285818151815260200191508051906020019080838360005b8381101561096e578082015181840152602081019050610953565b50505050905090810190601f16801561099b5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156109d45780820151818401526020810190506109b9565b50505050905090810190601f168015610a015780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a2505050565b60008083815481101515610a2557fe5b90600052602060002090600502019050818160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008160040160146101000a81548160ff021916908315150217905550505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610adb57805160ff1916838001178555610b09565b82800160010185558215610b09579182015b82811115610b08578251825591602001919060010190610aed565b5b509050610b169190610b9a565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b5b57805160ff1916838001178555610b89565b82800160010185558215610b89579182015b82811115610b88578251825591602001919060010190610b6d565b5b509050610b969190610b9a565b5090565b610bbc91905b80821115610bb8576000816000905550600101610ba0565b5090565b905600a165627a7a7230582074461c94e7dcaf1d2023e95929ae6c5bd966340d336d28fb20a20b214284b70d0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610beb806100206000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635a080c5414610067578063ac7d701c146101f1578063c193ab6314610231578063c2c6d51a146102ea575b600080fd5b34801561007357600080fd5b50610092600480360381019080803590602001909291905050506103c3565b604051808773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001806020018681526020018573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200184151515158152602001838103835288818151815260200191508051906020019080838360005b8381101561014a57808201518184015260208101905061012f565b50505050905090810190601f1680156101775780820380516001836020036101000a031916815260200191505b50838103825287818151815260200191508051906020019080838360005b838110156101b0578082015181840152602081019050610195565b50505050905090810190601f1680156101dd5780820380516001836020036101000a031916815260200191505b509850505050505050505060405180910390f35b61022f60048036038101908080359060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061058b565b005b34801561023d57600080fd5b506102e860048036038101908080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610610565b005b3480156102f657600080fd5b506103c1600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610803565b005b6000818154811015156103d257fe5b90600052602060002090600502016000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104a45780601f10610479576101008083540402835291602001916104a4565b820191906000526020600020905b81548152906001019060200180831161048757829003601f168201915b505050505090806002018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105425780601f1061051757610100808354040283529160200191610542565b820191906000526020600020905b81548152906001019060200180831161052557829003601f168201915b5050505050908060030154908060040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060040160149054906101000a900460ff16905086565b6000808381548110151561059b57fe5b90600052602060002090600502019050818160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060008160040160146101000a81548160ff021916908315150217905550505050565b60008381548110151561061f57fe5b906000526020600020906005020160040160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561068a57600080fd5b8160008481548110151561069a57fe5b906000526020600020906005020160010190805190602001906106be929190610a9a565b50806000848154811015156106cf57fe5b906000526020600020906005020160020190805190602001906106f3929190610a9a565b50827feb0a41cb649340409d16b3dc1194f282fabc36b755be31f0f63cfb1d5d4862528383604051808060200180602001838103835285818151815260200191508051906020019080838360005b8381101561075c578082015181840152602081019050610741565b50505050905090810190601f1680156107895780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b838110156107c25780820151818401526020810190506107a7565b50505050905090810190601f1680156107ef5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a2505050565b600060c0604051908101604052808673ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018481526020018381526020013373ffffffffffffffffffffffffffffffffffffffff168152602001600115158152509080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010190805190602001906108f0929190610b1a565b50604082015181600201908051906020019061090d929190610b1a565b506060820151816003015560808201518160040160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060a08201518160040160146101000a81548160ff0219169083151502179055505050506001600080549050037feb0a41cb649340409d16b3dc1194f282fabc36b755be31f0f63cfb1d5d4862528484604051808060200180602001838103835285818151815260200191508051906020019080838360005b838110156109f25780820151818401526020810190506109d7565b50505050905090810190601f168015610a1f5780820380516001836020036101000a031916815260200191505b50838103825284818151815260200191508051906020019080838360005b83811015610a58578082015181840152602081019050610a3d565b50505050905090810190601f168015610a855780820380516001836020036101000a031916815260200191505b5094505050505060405180910390a250505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610adb57805160ff1916838001178555610b09565b82800160010185558215610b09579182015b82811115610b08578251825591602001919060010190610aed565b5b509050610b169190610b9a565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b5b57805160ff1916838001178555610b89565b82800160010185558215610b89579182015b82811115610b88578251825591602001919060010190610b6d565b5b509050610b969190610b9a565b5090565b610bbc91905b80821115610bb8576000816000905550600101610ba0565b5090565b905600a165627a7a72305820e94c03e75ba7bb391118e6d1c7d90adec844e76e2b82c40234d154fb795dc2580029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"_hash\",\"type\":\"address\"},{\"name\":\"_title\",\"type\":\"string\"},{\"name\":\"_description\",\"type\":\"string\"},{\"name\":\"_price\",\"type\":\"uint256\"}],\"name\":\"createArtwork\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"artworks\",\"outputs\":[{\"name\":\"hash\",\"type\":\"address\"},{\"name\":\"title\",\"type\":\"string\"},{\"name\":\"description\",\"type\":\"string\"},{\"name\":\"price\",\"type\":\"uint256\"},{\"name\":\"owner\",\"type\":\"address\"},{\"name\":\"isForSale\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_newTitle\",\"type\":\"string\"},{\"name\":\"_newDesc\",\"type\":\"string\"}],\"name\":\"updateArtwork\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_id\",\"type\":\"uint256\"},{\"name\":\"_buyer\",\"type\":\"address\"}],\"name\":\"buyArtwork\",\"outputs\":[],\"payable\":true,\"stateMutability\":\"payable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_id\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_title\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"_description\",\"type\":\"string\"}],\"name\":\"ArtCreated\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CREATEARTWORK = "createArtwork";

    public static final String FUNC_ARTWORKS = "artworks";

    public static final String FUNC_UPDATEARTWORK = "updateArtwork";

    public static final String FUNC_BUYARTWORK = "buyArtwork";

    public static final Event ARTCREATED_EVENT = new Event("ArtCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected MyGood(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt createArtwork(String _hash, String _title, String _description, BigInteger _price) {
        final Function function = new Function(
                FUNC_CREATEARTWORK, 
                Arrays.<Type>asList(new Address(_hash),
                new Utf8String(_title),
                new Utf8String(_description),
                new Uint256(_price)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] createArtwork(String _hash, String _title, String _description, BigInteger _price, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEARTWORK, 
                Arrays.<Type>asList(new Address(_hash),
                new Utf8String(_title),
                new Utf8String(_description),
                new Uint256(_price)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateArtwork(String _hash, String _title, String _description, BigInteger _price) {
        final Function function = new Function(
                FUNC_CREATEARTWORK, 
                Arrays.<Type>asList(new Address(_hash),
                new Utf8String(_title),
                new Utf8String(_description),
                new Uint256(_price)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple4<String, String, String, BigInteger> getCreateArtworkInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEARTWORK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple4<String, String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue()
                );
    }

    public Tuple6<String, String, String, BigInteger, String, Boolean> artworks(BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_ARTWORKS, 
                Arrays.<Type>asList(new Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple6<String, String, String, BigInteger, String, Boolean>(
                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (String) results.get(4).getValue(), 
                (Boolean) results.get(5).getValue());
    }

    public TransactionReceipt updateArtwork(BigInteger _id, String _newTitle, String _newDesc) {
        final Function function = new Function(
                FUNC_UPDATEARTWORK, 
                Arrays.<Type>asList(new Uint256(_id),
                new Utf8String(_newTitle),
                new Utf8String(_newDesc)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] updateArtwork(BigInteger _id, String _newTitle, String _newDesc, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_UPDATEARTWORK, 
                Arrays.<Type>asList(new Uint256(_id),
                new Utf8String(_newTitle),
                new Utf8String(_newDesc)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForUpdateArtwork(BigInteger _id, String _newTitle, String _newDesc) {
        final Function function = new Function(
                FUNC_UPDATEARTWORK, 
                Arrays.<Type>asList(new Uint256(_id),
                new Utf8String(_newTitle),
                new Utf8String(_newDesc)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<BigInteger, String, String> getUpdateArtworkInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_UPDATEARTWORK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public TransactionReceipt buyArtwork(BigInteger _id, String _buyer) {
        final Function function = new Function(
                FUNC_BUYARTWORK, 
                Arrays.<Type>asList(new Uint256(_id),
                new Address(_buyer)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public byte[] buyArtwork(BigInteger _id, String _buyer, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_BUYARTWORK, 
                Arrays.<Type>asList(new Uint256(_id),
                new Address(_buyer)),
                Collections.<TypeReference<?>>emptyList());
        return asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForBuyArtwork(BigInteger _id, String _buyer) {
        final Function function = new Function(
                FUNC_BUYARTWORK, 
                Arrays.<Type>asList(new Uint256(_id),
                new Address(_buyer)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<BigInteger, String> getBuyArtworkInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_BUYARTWORK, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public List<ArtCreatedEventResponse> getArtCreatedEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(ARTCREATED_EVENT, transactionReceipt);
        ArrayList<ArtCreatedEventResponse> responses = new ArrayList<ArtCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ArtCreatedEventResponse typedResponse = new ArtCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._title = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._description = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeArtCreatedEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(ARTCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeArtCreatedEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(ARTCREATED_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static MyGood load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new MyGood(contractAddress, client, credential);
    }

    public static MyGood deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(MyGood.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class ArtCreatedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger _id;

        public String _title;

        public String _description;
    }
}
