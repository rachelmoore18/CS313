import java.io.Serializable;

public class Clientobject implements Serializable {

private String cmd;
private String op;
private String re;



public String getCommand() {
	return cmd;
}

public void setCommand(String cmd) {
	this.cmd = cmd;
}

public String getOperand(){
	return op;

}

public void setOperand(String op) {
	this.op = op;
}

public void setResult(String re) {
	this.re = re;
}

}
