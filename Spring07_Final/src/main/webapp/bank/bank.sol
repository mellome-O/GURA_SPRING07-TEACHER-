// This needs to be the first line.
pragma solidity ^0.4.18;

// give your contract a name
contract SotoLABbank {
    
    /*
        key 값은 address(계정주소)
        value 값은 uint(정수)
        인 mapping 정의하기 
    */
    mapping (address => uint) balanceAccount;    
    
    // declare a deposit function that takes an input called amount
    function deposit(uint amount) public {
        // 인자로 전달된 정수(돈)를 함수를 호출한 계정 key 값으로
        // 누적 시키기 
        balanceAccount[msg.sender] += amount;
    }
    
    // a getBalance function that accepts no inputs but returns
    // the amount in the balanceAccount array
    function getBalance() returns (uint balance){
        return balanceAccount[msg.sender];
    }
}