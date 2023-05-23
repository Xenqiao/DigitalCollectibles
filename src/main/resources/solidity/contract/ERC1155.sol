pragma solidity ^0.4.26;

contract Collectible{
    struct Artwork {
        address hash;            //藏品哈希
        string title;           //名称
        string description;     //描述
        uint price;             //价格
        address owner;          //所有者
        bool isForSale;         //是否在售
    }

    // 数字藏品数组
    Artwork[] public artworks;

    // 事件,让外界观察状态变化
    event ArtCreated (
        uint indexed _id,
        string _title,
        string _description
    );

    // 创建藏品
    function createArtwork(
        address _hash,
        string memory _title,
        string memory _description,
        uint _price
    ) public {

        artworks.push(Artwork({
            hash: _hash,
            title: _title,
            description: _description,
            price: _price,
            owner: msg.sender,
            isForSale: true
        }));

        emit ArtCreated(artworks.length -1, _title, _description);
    }

    //修改藏品信息
    function updateArtwork(
        uint _id,
        string memory _newTitle,
        string memory _newDesc
    ) public {
        // 只有藏品所有人才能修改
        require(msg.sender == artworks[_id].owner);
        // 更新藏品信息
        artworks[_id].title = _newTitle;
        artworks[_id].description = _newDesc;
        // 触发 ArtUpdated 事件,记录修改日志
        emit ArtCreated(_id, _newTitle, _newDesc);
    }


    //购买藏品
    function buyArtwork(uint _id, address _buyer) payable public{
        Artwork storage artwork = artworks[_id];
        artwork.owner = _buyer;
        artwork.isForSale = false;
    }



    event TransferSingle(address indexed _operator, address indexed _from, address indexed _to, uint256 _id, uint256 _value);
    event TransferBatch(address indexed _operator, address indexed _from, address indexed _to, uint256[] _ids, uint256[] _values);
    event Approval(address indexed _owner, address indexed _operator, uint256 _id);

    mapping (address => mapping (uint256 => uint256)) public balances;
    mapping (uint256 => address) public approved;

    function balanceOf(address _owner, uint256 _id) public view returns (uint256) {
        return balances[_owner][_id];
    }

    function setApprovalForAll(address _operator, bool _approved) public {

    }

    function isApprovedForAll(address _owner, address _operator) public view returns (bool) {

        return true;
    }

    function safeTransferFrom(address _from, address _to, uint256 _id, uint256 _value, bytes memory _data) public {
        // 拒绝传输到 to the 0x0 address
        require(_to != address(0x0), "Invalid recipient address");
        // 检查发件人是否有足够的余额
        require(balances[_from][_id] >= _value, "Insufficient balance");

        // 转送 the tokens
        balances[_from][_id] -= _value;
        balances[_to][_id] += _value;

        // 触发转送事件
        emit TransferSingle(msg.sender, _from,_to, _id, _value);

        // 如果是智能合约，则处理接收方合约
        if (_isContract(_to)) {
            _callOnERC1155Received(_from, _to, _id, _value, _data);
        }
    }

    function safeBatchTransferFrom(address _from, address _to, uint256[] memory _ids, uint256[] memory _values, bytes memory _data) public {
        require(_to != address(0x0), "Invalid recipient address");
        // Reject transfers to the 0x0 address
        require(_ids.length == _values.length, "Invalid input arrays");
        // Check the input arrays have the same length

        for (uint i = 0; i < _ids.length; i++) {
            uint256 id = _ids[i];
            uint256 value = _values[i];

            require(balances[_from][id] >= value, "Insufficient balance");
            // Check if the sender has enough balance

            // Transfer the tokens
            balances[_from][id] -= value;
            balances[_to][id] += value;

            // Emit the transfer event
            emit TransferSingle(msg.sender, _from, _to, id, value);

            // Handle the receiver contract if it's a smart contract
            if (_isContract(_to)) {
                _callOnERC1155Received(_from, _to, id, value, _data);
            }
            emit TransferBatch(msg.sender, _from, _to, _ids, _values);
        }
    }


    function setApprovalForOne(address _operator, uint256 _id) public {
        approved[_id] = _operator;
        emit Approval(msg.sender, _operator, _id);
    }

    function getApproved(uint256 _id) public view returns (address) {
        return approved[_id];
    }

    function _callOnERC1155Received(
        address _from,
        address _to,
        uint256 _id,
        uint256 _value,
        bytes memory _data
    ) internal {



    }

    function _isContract(address _addr) internal view returns (bool) {
        uint256 size;
        assembly { size := extcodesize(_addr) }
        return size > 0;
    }


}