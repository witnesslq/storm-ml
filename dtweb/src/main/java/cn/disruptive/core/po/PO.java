package cn.disruptive.core.po;

import java.io.Serializable;

public interface PO<PK extends Serializable> extends Serializable {
	public abstract PK getId();

	public abstract void setId(PK paramPK);
}
