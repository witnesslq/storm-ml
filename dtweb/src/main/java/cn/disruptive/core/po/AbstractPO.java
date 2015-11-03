package cn.disruptive.core.po;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@SuppressWarnings("rawtypes")
public abstract class AbstractPO<PK extends Serializable>
implements PO<PK>, Comparable<PO<PK>>{

	private static final long serialVersionUID = 1860239169373176949L;

	public abstract PK getId();
	
	public int hashCode(){
		return new HashCodeBuilder().append(getId()).toHashCode(); 
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("ID", getId()).toString(); 
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if ((o == null) || (!(o instanceof PO))) {
			return false;
		}
		
		PO po = (PO)o;
		if ((po.getId() == null) && (getId() == null))
			return false;
		return new EqualsBuilder().append(getId(), po.getId()).isEquals();
	}
	
	public int compareTo(PO<PK> po){
		return new CompareToBuilder().append(getId(), po.getId()).toComparison();
	}
}
