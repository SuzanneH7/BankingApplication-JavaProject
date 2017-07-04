package workFlowBanking;

public class CustomerObject {
public int cid;
public int accid;
public String name;
public int balance;
public String pswd;
public CustomerObject(int cid, int accid, String name, int balance, String pswd) {
	this.cid=cid;
	this.accid=accid;
	this.name=name;
	this.balance=balance;
	this.pswd=pswd;
}
public CustomerObject(int accid, int cid, int balance) {
	this.cid=cid;
	this.accid=accid;
	this.balance=balance;
}
}
