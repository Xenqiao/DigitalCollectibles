// SPDX-License-Identifier: UNLICENSED
pragma solidity>=0.4.24 <0.6.11;

contract myGood{

    struct Artwork {
        address hash;           //藏品哈希
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

}