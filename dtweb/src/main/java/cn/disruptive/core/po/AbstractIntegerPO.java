package cn.disruptive.core.po;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AbstractIntegerPO extends AbstractPO<Integer>{
	
	private static final long serialVersionUID = -221398645210591020L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;

	public Integer getId(){
	    return this.id; 
	}

	public void setId(Integer id) {
	    this.id = id;
	}

}
