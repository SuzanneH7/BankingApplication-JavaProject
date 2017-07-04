package workFlowBanking;

public class TransactionObject {
public int tid;
public int accid;
public int type;
public int amount;
public int benefactor;
public int balance;
public TransactionObject(int tid,int accid,int type,int amount,int benefactor,int balance)
{
	this.tid=tid;
	this.accid=accid;
	this.type=type;
	this.amount=amount;
	this.benefactor=benefactor;
	this.balance=balance;
}
}
