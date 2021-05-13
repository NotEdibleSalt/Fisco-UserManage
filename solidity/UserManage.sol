pragma solidity ^0.5.1;

contract UserManage {

    struct User {

        uint256 id;

        string name;

        string sex;
    }

    mapping(uint256 => User) users;

    uint256[] ids;


    modifier notExist(){

        bool exist = isExist(uint256(msg.sender));
        require(!exist, "User already exist");
        _;
    }

    /**
     * 新增user
     */
    function add(string memory name, string memory sex) public notExist returns(uint256, string memory, string memory) {

        uint256 id = uint256(msg.sender);
        User memory user = User(id, name, sex);
        ids.push(id);
        users[id] = user;
        return (users[id].id, users[id].name, users[id].sex);
    }

    /**
     * 更新User
     *
     */
    function update(string memory name, string memory sex) public returns(uint256, string memory, string memory) {

        bool exist = isExist(uint256(msg.sender));
        require(exist, "User not exist");

        uint256 id = uint256(msg.sender);
        User memory user = User(id, name, sex);
        users[id] = user;
        return (users[id].id, users[id].name, users[id].sex);
    }

    /**
     * 删除User
     *
     */
    function del(uint256 id) public {

        uint8 index = 0;
        bool exist = false;
        for (uint8 i = 0; i < ids.length; i++){

            if (uint256(msg.sender) == ids[i]){
                exist = true;
                index = i;
                break;
            }
        }

        require(exist, "User not exist");
        delete ids[index];
        delete users[id];
    }

    /**
     * 查询User
     *
     */
    function get(uint256 id) public view returns(uint256, string memory, string memory){

        return (users[id].id, users[id].name, users[id].sex);
    }

    /**
     * 用户是否存在
     *
     */
    function isExist(uint256 id) private view  returns(bool){

        bool exist = false;
        for (uint8 i = 0; i < ids.length; i++){

            if (uint256(id) == ids[i]){
                exist = true;
                break;
            }
        }
        return exist;
    }

    function test() public pure returns(string memory){

        return "hello world";
    }
}